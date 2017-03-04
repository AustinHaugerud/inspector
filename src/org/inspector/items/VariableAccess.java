package org.inspector.items;

import java.util.Optional;

public interface VariableAccess {

    public String type();
    public String identifier();
    public Optional<String> value();

}
