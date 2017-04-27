package org.inspector.items;

import org.inspector.bank.VariableBank;

public class Procedure {

    public String _procedureName;

    /**
     * The parameters to the function/method
     */
    public VariableBank _parameters = null;

    /**
     * The code block
     */
    public Block        _block      = null;
}
