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
package com.kuflow.rest.client.operations;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.kuflow.rest.client.implementation.AuthenticationOperationsImpl;
import com.kuflow.rest.client.implementation.KuFlowClientImpl;
import com.kuflow.rest.client.models.Authentication;
import com.kuflow.rest.client.models.DefaultErrorException;

/** An instance of this class provides access to all the operations defined in AuthenticationOperations. */
public final class AuthenticationOperations {

    /** The service. */
    private final AuthenticationOperationsImpl service;

    /**
     * Initializes an instance of AuthenticationOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public AuthenticationOperations(KuFlowClientImpl client) {
        this.service = client.getAuthenticationOperations();
    }

    /**
     * Create an authentication for the current principal.
     *
     * @param authentication Authentication to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Authentication> createAuthenticationWithResponse(Authentication authentication, Context context) {
        return this.service.createAuthenticationWithResponse(authentication, context);
    }

    /**
     * Create an authentication for the current principal.
     *
     * @param authentication Authentication to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Authentication createAuthentication(Authentication authentication) {
        return this.createAuthenticationWithResponse(authentication, Context.NONE).getValue();
    }
}
