//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.client.models.Authentication;
import com.kuflow.rest.client.models.DefaultErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in AuthenticationOperations. */
public final class AuthenticationOperationsImpl {
    /** The proxy service used to perform REST calls. */
    private final AuthenticationOperationsService service;

    /** The service client containing this operation class. */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of AuthenticationOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    AuthenticationOperationsImpl(KuFlowClientImpl client) {
        this.service =
                RestProxy.create(
                        AuthenticationOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientAuthenticationOperations to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientAuthenti")
    public interface AuthenticationOperationsService {
        @Post("/authentications")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Authentication>> createAuthentication(
                @HostParam("$host") String host,
                @BodyParam("application/json") Authentication authentication,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/authentications")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Authentication> createAuthenticationSync(
                @HostParam("$host") String host,
                @BodyParam("application/json") Authentication authentication,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Create an authentication for the current principal.
     *
     * @param authentication Authentication to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Authentication>> createAuthenticationWithResponseAsync(Authentication authentication) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.createAuthentication(this.client.getHost(), authentication, accept, context));
    }

    /**
     * Create an authentication for the current principal.
     *
     * @param authentication Authentication to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Authentication>> createAuthenticationWithResponseAsync(
            Authentication authentication, Context context) {
        final String accept = "application/json";
        return service.createAuthentication(this.client.getHost(), authentication, accept, context);
    }

    /**
     * Create an authentication for the current principal.
     *
     * @param authentication Authentication to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Authentication> createAuthenticationAsync(Authentication authentication) {
        return createAuthenticationWithResponseAsync(authentication).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create an authentication for the current principal.
     *
     * @param authentication Authentication to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Authentication> createAuthenticationAsync(Authentication authentication, Context context) {
        return createAuthenticationWithResponseAsync(authentication, context)
                .flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.createAuthenticationSync(this.client.getHost(), authentication, accept, context);
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
        return createAuthenticationWithResponse(authentication, Context.NONE).getValue();
    }
}
