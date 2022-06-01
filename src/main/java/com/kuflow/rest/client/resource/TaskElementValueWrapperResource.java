/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kuflow.rest.client.util.CastUtils;
import java.io.Serializable;
import java.time.LocalDate;
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
            throw new IllegalArgumentException("Some value is required");
        }

        if (!values.isEmpty()) {
            values
                .stream()
                .filter(Objects::isNull)
                .findAny()
                .ifPresent(v -> {
                    throw new IllegalArgumentException(String.format("Items object type: '%s' is unsupported", v.getClass()));
                });
        }

        this.value = values;
    }

    Object getRawValue() {
        return this.value;
    }

    public static TaskElementValueWrapperResource of(TaskElementValueResource value) {
        return new TaskElementValueWrapperResource(value);
    }

    public static TaskElementValueWrapperResource of(TaskElementValueResource... value) {
        return new TaskElementValueWrapperResource((TaskElementValueResource) Arrays.asList((Object[]) value));
    }

    public static TaskElementValueWrapperResource of(String value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(String... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    public static TaskElementValueWrapperResource of(Double value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(Double... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    public static TaskElementValueWrapperResource of(LocalDate value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(LocalDate... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    public static TaskElementValueWrapperResource of(Map<String, Serializable> value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(List<Map<String, Serializable>> values) {
        return toElementValuesResource(values.toArray());
    }

    public static TaskElementValueWrapperResource of(TaskElementValueDocumentResource value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(TaskElementValueDocumentResource... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    public static TaskElementValueWrapperResource of(TaskElementValuePrincipalResource value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(TaskElementValuePrincipalResource... values) {
        return toElementValuesResource(CastUtils.cast(values));
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
        TaskElementValueResource elementValueResource = new TaskElementValueResource();

        if (value instanceof String) {
            elementValueResource.value(TaskElementValueItemResource.of((String) value));
        } else if (value instanceof Double) {
            elementValueResource.value(TaskElementValueItemResource.of((Double) value));
        } else if (value instanceof LocalDate) {
            elementValueResource.value(TaskElementValueItemResource.of((LocalDate) value));
        } else if (value instanceof Map) {
            Map<String, Serializable> valueMap = CastUtils.cast(value);
            elementValueResource.value(TaskElementValueItemResource.of(valueMap));
        } else if (value instanceof TaskElementValueDocumentResource) {
            elementValueResource.value(TaskElementValueItemResource.of((TaskElementValueDocumentResource) value));
        } else if (value instanceof TaskElementValuePrincipalResource) {
            elementValueResource.value(TaskElementValueItemResource.of((TaskElementValuePrincipalResource) value));
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }

        return elementValueResource;
    }

    public TaskElementValueResource getValueAsTaskElementValue() {
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

    public List<TaskElementValueResource> getValueAsTaskElementValueList() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        if (this.value instanceof TaskElementValueResource) {
            return List.of((TaskElementValueResource) this.value);
        }

        return CastUtils.cast(this.value);
    }

    public String getValueAsString() {
        return this.getValueAs(TaskElementValueItemResource::getValueAsString);
    }

    public List<String> getValueAsStringList() {
        return this.getValuesAs(TaskElementValueItemResource::getValueAsString);
    }

    public Double getValueAsDouble() {
        return this.getValueAs(TaskElementValueItemResource::getValueAsDouble);
    }

    public List<Double> getValueAsDoubleList() {
        return this.getValuesAs(TaskElementValueItemResource::getValueAsDouble);
    }

    public LocalDate getValueAsLocalDate() {
        return this.getValueAs(TaskElementValueItemResource::getValueAsLocalDate);
    }

    public List<LocalDate> getValueAsLocalDateList() {
        return this.getValuesAs(TaskElementValueItemResource::getValueAsLocalDate);
    }

    public Map<String, Serializable> getValueAsMap() {
        return this.getValueAs(TaskElementValueItemResource::getValueAsMap);
    }

    public List<Map<String, Serializable>> getValueAsMapList() {
        return this.getValuesAs(TaskElementValueItemResource::getValueAsMap);
    }

    public TaskElementValueDocumentResource getValueAsDocument() {
        return this.getValueAs(TaskElementValueItemResource::getValueAsDocument);
    }

    public List<TaskElementValueDocumentResource> getValueAsDocumentList() {
        return this.getValuesAs(TaskElementValueItemResource::getValueAsDocument);
    }

    public TaskElementValuePrincipalResource getValueAsPrincipal() {
        return this.getValueAs(TaskElementValueItemResource::getValueAsPrincipal);
    }

    public List<TaskElementValuePrincipalResource> getValueAsPrincipalList() {
        return this.getValuesAs(TaskElementValueItemResource::getValueAsPrincipal);
    }

    private <R> R getValueAs(Function<TaskElementValueItemResource, R> cb) {
        TaskElementValueResource value = this.getValueAsTaskElementValue();
        if (value == null || value.getValue() == null) {
            return null;
        }

        return cb.apply(value.getValue());
    }

    private <R> List<R> getValuesAs(final Function<TaskElementValueItemResource, R> cb) {
        List<TaskElementValueResource> values = this.getValueAsTaskElementValueList();

        return values.stream().filter(m -> m.getValue() != null).map(m -> cb.apply(m.getValue())).collect(toList());
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
        if (this.value == null) {
            return null;
        }

        if (!(this.value instanceof Collection)) {
            throw new UnsupportedOperationException("Task element value not is an array, did you mean getValid()");
        }

        List<TaskElementValueResource> values = CastUtils.cast(this.value);

        return values.get(index).getValid();
    }

    public TaskElementValueWrapperResource valid(Boolean value) {
        if (this.value == null) {
            throw new NullPointerException();
        }

        if (this.value instanceof Collection) {
            throw new UnsupportedOperationException("Task element value is an array, did you mean validAt()");
        }

        TaskElementValueResource elementValueResource = (TaskElementValueResource) this.value;
        elementValueResource.setValid(value);

        return this;
    }

    public TaskElementValueWrapperResource validAt(int index, Boolean value) {
        if (this.value == null) {
            throw new NullPointerException();
        }

        if (!(this.value instanceof Collection)) {
            throw new UnsupportedOperationException("Task element value not is an array, did you mean valid()");
        }

        List<TaskElementValueResource> values = CastUtils.cast(this.value);
        values.get(index).setValid(value);

        return this;
    }
}
