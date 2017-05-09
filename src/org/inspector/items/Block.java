package org.inspector.items;

import org.apache.commons.lang3.StringUtils;
import org.inspector.bank.VariableBank;

public class Block {

    private String _source;

    private VariableBank _variables = new VariableBank();

    public VariableBank getVariableBank()
    {
        return _variables;
    }

    public void setSource(String source)
    {
        _source = source;
    }

    public int countUsages(String var)
    {
        String regex = "\\b" + var + "\\b";
        return _source.split(regex).length - 1;
    }
}
