package org.inspector.items;

import org.inspector.bank.ProcedureBank;
import org.inspector.bank.VariableBank;

public class Class {

    public String _name;

    public ProcedureBank _procedureBank;

    public VariableBank _variableBank;

    public String getVariableUsageInfo()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Class[%s]", _name));
        sb.append(System.lineSeparator());
        for(Procedure proc : _procedureBank.getProcedures())
        {
            sb.append(String.format("Procedure[%s]", proc.name()));
            sb.append(System.lineSeparator());
            for(ImmutableVariable var : proc.block().getVariableBank().getImmutableVariables())
            {
                sb.append(String.format("Variable Ret[%s] Name[%s] Uses[%s]", var.type(), var.identifier(), proc.block().countUsages(var.identifier())));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
