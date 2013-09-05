package edu.stanford.bmir.protege.web.client.primitive;

import com.google.common.base.Optional;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 06/01/2013
 */
public interface PrimitiveDataParser {

    void parsePrimitiveData(String content, Optional<String> language, PrimitiveDataParsingContext context, PrimitiveDataParserCallback callback);
}
