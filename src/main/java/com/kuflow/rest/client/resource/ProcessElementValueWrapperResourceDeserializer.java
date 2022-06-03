/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class ProcessElementValueWrapperResourceDeserializer extends StdDeserializer<ProcessElementValueWrapperResource> {

    private static final long serialVersionUID = -2061344403033830505L;

    public ProcessElementValueWrapperResourceDeserializer() {
        this(null);
    }

    public ProcessElementValueWrapperResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProcessElementValueWrapperResource deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();

        if (currentToken == JsonToken.START_OBJECT) {
            ProcessElementValueResource elementValueResource = jsonParser.readValueAs(ProcessElementValueResource.class);

            return new ProcessElementValueWrapperResource(elementValueResource);
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }

        throw new JsonMappingException(
            jsonParser,
            String.format("Unable to deserialize a %s", ProcessElementValueWrapperResource.class.getName())
        );
    }
}
