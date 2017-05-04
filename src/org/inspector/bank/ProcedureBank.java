package org.inspector.bank;

import org.inspector.items.ImmutableVariable;
import org.inspector.items.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureBank implements IProcedureBankReadable {

    public VariableBank _variables;

    ArrayList<Procedure> _procedures;

    @Override
    public List<Procedure> getProcedures() {
        return null;
    }

    int countUses(ImmutableVariable variable) {
        int uses = 0;
        for(Procedure proc : _procedures)
        {
            uses += proc.countUsages(variable);
        }
        return uses;
    }
}
