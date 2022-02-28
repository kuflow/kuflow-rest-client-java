/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client;

public class KuFlowClientException extends RuntimeException {

    private static final long serialVersionUID = -5196736077043846135L;

    public KuFlowClientException(String msg) {
        super(msg);
    }

    public KuFlowClientException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
