/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Collection;

public class ElementValueWrapperResourceSerializer extends StdSerializer<ElementValueWrapperResource> {

    protected ElementValueWrapperResourceSerializer(Class<ElementValueWrapperResource> t) {
        super(t);
    }

    protected ElementValueWrapperResourceSerializer() {
        this(null);
    }

    @Override
    public void serialize(ElementValueWrapperResource source, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (source == null) {
            return;
        }

        Object rawValue = source.getRawValue();
        if (rawValue instanceof Collection<?>) {
            @SuppressWarnings("unchecked")
            Collection<Object> values = (Collection<Object>) rawValue;

            jsonGenerator.writeStartArray();
            for (Object value : values) {
                jsonGenerator.writeObject(value);
            }
            jsonGenerator.writeEndArray();
        } else {
            jsonGenerator.writeObject(source.getRawValue());
        }
    }
}
