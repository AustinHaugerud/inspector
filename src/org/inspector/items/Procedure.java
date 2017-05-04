package org.inspector.items;

import org.inspector.bank.VariableBank;

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
}
