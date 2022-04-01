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

public class ProcessElementValueWrapperResourceSerializer extends StdSerializer<ProcessElementValueWrapperResource> {

    private static final long serialVersionUID = 7098375548656535817L;

    protected ProcessElementValueWrapperResourceSerializer(Class<ProcessElementValueWrapperResource> t) {
        super(t);
    }

    protected ProcessElementValueWrapperResourceSerializer() {
        this(null);
    }

    @Override
    public void serialize(ProcessElementValueWrapperResource source, JsonGenerator jsonGenerator, SerializerProvider provider)
        throws IOException {
        if (source == null) {
            return;
        }

        jsonGenerator.writeObject(source.getRawValue());
    }
}
