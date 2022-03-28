/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.List;

public class TaskElementValueWrapperResourceDeserializer extends StdDeserializer<TaskElementValueWrapperResource> {

    private static final TypeReference<List<TaskElementValueResource>> LIST_TYPE = new TypeReference<List<TaskElementValueResource>>() {};

    public TaskElementValueWrapperResourceDeserializer() {
        this(null);
    }

    public TaskElementValueWrapperResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TaskElementValueWrapperResource deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException, JacksonException {
        JsonToken currentToken = jsonParser.getCurrentToken();

        if (currentToken == JsonToken.START_OBJECT) {
            TaskElementValueResource elementValueResource = jsonParser.readValueAs(TaskElementValueResource.class);
            if (elementValueResource.getValue() == null) {
                return null;
            }

            return new TaskElementValueWrapperResource(elementValueResource);
        } else if (currentToken == JsonToken.START_ARRAY) {
            List<Object> elementsValueResource = jsonParser.readValueAs(LIST_TYPE);
            return new TaskElementValueWrapperResource(elementsValueResource);
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }

        throw new JsonMappingException(
            jsonParser,
            String.format("Unable to deserialize a %s", TaskElementValueWrapperResource.class.getName())
        );
    }
}
