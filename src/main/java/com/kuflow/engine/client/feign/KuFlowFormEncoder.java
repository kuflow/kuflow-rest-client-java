/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;

public class KuFlowFormEncoder extends FormEncoder {

    public KuFlowFormEncoder(ObjectMapper objectMapper, Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor contentProcessor = (MultipartFormContentProcessor) getContentProcessor(ContentType.MULTIPART);
        contentProcessor.addFirstWriter(new JacksonPojoWriter(objectMapper));
    }
}
