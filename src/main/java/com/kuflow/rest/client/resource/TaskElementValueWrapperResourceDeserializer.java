/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.List;

public class TaskElementValueWrapperResourceDeserializer extends StdDeserializer<TaskElementValueWrapperResource> {

    private static final long serialVersionUID = 731376553939083705L;

    private static final TypeReference<List<TaskElementValueResource>> LIST_TASK_ELEMENT_VALUE_TYPE = new TypeReference<>() {};

    public TaskElementValueWrapperResourceDeserializer() {
        this(null);
    }

    public TaskElementValueWrapperResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TaskElementValueWrapperResource deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();

        if (currentToken == JsonToken.START_OBJECT) {
            return this.deserializeSingleNode(jsonParser);
        } else if (currentToken == JsonToken.START_ARRAY) {
            return this.deserializeMultipleNode(jsonParser);
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }

        throw new JsonMappingException(
            jsonParser,
            String.format("Unable to deserialize a %s", TaskElementValueWrapperResource.class.getName())
        );
    }

    private TaskElementValueWrapperResource deserializeSingleNode(JsonParser jsonParser) throws IOException {
        TaskElementValueResource elementValueResource = jsonParser.readValueAs(TaskElementValueResource.class);
        if (elementValueResource.getValue() == null) {
            return null;
        }

        return new TaskElementValueWrapperResource(elementValueResource);
    }

    private TaskElementValueWrapperResource deserializeMultipleNode(JsonParser jsonParser) throws IOException {
        List<TaskElementValueResource> elementsValueResource = jsonParser.readValueAs(LIST_TASK_ELEMENT_VALUE_TYPE);
        if (elementsValueResource.isEmpty()) {
            return null;
        }

        return new TaskElementValueWrapperResource(elementsValueResource);
    }
}
