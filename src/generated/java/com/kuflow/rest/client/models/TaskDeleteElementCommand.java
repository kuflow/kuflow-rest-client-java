//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The TaskDeleteElementCommand model. */
@Fluent
public final class TaskDeleteElementCommand {

    /*
     * Code of task element to delete.
     */
    @JsonProperty(value = "elementDefinitionCode", required = true)
    private String elementDefinitionCode;

    /** Creates an instance of TaskDeleteElementCommand class. */
    public TaskDeleteElementCommand() {}

    /**
     * Get the elementDefinitionCode property: Code of task element to delete.
     *
     * @return the elementDefinitionCode value.
     */
    public String getElementDefinitionCode() {
        return this.elementDefinitionCode;
    }

    /**
     * Set the elementDefinitionCode property: Code of task element to delete.
     *
     * @param elementDefinitionCode the elementDefinitionCode value to set.
     * @return the TaskDeleteElementCommand object itself.
     */
    public TaskDeleteElementCommand setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;
        return this;
    }
}
