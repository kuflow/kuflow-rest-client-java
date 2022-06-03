/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kuflow.rest.client.KuFlowRestClientException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

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

    public ProcessElementValueResource getValue() {
        return this.getValueAsProcessElementValue();
    }

    public String getValueAsString() {
        return this.getValueAs(this::getValueAsString);
    }

    public Double getValueAsDouble() {
        return this.getValueAs(this::getValueAsDouble);
    }

    public LocalDate getValueAsLocalDate() {
        return this.getValueAs(this::getValueAsLocalDate);
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

    private static ProcessElementValueWrapperResource toProcessElementWrapperResource(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        ProcessElementValueResource elementValueResource = toProcessElementValueResource(value);

        return new ProcessElementValueWrapperResource(elementValueResource);
    }

    private static ProcessElementValueResource toProcessElementValueResource(Object value) {
        if (value instanceof String) {
            return toProcessElementValueResourceString((String) value);
        } else if (value instanceof Double) {
            return toProcessElementValueResourceNumber((Double) value);
        } else if (value instanceof LocalDate) {
            return toProcessElementValueResourceString(value.toString());
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }
    }

    private static ProcessElementValueStringResource toProcessElementValueResourceString(String value) {
        ProcessElementValueStringResource elementValueResource = new ProcessElementValueStringResource();
        elementValueResource.setType(ProcessElementValueTypeResource.STRING);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private static ProcessElementValueNumberResource toProcessElementValueResourceNumber(Double value) {
        ProcessElementValueNumberResource elementValueResource = new ProcessElementValueNumberResource();
        elementValueResource.setType(ProcessElementValueTypeResource.NUMBER);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private <R> R getValueAs(Function<ProcessElementValueResource, R> cb) {
        ProcessElementValueResource value = this.getValueAsProcessElementValue();
        if (value == null) {
            return null;
        }

        return cb.apply(value);
    }

    private String getValueAsString(ProcessElementValueResource value) {
        if (value.getType().equals(ProcessElementValueTypeResource.STRING) && (value instanceof ProcessElementValueStringResource)) {
            ProcessElementValueStringResource valueString = (ProcessElementValueStringResource) value;

            return valueString.getValue();
        }

        if (value.getType().equals(ProcessElementValueTypeResource.NUMBER) && (value instanceof ProcessElementValueNumberResource)) {
            ProcessElementValueNumberResource valueNumber = (ProcessElementValueNumberResource) value;

            return valueNumber.getValue() != null ? valueNumber.getValue().toString() : null;
        }

        throw new KuFlowRestClientException(String.format("value %s is not a String", value));
    }

    private Double getValueAsDouble(ProcessElementValueResource value) {
        if (value.getType().equals(ProcessElementValueTypeResource.NUMBER) && (value instanceof ProcessElementValueNumberResource)) {
            ProcessElementValueNumberResource valueNumber = (ProcessElementValueNumberResource) value;

            return valueNumber.getValue();
        }

        if (value.getType().equals(ProcessElementValueTypeResource.STRING) && (value instanceof ProcessElementValueStringResource)) {
            ProcessElementValueStringResource valueString = (ProcessElementValueStringResource) value;

            try {
                if (valueString.getValue() == null) {
                    return null;
                }
                return Double.valueOf(valueString.getValue());
            } catch (NumberFormatException e) {
                throw new KuFlowRestClientException(String.format("value %s is not a number", valueString), e);
            }
        }

        throw new KuFlowRestClientException(String.format("value %s is not a Number", value));
    }

    private LocalDate getValueAsLocalDate(ProcessElementValueResource value) {
        String valueString = this.getValueAsString(value);

        if (valueString == null) {
            return null;
        }

        try {
            return LocalDate.parse(valueString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new KuFlowRestClientException(String.format("value %s is not a date", valueString), e);
        }
    }

    private ProcessElementValueResource getValueAsProcessElementValue() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof ProcessElementValueResource) {
            return (ProcessElementValueResource) this.value;
        }

        throw new IllegalArgumentException(String.format("Unknown value type %s", this.value.getClass().getName()));
    }
}
