/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kuflow.rest.client.KuFlowRestClientException;
import com.kuflow.rest.client.util.CastUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@JsonDeserialize(using = TaskElementValueWrapperResourceDeserializer.class)
@JsonSerialize(using = TaskElementValueWrapperResourceSerializer.class)
public class TaskElementValueWrapperResource {

    // Only can be: TaskElementValueResource || List<TaskElementValueResource>
    private Object value;

    TaskElementValueWrapperResource(TaskElementValueResource value) {
        this.value = value;
    }

    TaskElementValueWrapperResource(List<TaskElementValueResource> values) {
        if (values == null) {
            values = Collections.emptyList();
        }

        values = values.stream().filter(Objects::nonNull).collect(toList());

        this.value = Collections.unmodifiableList(values);
    }

    Object getRawValue() {
        return this.value;
    }

    public static TaskElementValueWrapperResource of(TaskElementValueResource value) {
        return new TaskElementValueWrapperResource(value);
    }

    public static TaskElementValueWrapperResource of(TaskElementValueResource... values) {
        return new TaskElementValueWrapperResource(Arrays.asList(values));
    }

    public static TaskElementValueWrapperResource of(String value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(String... values) {
        return toElementValuesResource((Object[]) values);
    }

    public static TaskElementValueWrapperResource of(Double value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(Double... values) {
        return toElementValuesResource((Object[]) values);
    }

    public static TaskElementValueWrapperResource of(LocalDate value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(LocalDate... values) {
        return toElementValuesResource((Object[]) values);
    }

    public static TaskElementValueWrapperResource of(Map<String, Serializable> value) {
        return toElementValuesResource(value);
    }

    @SafeVarargs
    public static TaskElementValueWrapperResource of(Map<String, Serializable>... values) {
        return toElementValuesResource((Object[]) values);
    }

    public static TaskElementValueWrapperResource of(TaskElementValueDocumentItemResource value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(TaskElementValueDocumentItemResource... values) {
        return toElementValuesResource((Object[]) values);
    }

    public static TaskElementValueWrapperResource of(TaskElementValuePrincipalItemResource value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(TaskElementValuePrincipalItemResource... values) {
        return toElementValuesResource((Object[]) values);
    }

    public static String getValueAsString(TaskElementValueResource value) {
        if (value.getType().equals(TaskElementValueTypeResource.STRING) && (value instanceof TaskElementValueStringResource)) {
            TaskElementValueStringResource valueString = (TaskElementValueStringResource) value;

            return valueString.getValue();
        }

        if (value.getType().equals(TaskElementValueTypeResource.NUMBER) && (value instanceof TaskElementValueNumberResource)) {
            TaskElementValueNumberResource valueNumber = (TaskElementValueNumberResource) value;

            return valueNumber.toString();
        }

        throw new KuFlowRestClientException(String.format("value %s is not a String", value));
    }

    public static Double getValueAsDouble(TaskElementValueResource value) {
        if (value.getType().equals(TaskElementValueTypeResource.NUMBER) && (value instanceof TaskElementValueNumberResource)) {
            TaskElementValueNumberResource valueNumber = (TaskElementValueNumberResource) value;

            return valueNumber.getValue();
        }

        if (value.getType().equals(TaskElementValueTypeResource.STRING) && (value instanceof TaskElementValueStringResource)) {
            TaskElementValueStringResource valueString = (TaskElementValueStringResource) value;

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

    public static LocalDate getValueAsLocalDate(TaskElementValueResource value) {
        String valueString = getValueAsString(value);

        if (valueString == null) {
            return null;
        }

        try {
            return LocalDate.parse(valueString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new KuFlowRestClientException(String.format("value %s is not a date", valueString), e);
        }
    }

    public static Map<String, Serializable> getValueAsMap(TaskElementValueResource value) {
        if (!value.getType().equals(TaskElementValueTypeResource.OBJECT) || !(value instanceof TaskElementValueObjectResource)) {
            throw new KuFlowRestClientException(String.format("value %s is not an Object", value));
        }

        TaskElementValueObjectResource valueObject = (TaskElementValueObjectResource) value;

        if (valueObject.getValue() == null) {
            return null;
        }

        return CastUtils.cast(valueObject.getValue());
    }

    public static TaskElementValueDocumentItemResource getValueAsDocument(TaskElementValueResource value) {
        if (!value.getType().equals(TaskElementValueTypeResource.DOCUMENT) || !(value instanceof TaskElementValueDocumentResource)) {
            throw new KuFlowRestClientException(String.format("value %s is not a Document", value));
        }

        TaskElementValueDocumentResource valueDocument = (TaskElementValueDocumentResource) value;

        return valueDocument.getValue();
    }

    public static TaskElementValuePrincipalItemResource getValueAsPrincipal(TaskElementValueResource value) {
        if (!value.getType().equals(TaskElementValueTypeResource.PRINCIPAL) || !(value instanceof TaskElementValuePrincipalResource)) {
            throw new KuFlowRestClientException(String.format("value %s is not a Principal", value));
        }

        TaskElementValuePrincipalResource valuePrincipal = (TaskElementValuePrincipalResource) value;

        return valuePrincipal.getValue();
    }

    public TaskElementValueResource getValue() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof TaskElementValueResource) {
            return (TaskElementValueResource) this.value;
        }

        Collection<?> collection = (Collection<?>) this.value;
        if (collection.size() == 0) {
            return null;
        }

        return (TaskElementValueResource) collection.iterator().next();
    }

    public List<TaskElementValueResource> getValueList() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        if (this.value instanceof TaskElementValueResource) {
            return List.of((TaskElementValueResource) this.value);
        }

        return CastUtils.cast(this.value);
    }

    public String getValueAsString() {
        return this.getValueAs(TaskElementValueWrapperResource::getValueAsString);
    }

    public List<String> getValueAsStringList() {
        return this.getValuesAs(TaskElementValueWrapperResource::getValueAsString);
    }

    public Double getValueAsDouble() {
        return this.getValueAs(TaskElementValueWrapperResource::getValueAsDouble);
    }

    public List<Double> getValueAsDoubleList() {
        return this.getValuesAs(TaskElementValueWrapperResource::getValueAsDouble);
    }

    public LocalDate getValueAsLocalDate() {
        return this.getValueAs(TaskElementValueWrapperResource::getValueAsLocalDate);
    }

    public List<LocalDate> getValueAsLocalDateList() {
        return this.getValuesAs(TaskElementValueWrapperResource::getValueAsLocalDate);
    }

    public Map<String, Serializable> getValueAsMap() {
        return this.getValueAs(TaskElementValueWrapperResource::getValueAsMap);
    }

    public List<Map<String, Serializable>> getValueAsMapList() {
        return this.getValuesAs(TaskElementValueWrapperResource::getValueAsMap);
    }

    public TaskElementValueDocumentItemResource getValueAsDocument() {
        return this.getValueAs(TaskElementValueWrapperResource::getValueAsDocument);
    }

    public List<TaskElementValueDocumentItemResource> getValueAsDocumentList() {
        return this.getValuesAs(TaskElementValueWrapperResource::getValueAsDocument);
    }

    public TaskElementValuePrincipalItemResource getValueAsPrincipal() {
        return this.getValueAs(TaskElementValueWrapperResource::getValueAsPrincipal);
    }

    public List<TaskElementValuePrincipalItemResource> getValueAsPrincipalList() {
        return this.getValuesAs(TaskElementValueWrapperResource::getValueAsPrincipal);
    }

    public Boolean getValid() {
        if (this.value == null) {
            return null;
        }

        if (this.value instanceof Collection) {
            List<TaskElementValueResource> values = CastUtils.cast(this.value);
            return values.stream().filter(v -> Boolean.FALSE.equals(v.getValid())).findAny().isEmpty();
        }

        TaskElementValueResource elementValueResource = (TaskElementValueResource) this.value;

        return elementValueResource.getValid();
    }

    public Boolean getValidAt(int index) {
        List<TaskElementValueResource> values = this.getValueList();

        return values.get(index).getValid();
    }

    public TaskElementValueWrapperResource valid(Boolean value) {
        List<TaskElementValueResource> values = this.getValueList();
        values.forEach(it -> it.setValid(value));

        return this;
    }

    public TaskElementValueWrapperResource validAt(int index, Boolean value) {
        List<TaskElementValueResource> values = this.getValueList();
        values.get(index).setValid(value);

        return this;
    }

    private static TaskElementValueWrapperResource toElementValuesResource(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        TaskElementValueResource elementValueResource = toElementValueResource(value);

        return new TaskElementValueWrapperResource(elementValueResource);
    }

    private static TaskElementValueWrapperResource toElementValuesResource(Object... values) {
        if (values == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        List<TaskElementValueResource> elementValues = Stream
            .of(values)
            .map(TaskElementValueWrapperResource::toElementValueResource)
            .collect(toList());

        return new TaskElementValueWrapperResource(elementValues);
    }

    private static TaskElementValueResource toElementValueResource(Object value) {
        if (value instanceof String) {
            return toElementValueResourceString((String) value);
        } else if (value instanceof Double) {
            return toElementValueResourceNumber((Double) value);
        } else if (value instanceof LocalDate) {
            return toElementValueResourceString(value.toString());
        } else if (value instanceof Map) {
            return toElementValueResourceObject(CastUtils.cast(value));
        } else if (value instanceof TaskElementValueDocumentItemResource) {
            return toElementValueResourceDocument((TaskElementValueDocumentItemResource) value);
        } else if (value instanceof TaskElementValuePrincipalItemResource) {
            return toElementValueResourcePrincipal((TaskElementValuePrincipalItemResource) value);
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }
    }

    private static TaskElementValueStringResource toElementValueResourceString(String value) {
        TaskElementValueStringResource elementValueResource = new TaskElementValueStringResource();
        elementValueResource.setType(TaskElementValueTypeResource.STRING);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private static TaskElementValueNumberResource toElementValueResourceNumber(Double value) {
        TaskElementValueNumberResource elementValueResource = new TaskElementValueNumberResource();
        elementValueResource.setType(TaskElementValueTypeResource.NUMBER);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private static TaskElementValueObjectResource toElementValueResourceObject(Map<String, Object> value) {
        TaskElementValueObjectResource elementValueResource = new TaskElementValueObjectResource();
        elementValueResource.setType(TaskElementValueTypeResource.OBJECT);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private static TaskElementValueDocumentResource toElementValueResourceDocument(TaskElementValueDocumentItemResource value) {
        TaskElementValueDocumentResource elementValueResource = new TaskElementValueDocumentResource();
        elementValueResource.setType(TaskElementValueTypeResource.DOCUMENT);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private static TaskElementValuePrincipalResource toElementValueResourcePrincipal(TaskElementValuePrincipalItemResource value) {
        TaskElementValuePrincipalResource elementValueResource = new TaskElementValuePrincipalResource();
        elementValueResource.setType(TaskElementValueTypeResource.PRINCIPAL);
        elementValueResource.value(value);

        return elementValueResource;
    }

    private <R> R getValueAs(Function<TaskElementValueResource, R> cb) {
        TaskElementValueResource value = this.getValue();
        if (value == null) {
            return null;
        }

        return cb.apply(value);
    }

    private <R> List<R> getValuesAs(final Function<TaskElementValueResource, R> cb) {
        List<TaskElementValueResource> values = this.getValueList();

        return values.stream().map(cb).collect(toList());
    }
}
