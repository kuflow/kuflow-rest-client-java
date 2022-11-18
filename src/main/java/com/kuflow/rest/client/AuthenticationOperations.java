package com.kuflow.rest.client;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.kuflow.rest.client.implementation.AuthenticationOperationsImpl;
import com.kuflow.rest.client.models.Authentication;
import com.kuflow.rest.client.models.DefaultErrorException;

/** An instance of this class provides access to all the operations defined in AuthenticationOperations. */
public final class AuthenticationOperations {

    /** The service. */
    private final AuthenticationOperationsImpl service;

    /**
     * Initializes an instance of AuthenticationOperationsImpl.
     *
     * @param service the instance of the service client containing this operation class.
     */
    AuthenticationOperations(AuthenticationOperationsImpl service) {
        this.service = service;
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
        return service.createAuthenticationWithResponse(authentication, context);
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
