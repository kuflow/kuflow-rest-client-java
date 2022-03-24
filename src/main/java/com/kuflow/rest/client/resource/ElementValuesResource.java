/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kuflow.rest.client.util.CastUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonDeserialize(using = ElementValuesResourceDeserializer.class)
@JsonSerialize(using = ElementValuesResourceSerializer.class)
public class ElementValuesResource {

    // Only can be: ElementValueResource || ElementValueDocumentResource || List<ElementValueResource> || List<ElementValueDocumentResource>
    private Object value;

    ElementValuesResource(ElementValueResource value) {
        this.value = value;
    }

    ElementValuesResource(ElementValueDocumentResource value) {
        this.value = value;
    }

    ElementValuesResource(List<Object> values) {
        if (values == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        if (!values.isEmpty()) {
            values
                .stream()
                .filter(v -> !((v instanceof ElementValueResource) || (v instanceof ElementValueDocumentResource)))
                .findAny()
                .ifPresent(v -> {
                    throw new IllegalArgumentException("Items object type is unsupported");
                });
        }

        this.value = values;
    }

    Object getRawValue() {
        return this.value;
    }

    public static ElementValuesResource of(ElementValueDocumentResource value) {
        if (value == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        return new ElementValuesResource(value);
    }

    public static ElementValuesResource of(ElementValueDocumentResource... values) {
        if (values == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        List<Object> asList = CastUtils.cast(Arrays.asList(values));

        return new ElementValuesResource(asList);
    }

    public static ElementValuesResource of(String value) {
        return toElementValuesResource(value);
    }

    public static ElementValuesResource of(String... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    public static ElementValuesResource of(Integer value) {
        return toElementValuesResource(value);
    }

    public static ElementValuesResource of(Integer... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    public static ElementValuesResource of(Double value) {
        return toElementValuesResource(value);
    }

    public static ElementValuesResource of(Double... values) {
        return toElementValuesResource(CastUtils.cast(values));
    }

    private static <T> ElementValuesResource toElementValuesResource(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        ElementValueResource elementValueResource = toElementValueResource(value);

        return new ElementValuesResource(elementValueResource);
    }

    private static ElementValuesResource toElementValuesResource(Object... values) {
        if (values == null) {
            throw new IllegalArgumentException("Some value is required");
        }

        List<Object> elementValues = Stream.of(values).map(ElementValuesResource::toElementValueResource).collect(Collectors.toList());

        return new ElementValuesResource(elementValues);
    }

    private static ElementValueResource toElementValueResource(Object value) {
        ElementValueResource elementValueResource = new ElementValueResource();

        if (value instanceof String) {
            elementValueResource.value(ElementValueItemResource.of((String) value));
        } else if (value instanceof Integer) {
            elementValueResource.value(ElementValueItemResource.of((Integer) value));
        } else if (value instanceof Double) {
            elementValueResource.value(ElementValueItemResource.of((Double) value));
        } else {
            throw new IllegalArgumentException(String.format("Unkown type %s", value.getClass().getName()));
        }

        return elementValueResource;
    }

    public ElementValueResource getValue() {
        if (this.value == null) {
            return null;
        }

        ElementValueResource elementValueResource = (ElementValueResource) this.value;

        return elementValueResource;
    }

    public List<ElementValueResource> getValues() {
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

        ElementValueResource elementValueResource = (ElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsString();
    }

    public List<String> getValuesAsString() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        List<ElementValueResource> values = CastUtils.cast(this.value);

        return values.stream().map(m -> m.getValue().getValueAsString()).collect(Collectors.toList());
    }

    public Integer getValueAsInteger() {
        if (this.value == null) {
            return null;
        }

        ElementValueResource elementValueResource = (ElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsInteger();
    }

    public List<Integer> getValuesAsInteger() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        List<ElementValueResource> values = CastUtils.cast(this.value);

        return values.stream().map(m -> m.getValue().getValueAsInteger()).collect(Collectors.toList());
    }

    public Double getValueAsDouble() {
        if (this.value == null) {
            return null;
        }

        ElementValueResource elementValueResource = (ElementValueResource) this.value;

        return elementValueResource.getValue().getValueAsDouble();
    }

    public List<Double> getValuesAsDouble() {
        if (this.value == null) {
            return Collections.emptyList();
        }

        List<ElementValueResource> values = CastUtils.cast(this.value);

        return values.stream().map(m -> m.getValue().getValueAsDouble()).collect(Collectors.toList());
    }

    public Boolean isValid() {
        if (this.value == null) {
            return null;
        }

        ElementValueResource elementValueResource = (ElementValueResource) this.value;

        return elementValueResource.getValid();
    }

    public ElementValuesResource valid(Boolean value) {
        if (this.value == null) {
            throw new UnsupportedOperationException();
        }

        ElementValueResource elementValueResource = (ElementValueResource) this.value;
        elementValueResource.setValid(value);

        return this;
    }
}
