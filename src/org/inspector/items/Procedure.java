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

    public int countUsages(ImmutableVariable var)
    {
        return 0;
    }

    public Block block()
    {
        return _block;
    }
}
