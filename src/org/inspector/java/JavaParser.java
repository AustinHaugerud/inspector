package org.inspector.java;

import org.apache.commons.io.IOUtils;
import org.inspector.ISourceParser;
import org.inspector.SourceStructure;
import org.inspector.bank.ClassBank;
import org.inspector.bank.DependencyBank;
import org.inspector.bank.ProcedureBank;
import org.inspector.bank.VariableBank;
import org.inspector.items.Class;
import org.inspector.items.ImmutableVariable;

import java.io.InputStream;
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
            String match = matcher.group();
            result.addDependency(match);
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

        result._procedureBank = extractMethods(culledDefinition);
        result._variableBank = extractVariables(culledDefinition);

        return result;
    }

    private ProcedureBank extractMethods(String source)
    {

        String methodRegex = "((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*\\{?[^\\}]*\\}?";

        Pattern pattern = Pattern.compile(methodRegex);

        Matcher matcher = pattern.matcher(source);

        ProcedureBank procedures = new ProcedureBank();

        while(matcher.find())
        {
            String methodSource = matcher.group();
            procedures.addProcedure(MethodParser.parse(methodSource));
        }

        return procedures;
    }

    private VariableBank extractVariables(String source)
    {

        String varRegex = ".+\\s(.+?)(;|=)";

        Pattern pattern = Pattern.compile(varRegex);

        Matcher matcher = pattern.matcher(source);

        VariableBank result = new VariableBank();

        while(matcher.find())
        {
            String varSource = matcher.group(0);

            ImmutableVariable var = VariableParser.parseVariable(varSource);

            result.setVariable(var.type(), var.identifier(), var.value().orElseGet(null));
        }

        return result;
    }

    private SourceStructure parseBody(String body) {
        SourceStructure result;

        String packageDeclaration = extractPackageDeclaration(body);
        DependencyBank dependencyBank = extractImports(body);
        Class classDefinition = extractClassDefinition(body);
        ClassBank classBank = new ClassBank();
        classBank.addClass(classDefinition);

        result = new SourceStructure(classBank, dependencyBank, null, null);

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
