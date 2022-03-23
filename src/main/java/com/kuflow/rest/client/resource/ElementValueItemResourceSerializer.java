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

public class ElementValueItemResourceSerializer extends StdSerializer<ElementValueItemResource> {

    protected ElementValueItemResourceSerializer(Class<ElementValueItemResource> t) {
        super(t);
    }

    protected ElementValueItemResourceSerializer() {
        this(null);
    }

    @Override
    public void serialize(ElementValueItemResource value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            return;
        }

        gen.writeObject(value.getRawValue());
    }
}
