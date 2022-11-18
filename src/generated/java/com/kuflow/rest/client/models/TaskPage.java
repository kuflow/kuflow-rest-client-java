//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

/** The TaskPage model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "modelType")
@JsonTypeName("TASK_PAGE")
@Fluent
public final class TaskPage extends Page {

    /*
     * The content property.
     */
    @JsonProperty(value = "content", required = true)
    private List<Task> content;

    /** Creates an instance of TaskPage class. */
    public TaskPage() {}

    /**
     * Get the content property: The content property.
     *
     * @return the content value.
     */
    public List<Task> getContent() {
        return this.content;
    }

    /**
     * Set the content property: The content property.
     *
     * @param content the content value to set.
     * @return the TaskPage object itself.
     */
    public TaskPage setContent(List<Task> content) {
        this.content = content;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskPage setMetadata(PageMetadata metadata) {
        super.setMetadata(metadata);
        return this;
    }
}