/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;

@JsonDeserialize(using = ProcessElementValueWrapperResourceDeserializer.class)
@JsonSerialize(using = ProcessElementValueWrapperResourceSerializer.class)
public class ProcessElementValueWrapperResource {

    // Only can be: ProcessElementValueResource
    private Object value;

    ProcessElementValueWrapperResource(ProcessElementValueResource value) {
        this.value = value;
    }

    Object getRawValue() {
        return this.value;
    }

    public static ProcessElementValueWrapperResource of(ProcessElementValueResource value) {
        return new ProcessElementValueWrapperResource(value);
    }

    public static ProcessElementValueWrapperResource of(String value) {
        return toProcessElementWrapperResource(value);
    }

    public static ProcessElementValueWrapperResource of(Double value) {
        return toProcessElementWrapperResource(value);
    }

    public static ProcessElementValueWrapperResource of(LocalDate value) {
        return toProcessElementWrapperResource(value);
    }

    private static <T> ProcessElementValueWrapperResource toProcessElementWrapperResource(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        ProcessElementValueResource elementValueResource = toProcessElementValueResource(value);

        return new ProcessElementValueWrapperResource(elementValueResource);
    }

    private static ProcessElementValueResource toProcessElementValueResource(Object value) {
        ProcessElementValueResource elementValueResource = new ProcessElementValueResource();

        if (value instanceof String) {
            elementValueResource.value(ProcessElementValueItemResource.of((String) value));
        } else if (value instanceof Double) {
            elementValueResource.value(ProcessElementValueItemResource.of((Double) value));
        } else if (value instanceof LocalDate) {
            elementValueResource.value(ProcessElementValueItemResource.of((LocalDate) value));
        } else {
            throw new IllegalArgumentException(String.format("Unkown type %s", value.getClass().getName()));
        }

        return elementValueResource;
    }

    public ProcessElementValueResource getValue() {
        if (this.value == null) {
            return null;
        }

        ProcessElementValueResource elementValueResource = (ProcessElementValueResource) this.value;

        return elementValueResource;
    }

    public String getValueAsString() {
        if (this.value == null) {
            return null;
        }

        ProcessElementValueResource elementValueResource = (ProcessElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsString();
    }

    public Double getValueAsDouble() {
        if (this.value == null) {
            return null;
        }

        ProcessElementValueResource elementValueResource = (ProcessElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsDouble();
    }

    public LocalDate getValueAsLocalDate() {
        if (this.value == null) {
            return null;
        }

        ProcessElementValueResource elementValueResource = (ProcessElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsLocalDate();
    }

    public Boolean getValid() {
        if (this.value == null) {
            return null;
        }

        ProcessElementValueResource elementValueResource = (ProcessElementValueResource) this.value;

        return elementValueResource.getValid();
    }

    public ProcessElementValueWrapperResource valid(Boolean value) {
        if (this.value == null) {
            throw new UnsupportedOperationException();
        }

        ProcessElementValueResource elementValueResource = (ProcessElementValueResource) this.value;
        elementValueResource.setValid(value);

        return this;
    }
}
