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
import java.util.Map;

public class TaskElementValueItemResourceDeserializer extends StdDeserializer<TaskElementValueItemResource> {

    private static final long serialVersionUID = 6419968088062208795L;

    private static final TypeReference<Map<String, Object>> FORM_TYPE = new TypeReference<Map<String, Object>>() {};

    public TaskElementValueItemResourceDeserializer() {
        this(null);
    }

    public TaskElementValueItemResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TaskElementValueItemResource deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException, JacksonException {
        JsonToken currentToken = jsonParser.getCurrentToken();

        if (currentToken == JsonToken.START_OBJECT) {
            Map<String, Object> form = jsonParser.readValueAs(FORM_TYPE);
            return TaskElementValueItemResource.of(form);
        } else if (currentToken == JsonToken.VALUE_STRING) {
            return TaskElementValueItemResource.of(jsonParser.getText());
        } else if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return TaskElementValueItemResource.of(jsonParser.getDoubleValue());
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }

        throw new JsonMappingException(
            jsonParser,
            String.format("Unable to deserialize a %s", TaskElementValueItemResource.class.getName())
        );
    }
}
