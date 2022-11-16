//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/** The WebhookEventTaskStateChangedData model. */
@Fluent
public final class WebhookEventTaskStateChangedData {
    /*
     * The processId property.
     */
    @JsonProperty(value = "processId", required = true)
    private UUID processId;

    /*
     * The taskId property.
     */
    @JsonProperty(value = "taskId", required = true)
    private UUID taskId;

    /*
     * The taskCode property.
     */
    @JsonProperty(value = "taskCode", required = true)
    private String taskCode;

    /*
     * Task state
     */
    @JsonProperty(value = "taskState", required = true)
    private TaskState taskState;

    /** Creates an instance of WebhookEventTaskStateChangedData class. */
    public WebhookEventTaskStateChangedData() {}

    /**
     * Get the processId property: The processId property.
     *
     * @return the processId value.
     */
    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property: The processId property.
     *
     * @param processId the processId value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the taskId property: The taskId property.
     *
     * @return the taskId value.
     */
    public UUID getTaskId() {
        return this.taskId;
    }

    /**
     * Set the taskId property: The taskId property.
     *
     * @param taskId the taskId value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setTaskId(UUID taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * Get the taskCode property: The taskCode property.
     *
     * @return the taskCode value.
     */
    public String getTaskCode() {
        return this.taskCode;
    }

    /**
     * Set the taskCode property: The taskCode property.
     *
     * @param taskCode the taskCode value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setTaskCode(String taskCode) {
        this.taskCode = taskCode;
        return this;
    }

    /**
     * Get the taskState property: Task state.
     *
     * @return the taskState value.
     */
    public TaskState getTaskState() {
        return this.taskState;
    }

    /**
     * Set the taskState property: Task state.
     *
     * @param taskState the taskState value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setTaskState(TaskState taskState) {
        this.taskState = taskState;
        return this;
    }
}
