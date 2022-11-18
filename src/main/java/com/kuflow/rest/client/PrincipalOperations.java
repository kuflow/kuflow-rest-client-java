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
import com.kuflow.rest.client.model.FindPrincipalsOptions;
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
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<PrincipalPage> findPrincipalsWithResponse(FindPrincipalsOptions options, Context context) {
        options = options != null ? options : new FindPrincipalsOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        PrincipalType type = options.getType();
        List<UUID> groupId = !options.getGroupIds().isEmpty() ? options.getGroupIds() : null;

        return this.service.findPrincipalsWithResponse(size, page, sort, type, groupId, context);
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PrincipalPage findPrincipals(FindPrincipalsOptions options) {
        return this.findPrincipalsWithResponse(options, Context.NONE).getValue();
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
        return this.findPrincipalsWithResponse(null, Context.NONE).getValue();
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
