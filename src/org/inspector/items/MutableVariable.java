package org.inspector.items;

import java.util.Optional;

public class MutableVariable implements VariableAccess {

    private Variable _variable;

    public MutableVariable(String type, String identifier, String value)
    {

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

    public void setType(String type)
    {
        _variable._type = type;
    }

    public void setIdentifier(String identifier)
    {
        _variable._identifier = identifier;
    }

    public void setValue(String value)
    {
        _variable._value = value;
    }

    public ImmutableVariable asImmutable()
    {
        return new ImmutableVariable(type(), identifier(), value().orElse(null));
    }

}
