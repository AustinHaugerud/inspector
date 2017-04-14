package org.inspector;

import java.io.InputStream;

public interface ISourceParser {

    SourceStructure parseSource(InputStream source);

}
