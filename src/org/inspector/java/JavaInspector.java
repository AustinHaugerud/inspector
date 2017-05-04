package org.inspector.java;

import org.inspector.ISourceInspector;
import org.inspector.ResultAttributes;
import org.inspector.SourceStructure;
import org.inspector.bank.IClassBankReadable;
import org.inspector.bank.IDependencyBankReadable;
import org.inspector.bank.IProcedureBankReadable;
import org.inspector.bank.IStatementBankReadable;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.inspector.items.*;
import java.util.HashMap;

public class JavaInspector implements ISourceInspector
{


    @Override
    public int inspectSource(SourceStructure source1, SourceStructure source2)
    {


        ResultAttributes result = null;
        return 0;
    }

    private boolean dependencyIsShared(Dependency dependency, IDependencyBankReadable dependBank)
    {
        for(Dependency otherDependency : dependBank.getDependencies())
        {
            if(otherDependency.equals(dependency))
            {
                return true;
            }
        }
        return false;
    }

    private int dependencyIntersectionWeight(SourceStructure source1, SourceStructure source2)
    {
        IDependencyBankReadable dependencyBankOne = source1.getDependencyBank();
        IDependencyBankReadable dependencyBankTwo = source2.getDependencyBank();

        int numShared = 0;
        for(Dependency dependency : dependencyBankOne.getDependencies())
        {
            if(dependencyIsShared(dependency, dependencyBankTwo))
            {
                numShared++;
            }
        }
        return numShared;
    }

    private boolean className(SourceStructure source1, SourceStructure source2)
    {
        IClassBankReadable classOne = (IClassBankReadable) source1.getClassBank();
        IClassBankReadable classTwo = (IClassBankReadable) source2.getClassBank();

        return classOne.name().equals(classTwo.name());
    }

    int compareClassMembers(SourceStructure source1, SourceStructure source2)
    {
        org.inspector.items.Class class1 = source1.getClassBank().getClasses().get(0);
        org.inspector.items.Class class2 = source2.getClassBank().getClasses().get(0);

        HashMap<String, Integer> _nameTable = new HashMap<>();

        int numCollisions = 0;

        for(ImmutableVariable var : class1._variables.getImmutableVariables())
        {
            _nameTable.put(var.identifier(), 1);
        }

        for(ImmutableVariable var : class2._variables.getImmutableVariables())
        {
            if(_nameTable.containsKey(var.identifier()))
            {
                numCollisions++;
            }
        }

        return numCollisions;
    }

    int compareOccurences(org.inspector.items.Class class1, org.inspector.items.Class class2)
    {
        HashMap<String, Integer> occurencesClass1 = new HashMap<>();
        HashMap<String, Integer> occurencesClass2 = new HashMap<>();

        for(ImmutableVariable var : class1._variables.getImmutableVariables())
        {
            occurencesClass1.put(var.identifier(), countOccurences(class1, var));
        }

        for(ImmutableVariable var : class2._variables.getImmutableVariables())
        {
            occurencesClass2.put(var.identifier(), countOccurences(class2, var));
        }

        int equalOccurencesCount = 0;

        for(ImmutableVariable var : class1._variables.getImmutableVariables())
        {
            if(occurencesClass2.containsKey(var.identifier()))
            {
                if(occurencesClass1.get(var.identifier()).intValue() == occurencesClass2.get(var.identifier()).intValue())
                {
                    equalOccurencesCount++;
                }
            }
        }

        for(ImmutableVariable var : class2._variables.getImmutableVariables())
        {
            if(occurencesClass2.containsKey(var.identifier()))
            {
                if(occurencesClass2.get(var.identifier()).intValue() == occurencesClass2.get(var.identifier()).intValue())
                {
                    equalOccurencesCount++;
                }
            }
        }

        return equalOccurencesCount;
    }

    int countOccurences(org.inspector.items.Class targetClass, ImmutableVariable var)
    {
        int usages = 0;

        for(Procedure proc : targetClass._procedures.getProcedures())
        {
            usages += countOccurences(proc, var);
        }

        return usages;
    }

    int countOccurences(Procedure procedure, ImmutableVariable var)
    {
        return procedure.countUsages(var);
    }

    private boolean procedureIsShared(Procedure procedure, IProcedureBankReadable procedureBank)
    {
        for(Procedure otherProcedure : procedureBank.getProcedures())
        {
            if(otherProcedure.equals(procedure))
            {
                return true;
            }
        }
        return false;
    }

    private int procedureName(SourceStructure source1, SourceStructure source2)
    {
        IProcedureBankReadable procedureBankOne = source1.getProcedures();
        IProcedureBankReadable procedureBankTwo = source2.getProcedures();

        int numShared = 0;
        for(Procedure procedure : procedureBankOne.getProcedures())
        {
            if(procedureIsShared(procedure, procedureBankTwo))
            {
                numShared++;
            }
        }
        return numShared;
    }

    private int localVariableCollisions(SourceStructure source1, SourceStructure source2)
    {
        IProcedureBankReadable procedureBankOne = source1.getProcedures();
        IProcedureBankReadable procedureBankTwo = source2.getProcedures();

        HashMap<String, Integer> _varTable = new HashMap<>();
        int numCollisions = 0;
        for(Procedure procedure : procedureBankOne.getProcedures())
        {
            Block block = procedure.block();

            for(VariableAccess var : block.getVariableBank().getVariables())
            {
                _varTable.put(var.identifier(), 1);
            }
        }

        for(Procedure procedure : procedureBankTwo.getProcedures())
        {
            Block block = procedure.block();

            for(VariableAccess var : block.getVariableBank().getVariables())
            {
                if(_varTable.containsKey(var.identifier()))
                {
                    numCollisions++;
                }
            }
        }
        return numCollisions;
    }
}

