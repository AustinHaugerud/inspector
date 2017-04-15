package org.inspector;

import org.inspector.bank.*;

public class SourceStructure {

    /**
     * Class definitions in source
     */
    private ClassBank      _classBank      = null;

    /**
     * Dependencies in source. This would be an
     * import statement or preprocessor include etc.
     */
    private DependencyBank _dependencyBank = null;

    /**
     * Global functions, not necessarily valid as some
     * languages like Java can't have functions outside
     * of an object
     */
    private ProcedureBank _procedureBank  = null;

    /**
     * Global variables, again not necessarily valid
     * as a language like Java can't have variables
     * outside of a class definition
     */
    private VariableBank   _variableBank   = null;

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

}
