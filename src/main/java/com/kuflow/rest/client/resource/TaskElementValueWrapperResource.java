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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonDeserialize(using = TaskElementValueWrapperResourceDeserializer.class)
@JsonSerialize(using = TaskElementValueWrapperResourceSerializer.class)
public class TaskElementValueWrapperResource {

    // Only can be: TaskElementValueResource || ElementValueDocumentResource || List<TaskElementValueResource> || List<ElementValueDocumentResource>
    private Object value;

    TaskElementValueWrapperResource(TaskElementValueResource value) {
        this.value = value;
    }

    TaskElementValueWrapperResource(ElementValueDocumentResource value) {
        this.value = value;
    }

    TaskElementValueWrapperResource(List<Object> values) {
        if (values == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        if (!values.isEmpty()) {
            values
                .stream()
                .filter(v -> !((v instanceof TaskElementValueResource) || (v instanceof ElementValueDocumentResource)))
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
        return new TaskElementValueWrapperResource(Arrays.asList((Object[]) value));
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

    public static TaskElementValueWrapperResource of(Map<String, Serializable> value) {
        return toElementValuesResource(value);
    }

    public static TaskElementValueWrapperResource of(List<Map<String, Serializable>> values) {
        return toElementValuesResource(values.toArray());
    }

    public static TaskElementValueWrapperResource of(ElementValueDocumentResource value) {
        if (value == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        return new TaskElementValueWrapperResource(value);
    }

    public static TaskElementValueWrapperResource of(ElementValueDocumentResource... values) {
        if (values == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        List<Object> asList = CastUtils.cast(Arrays.asList(values));

        return new TaskElementValueWrapperResource(asList);
    }

    private static <T> TaskElementValueWrapperResource toElementValuesResource(Object value) {
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

        List<Object> elementValues = Stream
            .of(values)
            .map(TaskElementValueWrapperResource::toElementValueResource)
            .collect(Collectors.toList());

        return new TaskElementValueWrapperResource(elementValues);
    }

    private static TaskElementValueResource toElementValueResource(Object value) {
        TaskElementValueResource elementValueResource = new TaskElementValueResource();

        if (value instanceof String) {
            elementValueResource.value(TaskElementValueItemResource.of((String) value));
        } else if (value instanceof Double) {
            elementValueResource.value(TaskElementValueItemResource.of((Double) value));
        } else if (value instanceof Map) {
            Map<String, Serializable> valueMap = CastUtils.cast(value);
            elementValueResource.value(TaskElementValueItemResource.of(valueMap));
        } else {
            throw new IllegalArgumentException(String.format("Unkown type %s", value.getClass().getName()));
        }

        return elementValueResource;
    }

    public TaskElementValueResource getValue() {
        if (this.value == null) {
            return null;
        }

        TaskElementValueResource elementValueResource = (TaskElementValueResource) this.value;

        return elementValueResource;
    }

    public List<TaskElementValueResource> getValues() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        return CastUtils.cast(this.value);
    }

    public ElementValueDocumentResource getValueAsDocument() {
        if (this.value == null) {
            return null;
        }

        return (ElementValueDocumentResource) this.value;
    }

    public List<ElementValueDocumentResource> getValuesAsDocument() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        return CastUtils.cast(this.value);
    }

    public String getValueAsString() {
        if (this.value == null) {
            return null;
        }

        TaskElementValueResource elementValueResource = (TaskElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsString();
    }

    public List<String> getValuesAsString() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        List<TaskElementValueResource> values = CastUtils.cast(this.value);

        return values.stream().map(m -> m.getValue().getValueAsString()).collect(Collectors.toList());
    }

    public Double getValueAsDouble() {
        if (this.value == null) {
            return null;
        }

        TaskElementValueResource elementValueResource = (TaskElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsDouble();
    }

    public List<Double> getValuesAsDouble() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        List<TaskElementValueResource> values = CastUtils.cast(this.value);

        return values.stream().map(m -> m.getValue().getValueAsDouble()).collect(Collectors.toList());
    }

    public Map<String, Serializable> getValueAsMap() {
        if (this.value == null) {
            return null;
        }

        TaskElementValueResource elementValueResource = (TaskElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsMap();
    }

    public List<Map<String, Serializable>> getValuesAsMap() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        List<TaskElementValueResource> values = CastUtils.cast(this.value);

        return values.stream().map(m -> m.getValue().getValueAsMap()).collect(Collectors.toList());
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
