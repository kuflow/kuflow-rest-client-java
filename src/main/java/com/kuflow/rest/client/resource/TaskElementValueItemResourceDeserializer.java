/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class TaskElementValueItemResourceDeserializer extends StdDeserializer<TaskElementValueItemResource> {

    private static final long serialVersionUID = 6419968088062208795L;

    public TaskElementValueItemResourceDeserializer() {
        this(null);
    }

    public TaskElementValueItemResourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TaskElementValueItemResource deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode jsonNode = jsonParser.readValueAsTree();

        return new TaskElementValueItemResource(codec, jsonNode);
    }
}
