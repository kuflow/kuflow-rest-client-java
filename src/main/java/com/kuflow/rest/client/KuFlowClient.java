/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kuflow.rest.client.controller.AuthenticationApi;
import com.kuflow.rest.client.controller.ProcessApi;
import com.kuflow.rest.client.controller.TaskApi;
import com.kuflow.rest.client.feign.KuFlowFormEncoder;
import com.kuflow.rest.client.util.Assert;
import feign.Feign;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.util.concurrent.TimeUnit;

public class KuFlowClient {

    private final AuthenticationApi authenticationApi;

    private final ProcessApi processApi;

    private final TaskApi taskApi;

    public KuFlowClient(KuFlowClientProperties properties) {
        Assert.notNull(properties, "properties is required");
        Assert.notNull(properties.getApplicationId(), "applicationId is required");
        Assert.notNull(properties.getToken(), "token is required");
        Assert.notNull(properties.getEndpoint(), "endpoint is required");
        Assert.notNull(properties.getLoggerLevel(), "loggerLevel is required");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        KuFlowFormEncoder encoder = new KuFlowFormEncoder(objectMapper, new JacksonEncoder(objectMapper));
        JacksonDecoder decoder = new JacksonDecoder(objectMapper);

        BasicAuthRequestInterceptor authRequestInterceptor = new BasicAuthRequestInterceptor(
            properties.getApplicationId(),
            properties.getToken()
        );

        Request.Options options = new Request.Options(
            properties.getConnectTimeoutMillis(),
            TimeUnit.MILLISECONDS,
            properties.getReadTimeoutMillis(),
            TimeUnit.MILLISECONDS,
            false
        );

        this.authenticationApi =
            Feign
                .builder()
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Slf4jLogger(AuthenticationApi.class))
                .logLevel(properties.getLoggerLevel())
                .options(options)
                .requestInterceptor(authRequestInterceptor)
                .target(AuthenticationApi.class, properties.getEndpoint());

        this.processApi =
            Feign
                .builder()
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Slf4jLogger(ProcessApi.class))
                .logLevel(properties.getLoggerLevel())
                .options(options)
                .requestInterceptor(authRequestInterceptor)
                .target(ProcessApi.class, properties.getEndpoint());

        this.taskApi =
            Feign
                .builder()
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Slf4jLogger(TaskApi.class))
                .logLevel(properties.getLoggerLevel())
                .options(options)
                .requestInterceptor(authRequestInterceptor)
                .target(TaskApi.class, properties.getEndpoint());
    }

    public AuthenticationApi getAuthenticationApi() {
        return this.authenticationApi;
    }

    public ProcessApi getProcessApi() {
        return this.processApi;
    }

    public TaskApi getTaskApi() {
        return this.taskApi;
    }
}
