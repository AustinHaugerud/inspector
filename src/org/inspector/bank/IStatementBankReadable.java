package org.inspector.bank;

import org.inspector.items.Statement;

import java.util.List;

public interface IStatementBankReadable {
    List<Statement> getStatements();
}
