/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client;

import feign.Request;

public class KuFlowRestClientProperties {

    private String endpoint = "https://api.kuflow.com/v1";

    private String applicationId;

    private String token;

    private Level loggerLevel = Level.NONE;

    private long connectTimeoutMillis = new Request.Options().readTimeoutMillis();

    private long readTimeoutMillis = new Request.Options().connectTimeoutMillis();

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Level getLoggerLevel() {
        return this.loggerLevel;
    }

    public void setLoggerLevel(Level loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public long getConnectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(long connectTimeoutMillis) {
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public long getReadTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public void setReadTimeoutMillis(long readTimeoutMillis) {
        this.readTimeoutMillis = readTimeoutMillis;
    }

    /**
     * Controls the level of logging.
     */
    public enum Level {
        /**
         * No logging.
         */
        NONE,
        /**
         * Log only the request method and URL and the response status code and execution time.
         */
        BASIC,
        /**
         * Log the basic information along with request and response headers.
         */
        HEADERS,
        /**
         * Log the headers, body, and metadata for both requests and responses.
         */
        FULL,
    }
}
