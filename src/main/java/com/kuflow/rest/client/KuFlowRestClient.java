package com.kuflow.rest.client;

import com.azure.core.annotation.ServiceClient;
import com.kuflow.rest.client.implementation.KuFlowClientImpl;

@ServiceClient(builder = KuFlowRestClientBuilder.class)
public class KuFlowRestClient {

    private final PrincipalOperations principalOperations;
    private final AuthenticationOperations authenticationOperations;
    private final ProcessOperations processOperations;
    private final TaskOperations taskOperations;

    public KuFlowRestClient(KuFlowClientImpl client) {
        this.principalOperations = new PrincipalOperations(client.getPrincipalOperations());
        this.authenticationOperations = new AuthenticationOperations(client.getAuthenticationOperations());
        this.processOperations = new ProcessOperations(client.getProcessOperations());
        this.taskOperations = new TaskOperations(client.getTaskOperations());
    }

    public PrincipalOperations getPrincipalOperations() {
        return this.principalOperations;
    }

    public AuthenticationOperations getAuthenticationOperations() {
        return this.authenticationOperations;
    }

    public ProcessOperations getProcessOperations() {
        return this.processOperations;
    }

    public TaskOperations getTaskOperations() {
        return this.taskOperations;
    }

}
