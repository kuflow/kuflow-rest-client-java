/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ProcessElementValueItemResourceSerializer.class)
@JsonDeserialize(using = ProcessElementValueItemResourceDeserializer.class)
public class ProcessElementValueItemResource {

    private Object value;

    private ProcessElementValueItemResource(Object value) {
        this.value = value;
    }

    static ProcessElementValueItemResource of(String value) {
        return new ProcessElementValueItemResource(value);
    }

    static ProcessElementValueItemResource of(Double value) {
        return new ProcessElementValueItemResource(value);
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
}
