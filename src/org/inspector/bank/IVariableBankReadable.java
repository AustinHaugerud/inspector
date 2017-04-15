package org.inspector.bank;

import org.inspector.items.VariableAccess;

import java.util.List;

public interface IVariableBankReadable {
    List<VariableAccess> getVariables();
}
