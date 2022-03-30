/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.util.List;

public class TaskElementValueWrapperResourceDeserializer extends StdDeserializer<TaskElementValueWrapperResource> {

    private static final long serialVersionUID = 731376553939083705L;

    private static final TypeReference<List<TaskElementValueResource>> LIST_TASK_ELEMENT_VALUE_TYPE = new TypeReference<List<TaskElementValueResource>>() {};

    private static final TypeReference<List<ElementValueDocumentResource>> LIST__ELEMENT_VALUE_DOCUMENT_TYPE = new TypeReference<List<ElementValueDocumentResource>>() {};

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

    private TaskElementValueWrapperResource deserializeMultipleNode(JsonParser jsonParser) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode jsonNode = jsonParser.readValueAsTree();

        if (jsonNode == null || jsonNode.isEmpty()) {
            return null;
        }

        jsonParser = jsonNode.traverse(codec);
        ArrayNode arrayField = (ArrayNode) jsonNode;
        if (arrayField.isEmpty()) {
            return null;
        }

        JsonNode testNode = arrayField.iterator().next();
        if (this.isElementValueDocumentResource(testNode)) {
            List<Object> elementsValueResource = jsonParser.readValueAs(LIST__ELEMENT_VALUE_DOCUMENT_TYPE);
            return new TaskElementValueWrapperResource(elementsValueResource);
        } else {
            List<Object> elementsValueResource = jsonParser.readValueAs(LIST_TASK_ELEMENT_VALUE_TYPE);
            return new TaskElementValueWrapperResource(elementsValueResource);
        }
    }

    private TaskElementValueWrapperResource deserializeSingleNode(JsonParser jsonParser) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode jsonNode = jsonParser.readValueAsTree();

        if (jsonNode == null || jsonNode.isEmpty()) {
            return null;
        }

        jsonParser = jsonNode.traverse(codec);

        if (this.isElementValueDocumentResource(jsonNode)) {
            return this.deserializeElementValueDocument(jsonParser);
        } else {
            return this.deserializeTaskElementValue(jsonParser);
        }
    }

    private TaskElementValueWrapperResource deserializeTaskElementValue(JsonParser jsonParser) throws IOException {
        TaskElementValueResource elementValueResource = jsonParser.readValueAs(TaskElementValueResource.class);
        if (elementValueResource.getValue() == null) {
            return null;
        }
        return new TaskElementValueWrapperResource(elementValueResource);
    }

    private TaskElementValueWrapperResource deserializeElementValueDocument(JsonParser jsonParser) throws IOException {
        ElementValueDocumentResource elementValueResource = jsonParser.readValueAs(ElementValueDocumentResource.class);
        return new TaskElementValueWrapperResource(elementValueResource);
    }

    private boolean isElementValueDocumentResource(JsonNode jsonNode) {
        return jsonNode.findValue("value") == null;
    }
}
