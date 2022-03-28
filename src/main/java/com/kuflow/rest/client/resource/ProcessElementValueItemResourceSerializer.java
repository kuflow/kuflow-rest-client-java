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

public class ProcessElementValueItemResourceSerializer extends StdSerializer<ProcessElementValueItemResource> {

    protected ProcessElementValueItemResourceSerializer(Class<ProcessElementValueItemResource> t) {
        super(t);
    }

    protected ProcessElementValueItemResourceSerializer() {
        this(null);
    }

    @Override
    public void serialize(ProcessElementValueItemResource value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            return;
        }

        gen.writeObject(value.getRawValue());
    }
}
