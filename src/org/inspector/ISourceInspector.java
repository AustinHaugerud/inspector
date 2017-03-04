package org.inspector;

import java.io.InputStream;

public interface ISourceInspector {

    SourceStructure inspectSource(InputStream input);

}
