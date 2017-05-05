package org.inspector.bank;

import org.inspector.items.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureBank implements IProcedureBankReadable {

    private ArrayList<Procedure> _procedures = new ArrayList<>();

    @Override
    public List<Procedure> getProcedures() {
        return _procedures;
    }

    public void addProcedure(Procedure procedure)
    {
        _procedures.add(procedure);
    }
}
