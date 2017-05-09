package org.inspector;

import java.util.List;

public class ResultAttributes {
    public int numSharedDependencies = 0;
    public int numSharedClasses = 0;
    public int numSharedLocalVariables = 0;
    public int numSharedProcedures = 0;
    public int sharedOccurences = 0;
    public List<String> sourceInfo;
}
