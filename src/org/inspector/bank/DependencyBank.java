package org.inspector.bank;

import org.inspector.items.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyBank implements IDependencyBankReadable {

    private ArrayList<Dependency> _dependencies = new ArrayList<>();

    @Override
    public List<Dependency> getDependencies() {
        return _dependencies;
    }

    public void addDependency(String source)
    {
        Dependency dependency = new Dependency();
        dependency._name = source.split(" ")[1];
        _dependencies.add(dependency);
    }
}
