/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.rest.client;

import com.azure.core.annotation.ServiceClient;
import com.kuflow.rest.client.implementation.KuFlowClientImpl;
import com.kuflow.rest.client.operations.AuthenticationOperations;
import com.kuflow.rest.client.operations.PrincipalOperations;
import com.kuflow.rest.client.operations.ProcessOperations;
import com.kuflow.rest.client.operations.TaskOperations;

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
        this.taskOperations = new TaskOperations(client);
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
