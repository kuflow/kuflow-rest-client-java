/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.client.KuFlowRestClientException;
import feign.codec.EncodeException;
import feign.form.multipart.AbstractWriter;
import feign.form.multipart.Output;

public class JacksonPojoWriter extends AbstractWriter {

    private static final String CRLF = "\r\n";

    private final ObjectMapper objectMapper;

    public JacksonPojoWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isApplicable(Object object) {
        return this.isUserPojo(object);
    }

    @Override
    protected void write(Output output, String key, Object value) throws EncodeException {
        String body = this.writeValueAsString(value);

        output.write("Content-Disposition: form-data; name=\"" + key + '"' + CRLF);
        output.write("Content-Type: application/json; charset=" + output.getCharset().name() + CRLF + CRLF);
        output.write(body);
    }

    private String writeValueAsString(Object value) {
        String data;
        try {
            data = this.objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new KuFlowRestClientException("Unable to marshall", e);
        }
        return data;
    }

    private boolean isUserPojo(Object object) {
        Class<?> type = object.getClass();
        Package typePackage = type.getPackage();
        return (typePackage != null && typePackage.getName().startsWith("com.kuflow."));
    }
}
