package com.kuflow.rest.client;

public class KuFlowRestClientException extends RuntimeException {

    public KuFlowRestClientException(String message) {
        super(message);
    }

    public KuFlowRestClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
