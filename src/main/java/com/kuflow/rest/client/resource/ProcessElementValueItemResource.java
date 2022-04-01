/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonSerialize(using = ProcessElementValueItemResourceSerializer.class)
@JsonDeserialize(using = ProcessElementValueItemResourceDeserializer.class)
public class ProcessElementValueItemResource {

    private Object value;

    private ProcessElementValueItemResource(Object value) {
        this.value = value;
    }

    public static ProcessElementValueItemResource of(String value) {
        return new ProcessElementValueItemResource(value);
    }

    public static ProcessElementValueItemResource of(Double value) {
        return new ProcessElementValueItemResource(value);
    }

    public static ProcessElementValueItemResource of(LocalDate value) {
        return new ProcessElementValueItemResource((value != null) ? value.toString() : null);
    }

    Object getRawValue() {
        return this.value;
    }

    public String getValueAsString() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof String) {
            return (String) this.value;
        }

        return String.valueOf(this.value);
    }

    public Double getValueAsDouble() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof Number) {
            return ((Number) this.value).doubleValue();
        }

        return Double.valueOf(this.value.toString());
    }

    public LocalDate getValueAsLocalDate() {
        if (this.value == null) {
            return null;
        }

        return LocalDate.parse((String) this.value, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
