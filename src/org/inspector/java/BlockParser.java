package org.inspector.java;

import org.inspector.items.Block;

import java.util.Arrays;
import java.util.List;

public class BlockParser {

    public static Block parseBlock(String blockSource)
    {
        Block result = new Block();
        List<String> statements = BlockParser.getStatements(blockSource);

        for(String statement : statements)
        {
            String[] parts = statement.split(" ");

            if(parts.length == 2)
            {
                String type = parts[0];
                String name = parts[1];
            }
            else if(parts.length == 3)
            {

            }
            else if(parts.length == 4)
            {

            }
        }

        return result;
    }

    public static List<String> getStatements(String blockSource)
    {
        String blocklessSource = blockSource.substring(1, blockSource.length() - 1);
        return Arrays.asList(blocklessSource.split(";"));
    }
}
