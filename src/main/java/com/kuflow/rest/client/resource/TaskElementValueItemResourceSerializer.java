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

public class TaskElementValueItemResourceSerializer extends StdSerializer<TaskElementValueItemResource> {

    private static final long serialVersionUID = -8147113248371190505L;

    protected TaskElementValueItemResourceSerializer(Class<TaskElementValueItemResource> t) {
        super(t);
    }

    protected TaskElementValueItemResourceSerializer() {
        this(null);
    }

    @Override
    public void serialize(TaskElementValueItemResource value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            return;
        }

        gen.writeObject(value.getRawValue());
    }
}
