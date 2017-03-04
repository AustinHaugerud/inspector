package org.inspector.items;

import java.util.Optional;

public class ImmutableVariable implements VariableAccess {

    private final Variable _variable;

    ImmutableVariable(String type_, String identifier_, String value_)
    {
        _variable = new Variable(type_, identifier_, value_);
    }

    public ImmutableVariable(MutableVariable mutable)
    {
        this(mutable.type(), mutable.identifier(), mutable.value().orElse(null));
    }

    public String type()
    {
        return _variable._type;
    }

    public String identifier()
    {
        return _variable._identifier;
    }

    public Optional<String> value()
    {
        return Optional.ofNullable(_variable._value);
    }

}
