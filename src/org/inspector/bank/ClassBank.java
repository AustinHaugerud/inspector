package org.inspector.bank;

import org.inspector.items.Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassBank implements IClassBankReadable {

    private ArrayList<Class> _classes = new ArrayList<>();

    public void addClass(Class classDef)
    {
        _classes.add(classDef);
    }

    @Override
    public List<Class> getClasses() {
        return Collections.unmodifiableList(_classes);
    }
}
