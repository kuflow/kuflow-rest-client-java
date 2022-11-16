//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.kuflow.rest.client.implementation.PrincipalOperationsImpl;
import com.kuflow.rest.client.models.DefaultErrorException;
import com.kuflow.rest.client.models.Principal;
import com.kuflow.rest.client.models.PrincipalPage;
import com.kuflow.rest.client.models.PrincipalType;

import java.util.List;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in PrincipalOperations. */
public final class PrincipalOperations {
    /** The service. */
    private final PrincipalOperationsImpl service;

    /**
     * Initializes an instance of PrincipalOperationsImpl.
     *
     * @param service the instance of the service client containing this operation class.
     */
    PrincipalOperations(PrincipalOperationsImpl service) {
        this.service = service;
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @param type Filter principals by type.
     * @param groupId Filter principals that exists in one of group ids.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<PrincipalPage> findPrincipalsWithResponse(
            Integer size, Integer page, List<String> sort, PrincipalType type, List<UUID> groupId, Context context) {
        return this.service.findPrincipalsWithResponse(size, page, sort, type, groupId, context);
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @param type Filter principals by type.
     * @param groupId Filter principals that exists in one of group ids.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PrincipalPage findPrincipals(Integer size, Integer page, List<String> sort, PrincipalType type, List<UUID> groupId) {
        return this.findPrincipalsWithResponse(size, page, sort, type, groupId, Context.NONE).getValue();
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PrincipalPage findPrincipals() {
        return this.findPrincipalsWithResponse(null, null, null, null, null, Context.NONE).getValue();
    }

    /**
     * Get a Principal by ID
     *
     * <p>Returns the requested Principal when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Principal> retrievePrincipalWithResponse(UUID id, Context context) {
        return this.service.retrievePrincipalWithResponse(id, context);
    }

    /**
     * Get a Principal by ID
     *
     * <p>Returns the requested Principal when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Principal retrievePrincipal(UUID id) {
        return this.retrievePrincipalWithResponse(id, Context.NONE).getValue();
    }
}
