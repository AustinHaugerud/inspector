package org.inspector.java;

import org.inspector.items.Block;

import java.util.Arrays;
import java.util.List;

public class BlockParser {

    public static Block parseBlock(String blockSource)
    {
        Block result = new Block();

        String blocklessSource = blockSource.substring(1, blockSource.length() - 1);

        List<String> statements = Arrays.asList(blocklessSource.split(";"));

        for(String statement : statements)
        {
            String parts[] = statement.split(" ",2);
            String type = parts[0];

            String rest = parts[1];

            String identifier = rest.replaceAll(" ","").replaceAll("=","").replaceAll(";","");

            result.getVariableBank().setVariable(type, identifier, null);
        }

        return result;
    }

    public static List<String> getStatements(String blockSource)
    {
        String blocklessSource = blockSource.substring(1, blockSource.length() - 1);
        return Arrays.asList(blocklessSource.split(";"));
    }
}
