/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client;

public class KuFlowRestClientException extends RuntimeException {

    private static final long serialVersionUID = -5196736077043846135L;

    public KuFlowRestClientException(String msg) {
        super(msg);
    }

    public KuFlowRestClientException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
