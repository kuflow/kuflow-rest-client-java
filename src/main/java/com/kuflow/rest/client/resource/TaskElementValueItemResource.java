/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kuflow.rest.client.util.CastUtils;
import java.io.Serializable;
import java.util.Map;

@JsonSerialize(using = TaskElementValueItemResourceSerializer.class)
@JsonDeserialize(using = TaskElementValueItemResourceDeserializer.class)
public class TaskElementValueItemResource {

    private Object value;

    private TaskElementValueItemResource(Object value) {
        this.value = value;
    }

    public static TaskElementValueItemResource of(String value) {
        return new TaskElementValueItemResource(value);
    }

    public static TaskElementValueItemResource of(Double value) {
        return new TaskElementValueItemResource(value);
    }

    public static TaskElementValueItemResource of(Map<String, Serializable> value) {
        return new TaskElementValueItemResource(value);
    }

    Object getRawValue() {
        return this.value;
    }

    String getValueAsString() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof String) {
            return (String) this.value;
        }

        return String.valueOf(this.value);
    }

    Double getValueAsDouble() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof Number) {
            return ((Number) this.value).doubleValue();
        }

        return Double.valueOf(this.value.toString());
    }

    Map<String, Serializable> getValueAsMap() {
        if (this.value == null) {
            return null;
        }

        return CastUtils.cast(this.value);
    }
}
