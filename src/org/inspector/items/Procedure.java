package org.inspector.items;

import org.inspector.bank.VariableBank;

import java.util.List;

public class Procedure {

    /**
     * The parameters to the function/method
     */
    private VariableBank _parameters = null;

    /**
     * The code block
     */
    private Block        _block      = null;

    private String _name;

    private String _returnType;

    public List<String> _descriptors;

    private Procedure()
    {

    }

    public static Procedure buildProcedure(String name, String returnType, Block block, VariableBank params)
    {
        Procedure result = new Procedure();
        result._name = name;
        result._returnType = returnType;
        result._block = block;
        result._parameters = params;
        return result;
    }

    public Block block()
    {
        return _block;
    }

    public String name()
    {
        return _name;
    }

    public String returnType()
    {
        return _returnType;
    }

    public String getDescriptor()
    {
        StringBuilder sb = new StringBuilder();
        for(String str : _descriptors)
        {
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }
}
