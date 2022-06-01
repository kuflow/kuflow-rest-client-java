/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kuflow.rest.client.KuFlowRestClientException;
import com.kuflow.rest.client.util.CastUtils;
import com.kuflow.rest.client.util.ThrowingFunction;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

@JsonSerialize(using = TaskElementValueItemResourceSerializer.class)
@JsonDeserialize(using = TaskElementValueItemResourceDeserializer.class)
public class TaskElementValueItemResource {

    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {};

    private Object value;

    private ObjectCodec valueCodec;
    private JsonNode valueJsonNode;

    private TaskElementValueItemResource(Object value) {
        this.value = value;
        this.valueCodec = null;
        this.valueJsonNode = null;
    }

    protected TaskElementValueItemResource(ObjectCodec valueCodec, JsonNode valueJsonNode) {
        this.value = null;
        this.valueCodec = valueCodec;
        this.valueJsonNode = valueJsonNode;
    }

    public static TaskElementValueItemResource of(String value) {
        return new TaskElementValueItemResource(value);
    }

    public static TaskElementValueItemResource of(Double value) {
        return new TaskElementValueItemResource(value);
    }

    public static TaskElementValueItemResource of(LocalDate value) {
        return new TaskElementValueItemResource((value != null) ? value.toString() : null);
    }

    public static TaskElementValueItemResource of(TaskElementValueDocumentResource value) {
        return new TaskElementValueItemResource(value);
    }

    public static TaskElementValueItemResource of(TaskElementValuePrincipalResource value) {
        return new TaskElementValueItemResource(value);
    }

    public static TaskElementValueItemResource of(Map<String, Serializable> value) {
        return new TaskElementValueItemResource(value);
    }

    Object getRawValue() {
        return this.value;
    }

    TreeNode getRawValueTreeNode() {
        return this.valueJsonNode;
    }

    public String getValueAsString() {
        this.tryReadValueAs(String.class);

        if (this.value == null) {
            return null;
        }

        if (this.value instanceof String) {
            return (String) this.value;
        }

        return String.valueOf(this.value);
    }

    public Double getValueAsDouble() {
        this.tryReadValueAsNumber();

        if (this.value == null) {
            return null;
        }

        if (this.value instanceof Number) {
            return ((Number) this.value).doubleValue();
        }

        try {
            return Double.valueOf(this.value.toString());
        } catch (NumberFormatException e) {
            throw new KuFlowRestClientException(String.format("value %s is not a number", this.value), e);
        }
    }

    public Map<String, Serializable> getValueAsMap() {
        this.tryReadValueAs(MAP_TYPE);

        if (this.value == null) {
            return null;
        }

        if (!(this.value instanceof Map)) {
            throw new KuFlowRestClientException(String.format("value %s is not a Map", this.value));
        }

        return CastUtils.cast(this.value);
    }

    public LocalDate getValueAsLocalDate() {
        this.tryReadValueAs(String.class);

        if (this.value == null) {
            return null;
        }

        if (!(this.value instanceof String)) {
            throw new KuFlowRestClientException(String.format("value %s is not a String", this.value));
        }

        try {
            return LocalDate.parse((String) this.value, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new KuFlowRestClientException(String.format("value %s is not a date", this.value), e);
        }
    }

    public TaskElementValueDocumentResource getValueAsDocument() {
        this.tryReadValueAs(TaskElementValueDocumentResource.class);

        if (this.value == null) {
            return null;
        }

        if (!(this.value instanceof TaskElementValueDocumentResource)) {
            throw new KuFlowRestClientException(String.format("value %s is not a document", this.value));
        }

        return CastUtils.cast(this.value);
    }

    public TaskElementValuePrincipalResource getValueAsPrincipal() {
        this.tryReadValueAs(TaskElementValuePrincipalResource.class);

        if (this.value == null) {
            return null;
        }

        if (this.value == null) {
            return null;
        }

        if (!(this.value instanceof TaskElementValuePrincipalResource)) {
            throw new KuFlowRestClientException(String.format("value %s is not a principal", this.value));
        }

        return CastUtils.cast(this.value);
    }

    private <T> void tryReadValueAs(Class<T> clazz) {
        this.tryReadValueAs(jsonParser -> jsonParser.readValueAs(clazz));
    }

    private <T> void tryReadValueAs(TypeReference<T> valueTypeRef) {
        this.tryReadValueAs(jsonParser -> jsonParser.readValueAs(valueTypeRef));
    }

    private <T> void tryReadValueAs(ThrowingFunction<JsonParser, T> callback) {
        if (this.valueJsonNode == null) {
            return;
        }

        try (JsonParser jsonParser = this.valueJsonNode.traverse(this.valueCodec)) {
            this.value = callback.apply(jsonParser);
            this.valueJsonNode = null;
            this.valueCodec = null;
        } catch (Exception e) {
            throw new KuFlowRestClientException(String.format("Error parsing value %s", this.valueJsonNode), e);
        }
    }

    private void tryReadValueAsNumber() {
        if (this.valueJsonNode == null) {
            return;
        }

        if (this.valueJsonNode.isNumber()) {
            this.value = this.valueJsonNode.numberValue();
        } else if (this.valueJsonNode.isTextual()) {
            this.value = this.valueJsonNode.textValue();
        } else {
            throw new KuFlowRestClientException(String.format("value %s is not a number or string", this.valueJsonNode));
        }

        this.valueJsonNode = null;
        this.valueCodec = null;
    }
}
