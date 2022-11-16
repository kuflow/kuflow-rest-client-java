//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.kuflow.rest.client.implementation.ProcessOperationsImpl;
import com.kuflow.rest.client.models.DefaultErrorException;
import com.kuflow.rest.client.models.Process;
import com.kuflow.rest.client.models.ProcessChangeInitiatorCommand;
import com.kuflow.rest.client.models.ProcessDeleteElementCommand;
import com.kuflow.rest.client.models.ProcessPage;
import com.kuflow.rest.client.models.ProcessSaveElementCommand;
import com.kuflow.rest.client.models.ProcessSaveUserActionValueDocumentCommand;

import java.util.List;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in ProcessOperations. */
public final class ProcessOperations {
    /** The service. */
    private final ProcessOperationsImpl service;

    /**
     * Initializes an instance of ProcessOperationsImpl.
     *
     * @param service the instance of the service client containing this operation class.
     */
    ProcessOperations(ProcessOperationsImpl service) {
        this.service = service;
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessPage> findProcessesWithResponse(
            Integer size, Integer page, List<String> sort, Context context) {
        return this.service.findProcessesWithResponse(size, page, sort, context);
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses(Integer size, Integer page, List<String> sort) {
        return this.findProcessesWithResponse(size, page, sort, Context.NONE).getValue();
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses() {
        return this.findProcessesWithResponse(null, null, null, Context.NONE).getValue();
    }

    /**
     * Create a new process
     *
     * <p>Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow
     * GUI.
     *
     * <p>When a process is created, the current user is assigned as the process initiator, if you want to change it,
     * you can pass a valid initiator using the following options:
     *
     * <p>* If you know the `principal ID` you can assign it to `initiator.id` * If you know the `user ID` you can
     * assign it to `initiator.user.id` * If you know the `user email` you can assign it to `initiator.user.email` * If
     * you know the `application ID` you can assign it to `initiator.application.id`
     *
     * <p>If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param process Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> createProcessWithResponse(Process process, Context context) {
        return this.service.createProcessWithResponse(process, context);
    }

    /**
     * Create a new process
     *
     * <p>Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow
     * GUI.
     *
     * <p>When a process is created, the current user is assigned as the process initiator, if you want to change it,
     * you can pass a valid initiator using the following options:
     *
     * <p>* If you know the `principal ID` you can assign it to `initiator.id` * If you know the `user ID` you can
     * assign it to `initiator.user.id` * If you know the `user email` you can assign it to `initiator.user.email` * If
     * you know the `application ID` you can assign it to `initiator.application.id`
     *
     * <p>If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param process Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process createProcess(Process process) {
        return this.createProcessWithResponse(process, Context.NONE).getValue();
    }

    /**
     * Get a Process by ID
     *
     * <p>Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> retrieveProcessWithResponse(UUID id, Context context) {
        return this.service.retrieveProcessWithResponse(id, context);
    }

    /**
     * Get a Process by ID
     *
     * <p>Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process retrieveProcess(UUID id) {
        return this.retrieveProcessWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Change process initiator
     *
     * <p>Change the current initiator of a process.
     *
     * <p>Allows you to choose a user (by email or principal identifier) or an application (principal identifier). Only
     * one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessChangeInitiatorWithResponse(UUID id, ProcessChangeInitiatorCommand command, Context context) {
        return this.service.actionsProcessChangeInitiatorWithResponse(id, command, context);
    }

    /**
     * Change process initiator
     *
     * <p>Change the current initiator of a process.
     *
     * <p>Allows you to choose a user (by email or principal identifier) or an application (principal identifier). Only
     * one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessChangeInitiator(UUID id, ProcessChangeInitiatorCommand command) {
        return this.actionsProcessChangeInitiatorWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save a process element, aka: metadata
     *
     * <p>Allow to save an element.
     *
     * <p>If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted.
     *
     * <p>If the process is already finished the invocations fails with an error.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessSaveElementWithResponse(UUID id, ProcessSaveElementCommand command, Context context) {
        return this.service.actionsProcessSaveElementWithResponse(id, command, context);
    }

    /**
     * Save a process element, aka: metadata
     *
     * <p>Allow to save an element.
     *
     * <p>If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted.
     *
     * <p>If the process is already finished the invocations fails with an error.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessSaveElement(UUID id, ProcessSaveElementCommand command) {
        return this.actionsProcessSaveElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Delete an element by code
     *
     * <p>Allow to delete a process element by specifying the item definition code.
     *
     * <p>Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessDeleteElementWithResponse(UUID id, ProcessDeleteElementCommand command, Context context) {
        return this.service.actionsProcessDeleteElementWithResponse(id, command, context);
    }

    /**
     * Delete an element by code
     *
     * <p>Allow to delete a process element by specifying the item definition code.
     *
     * <p>Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessDeleteElement(UUID id, ProcessDeleteElementCommand command) {
        return this.actionsProcessDeleteElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Complete a Process
     *
     * <p>Complete a Process. The state of Process is set to 'completed'.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessCompleteWithResponse(UUID id, Context context) {
        return this.service.actionsProcessCompleteWithResponse(id, context);
    }

    /**
     * Complete a Process
     *
     * <p>Complete a Process. The state of Process is set to 'completed'.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessComplete(UUID id) {
        return this.actionsProcessCompleteWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Cancel a Process
     *
     * <p>Cancel a Process. The Process state is set to 'cancelled'.
     *
     * <p>All the active tasks will be marked as cancelled too.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessCancelWithResponse(UUID id, Context context) {
        return this.service.actionsProcessCancelWithResponse(id, context);
    }

    /**
     * Cancel a Process
     *
     * <p>Cancel a Process. The Process state is set to 'cancelled'.
     *
     * <p>All the active tasks will be marked as cancelled too.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessCancel(UUID id) {
        return this.actionsProcessCancelWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param command Command info.
     * @param file Document file.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessSaveUserActionValueDocumentWithResponse(
            UUID id,
            ProcessSaveUserActionValueDocumentCommand command,
            BinaryData file,
            long contentLength,
            Context context
    ) {
        return this.service.actionsProcessSaveUserActionValueDocumentWithResponse(id, command, file, contentLength, context);
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param command Command info.
     * @param file Document file.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessSaveUserActionValueDocument(UUID id, ProcessSaveUserActionValueDocumentCommand command, BinaryData file, long contentLength) {
        return this.actionsProcessSaveUserActionValueDocumentWithResponse(id, command, file, contentLength, Context.NONE).getValue();
    }
}
