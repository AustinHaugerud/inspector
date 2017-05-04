package org.inspector.bank;

import org.inspector.items.Dependency;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface IDependencyBankReadable {
    List<Dependency> getDependencies();
}

