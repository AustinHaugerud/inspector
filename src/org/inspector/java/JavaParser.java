package org.inspector.java;

import org.apache.commons.io.IOUtils;
import org.inspector.ISourceParser;
import org.inspector.SourceStructure;
import org.inspector.bank.DependencyBank;
import org.inspector.items.Class;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class JavaParser implements ISourceParser {

    private static final String STATEMENT_DELIMITER = ";";

    private String extractPackageDeclaration(ArrayList<String> statements)
    {
        return null;
    }

    private DependencyBank extractImports(ArrayList<String> statements)
    {
        return null;
    }

    private Class extractClassDefinition(ArrayList<String> statements)
    {
        return null;
    }

    private SourceStructure parseBody(String body) {
        SourceStructure result = new SourceStructure();

        ArrayList<String> statements = new ArrayList<>(Arrays.asList(body.split(STATEMENT_DELIMITER)));

        String packageDeclaration = extractPackageDeclaration(statements);
        DependencyBank dependencyBank = extractImports(statements);
        Class classDefinition = extractClassDefinition(statements);

        return result;
    }

    @Override
    public SourceStructure parseSource(InputStream source) {

        SourceStructure result;

        try {
            String sourceBody = IOUtils.toString(source, "UTF-8");
            result = parseBody(sourceBody);
        } catch( final java.io.IOException e) {
            result = null;
        }

        return result;
    }

}
