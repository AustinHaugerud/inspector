package org.inspector;

import org.inspector.bank.ClassBank;
import org.inspector.bank.DependencyBank;
import org.inspector.items.Procedure;
import org.inspector.bank.VariableBank;

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
    private Procedure _procedureBank  = null;

    /**
     * Global variables, again not necessarily valid
     * as a language like Java can't have variables
     * outside of a class definition
     */
    private VariableBank   _variableBank   = null;

}
