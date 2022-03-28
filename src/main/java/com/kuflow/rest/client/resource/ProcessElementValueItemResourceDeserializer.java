/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class ProcessElementValueItemResourceDeserializer extends StdDeserializer<ProcessElementValueItemResource> {

    private static final long serialVersionUID = 4359409658609767631L;

    public ProcessElementValueItemResourceDeserializer() {
        this(null);
    }

    public ProcessElementValueItemResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProcessElementValueItemResource deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException, JacksonException {
        JsonToken currentToken = jsonParser.getCurrentToken();

        if (currentToken == JsonToken.VALUE_STRING) {
            return ProcessElementValueItemResource.of(jsonParser.getText());
        } else if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return ProcessElementValueItemResource.of(jsonParser.getDoubleValue());
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }

        throw new JsonMappingException(
            jsonParser,
            String.format("Unable to deserialize a %s", ProcessElementValueItemResource.class.getName())
        );
    }
}
