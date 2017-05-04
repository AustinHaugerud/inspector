package org.inspector.java;

import org.inspector.items.ImmutableVariable;
import org.inspector.items.MutableVariable;

public class VariableParser {

    public static ImmutableVariable parseVariable(String source)
    {
        MutableVariable result = new MutableVariable();
        String parts[] = source.split(" ");

        if(parts.length == 2)
        {
            result.setType(parts[0]);
            result.setIdentifier(parts[1]);
        }
        else
        {
            result.setType(parts[1]);
            result.setType(parts[2]);
        }

        return result.asImmutable();
    }
}
