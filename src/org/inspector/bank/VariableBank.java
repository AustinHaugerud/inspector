package org.inspector.bank;

import org.inspector.items.ImmutableVariable;
import org.inspector.items.MutableVariable;
import org.inspector.items.VariableAccess;

import java.util.*;

public class VariableBank implements IVariableBankReadable {

    private ArrayList<MutableVariable> _variables;

    /**
     * Constructors
     */
    public VariableBank()
    {
        _variables = new ArrayList<>();
    }

    /**
     * Register a new variable that is not in the bank
     * @param type The type of the variable
     * @param identifier The name of the variable
     * @param value The value of the variable, null if not specified
     */
    private void registerVariable(String type, String identifier, String value)
    {
        MutableVariable var = new MutableVariable();
        var.setType(type);
        var.setIdentifier(identifier);
        var.setValue(value);
        _variables.add(var);
    }

    /**
     * Determines whether or not the bank has registered the
     * variable of name identifier
     * @param identifier The name of the variable
     * @return Whether or not the variable is registered in
     * the bank
     */
    private Optional<MutableVariable> findVariable(String identifier)
    {
        MutableVariable result = null;
        for(MutableVariable var : _variables)
        {
            if(var.identifier().equals(identifier))
            {
                result = var;
                break;
            }
        }

        return Optional.ofNullable(result);
    }

    /**
     * Get an immutable list of the variables in the bank
     * @return an immutable list of variables in the bank
     */
    public List<ImmutableVariable> getImmutableVariables()
    {
        List<ImmutableVariable> immutables = new ArrayList<ImmutableVariable>(_variables.size());

        _variables.forEach(item -> immutables.add(new ImmutableVariable(item)));

        return immutables;
    }

    /**
     * Set a variable with its type, name, and value
     * @param identifier The type and name of the variable
     * @param value The value of the variable, if the variable
     *              is not assigned, then value is null
     */
    public void setVariable(String type, String identifier, String value)
    {
        Optional<MutableVariable> var = findVariable(identifier);

        if(var.isPresent())
        {
            var.get().setType(type);
            var.get().setValue(value);
        }
        else
        {
            registerVariable(type, identifier, value);
        }
    }

    @Override
    public List<VariableAccess> getVariables() {
        ArrayList<VariableAccess> result = new ArrayList<>();
        result.addAll(_variables);
        return result;
    }
}
