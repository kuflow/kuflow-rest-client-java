/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ElementValueItemResourceSerializer.class)
@JsonDeserialize(using = ElementValueItemResourceDeserializer.class)
public class ElementValueItemResource {

    private Object value;

    private ElementValueItemResource(Object value) {
        this.value = value;
    }

    static ElementValueItemResource of(String value) {
        return new ElementValueItemResource(value);
    }

    static ElementValueItemResource of(Double value) {
        return new ElementValueItemResource(value);
    }

    static ElementValueItemResource of(Integer value) {
        return new ElementValueItemResource(value);
    }

    Object getRawValue() {
        return this.value;
    }

    String getValueAsString() {
        if (this.value == null) {
            return null;
        }

        return String.class.cast(this.value);
    }

    Double getValueAsDouble() {
        if (this.value == null) {
            return null;
        }

        return Double.class.cast(this.value);
    }

    Integer getValueAsInteger() {
        if (this.value == null) {
            return null;
        }

        return Integer.class.cast(this.value);
    }
}
