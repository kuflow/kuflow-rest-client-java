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

public class ElementValuesResourceDeserializer extends StdDeserializer<ElementValuesResource> {

    private static final TypeReference<List<ElementValueResource>> LIST_TYPE = new TypeReference<List<ElementValueResource>>() {};

    public ElementValuesResourceDeserializer() {
        this(null);
    }

    public ElementValuesResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ElementValuesResource deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonToken currentToken = jsonParser.getCurrentToken();

        if (currentToken == JsonToken.START_OBJECT) {
            ElementValueResource elementValueResource = jsonParser.readValueAs(ElementValueResource.class);
            if (elementValueResource.getValue() == null) {
                return null;
            }

            return new ElementValuesResource(elementValueResource);
        } else if (currentToken == JsonToken.START_ARRAY) {
            List<Object> elementsValueResource = jsonParser.readValueAs(LIST_TYPE);
            return new ElementValuesResource(elementsValueResource);
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }

        throw new JsonMappingException(jsonParser, String.format("Unable to deserialize a %s", ElementValuesResource.class.getName()));
    }
}
