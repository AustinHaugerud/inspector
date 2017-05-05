package org.inspector;

import org.inspector.bank.*;

public class SourceStructure {

    /**
     * Class definitions in source
     */
    private ClassBank      _classBank      = new ClassBank();

    /**
     * Dependencies in source. This would be an
     * import statement or preprocessor include etc.
     */
    private DependencyBank _dependencyBank = new DependencyBank();

    /**
     * Global functions, not necessarily valid as some
     * languages like Java can't have functions outside
     * of an object
     */
    private ProcedureBank _procedureBank  = new ProcedureBank();

    /**
     * Global variables, again not necessarily valid
     * as a language like Java can't have variables
     * outside of a class definition
     */
    private VariableBank   _variableBank   = new VariableBank();

    public IClassBankReadable getClassBank()
    {
        return _classBank;
    }

    public IDependencyBankReadable getDependencyBank()
    {
        return _dependencyBank;
    }

    public IProcedureBankReadable getProcedures()
    {
        return _procedureBank;
    }

    public IVariableBankReadable getVariables()
    {
        return _variableBank;
    }

    public SourceStructure(ClassBank classBank, DependencyBank dependencyBank, ProcedureBank procedureBank, VariableBank variableBank)
    {
        _classBank = classBank;
        _dependencyBank = dependencyBank;
        _procedureBank = procedureBank;
        _variableBank = variableBank;
    }
}
