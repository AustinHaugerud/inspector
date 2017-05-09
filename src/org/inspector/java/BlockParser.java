package org.inspector.java;

import org.inspector.items.Block;
import org.inspector.items.ImmutableVariable;

import java.util.Arrays;
import java.util.List;

public class BlockParser {

    public static Block parseBlock(String blockSource)
    {
        Block result = new Block();

        String blocklessSource = blockSource.substring(1, blockSource.length() - 1);

        if(!blocklessSource.contains(";"))
        {
            return result;
        }

        List<String> statements = Arrays.asList(blocklessSource.split(";"));

        for(String statement : statements)
        {
            if(statement.contains("int") || statement.contains("String"))
            {
                ImmutableVariable var = VariableParser.parseVariable(statement);
                if(var != null)
                    result.getVariableBank().setVariable(var.type(), var.identifier(), var.value().orElse(null));
            }
        }

        result.setSource(blockSource);

        return result;
    }

    public static List<String> getStatements(String blockSource)
    {
        String blocklessSource = blockSource.substring(1, blockSource.length() - 1);
        return Arrays.asList(blocklessSource.split(";"));
    }
}
