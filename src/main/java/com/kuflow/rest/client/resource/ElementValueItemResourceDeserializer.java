/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class ElementValueItemResourceDeserializer extends StdDeserializer<ElementValueItemResource> {

    public ElementValueItemResourceDeserializer() {
        this(null);
    }

    public ElementValueItemResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ElementValueItemResource deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isTextual()) {
            return ElementValueItemResource.of(node.asText());
        } else if (node.isFloatingPointNumber()) {
            return ElementValueItemResource.of(node.asDouble());
        } else if (node.isIntegralNumber()) {
            return ElementValueItemResource.of(node.asInt());
        } else if (node.isObject()) {
            // return ElementValueItemResource.of(node.asInt());
        } else if (node.isEmpty()) {
            return null;
        }

        throw new JsonMappingException(jsonParser, String.format("Unable to deserialize a %s", ElementValueItemResource.class.getName()));
    }
}
