package org.inspector.java;

import org.inspector.items.ImmutableVariable;
import org.inspector.items.MutableVariable;

import java.util.regex.Pattern;

public class VariableParser {

    public static ImmutableVariable parseVariable(String source)
    {
        MutableVariable result = new MutableVariable();
        String parts[] = source.replaceAll(Pattern.quote("{"),"").replaceAll(Pattern.quote("}"), "").trim().split(" ");

        if(parts.length < 2)
            return null;

        result.setType(parts[0]);

        String rhs[] = parts[1].replaceAll(" ","").split("=");
        if(rhs.length == 0)
            return null;

        String ident = rhs[0];
        result.setIdentifier(ident);

        return result.asImmutable();
    }
}
