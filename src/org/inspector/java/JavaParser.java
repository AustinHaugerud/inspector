package org.inspector.java;

import org.apache.commons.io.IOUtils;
import org.inspector.ISourceParser;
import org.inspector.SourceStructure;
import org.inspector.bank.DependencyBank;
import org.inspector.items.Class;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaParser implements ISourceParser {

    private static final String STATEMENT_DELIMITER = ";";

    private String extractPackageDeclaration(String source)
    {
        int packageStatementIndex = source.indexOf("package");
        int packageStatementEndIndex = source.indexOf(";");

        return source.substring(packageStatementIndex, packageStatementEndIndex);
    }

    private DependencyBank extractImports(String source)
    {
        DependencyBank result = new DependencyBank();

        final String regexString =
                Pattern.quote("import") + "(.*?)" + Pattern.quote(";");

        Pattern pattern = Pattern.compile(regexString);

        Matcher matcher = pattern.matcher(source);

        while(matcher.find())
        {
            result.addDependency(matcher.group(1));
        }

        return result;
    }

    private Class extractClassDefinition(String source)
    {
        Class result = new Class();
        int defintionStartIndex = source.indexOf("{");
        int defintionEndIndex = source.lastIndexOf("}");
        int classStartIndex = source.indexOf("class");

        String culledDefinition = source.substring(defintionStartIndex - 1, defintionEndIndex);
        String info = source.substring(classStartIndex, defintionStartIndex);

        String infoParts[] = info.split(" ");
        result._name = infoParts[1];
    }

    private SourceStructure parseBody(String body) {
        SourceStructure result = new SourceStructure();

        String packageDeclaration = extractPackageDeclaration(body);
        DependencyBank dependencyBank = extractImports(body);
        Class classDefinition = extractClassDefinition(body);

        classDefinition.

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
