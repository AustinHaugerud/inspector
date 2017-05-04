package org.inspector.items;

import org.inspector.bank.IVariableBankReadable;
import org.inspector.bank.VariableBank;

public class Block {

    private VariableBank _variables = null;

    public IVariableBankReadable getVariableBank()
    {
        return _variables;
    }
}
