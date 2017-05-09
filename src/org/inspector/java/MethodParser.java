package org.inspector.java;

import org.inspector.bank.VariableBank;
import org.inspector.items.Block;
import org.inspector.items.ImmutableVariable;
import org.inspector.items.Procedure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {
    public static Procedure parse(String methodSource)
    {
        List<String> descriptors = MethodParser.getDescriptors(methodSource);
        String blockSource = MethodParser.getBlock(methodSource);
        Block block = BlockParser.parseBlock(blockSource);

        String name = MethodParser.getName(descriptors);
        String returnType = MethodParser.getReturnType(descriptors);
        VariableBank parameters = MethodParser.getParameters(methodSource);

        Procedure result = Procedure.buildProcedure(name, returnType, block, parameters);
        result._descriptors = descriptors;
        return result;
    }

    private static List<String> getDescriptors(String methodSource)
    {
        int blockStart = methodSource.indexOf("{");
        String descriptorBlob = methodSource.substring(0, blockStart);

        return Arrays.asList(descriptorBlob.split(" "));
    }

    private static String getBlock(String methodSource)
    {
        int blockStart = methodSource.indexOf("{");
        return methodSource.substring(blockStart, methodSource.length());
    }

    private static String getName(List<String> descriptors)
    {
        return descriptors.get(descriptors.size() - 1);
    }

    private static boolean isReservedWord(String word)
    {
        String words[] = {
                "public",
                "private",
                "protected",
                "static"
        };

        return Arrays.asList(words).contains(word);
    }

    private static String getReturnType(List<String> descriptors)
    {
        String result = null;

        for(String descriptor : descriptors)
        {
            if(!MethodParser.isReservedWord(descriptor))
            {
                result = descriptor;
            }
        }

        return result;
    }

    private static VariableBank getParameters(String descriptorSource)
    {
        VariableBank result;
        final String regexString =
                Pattern.quote("(") + "(.*?)" + Pattern.quote(")");

        final Pattern pattern = Pattern.compile(regexString);

        final Matcher matcher = pattern.matcher(descriptorSource);

        String variablesSourceBlob = null;
        if(matcher.find())
        {
            variablesSourceBlob = matcher.group();
        }
        else
        {
            return null;
        }

        result = new VariableBank();

        if(variablesSourceBlob.replaceAll(" ","").length() == 2)
        {
            return result;
        }

        String variablesSource[] = variablesSourceBlob.split(",");

        for(String varSrc : variablesSource)
        {
            ImmutableVariable var = VariableParser.parseVariable(varSrc);
            result.setVariable(var.type(), var.identifier(), var.value().orElse(null));
        }

        return result;
    }
}
