//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/** The TaskElementValuePrincipalItem model. */
@Fluent
public final class TaskElementValuePrincipalItem {

    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * The type property.
     */
    @JsonProperty(value = "type", required = true)
    private PrincipalType type;

    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /** Creates an instance of TaskElementValuePrincipalItem class. */
    public TaskElementValuePrincipalItem() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the TaskElementValuePrincipalItem object itself.
     */
    public TaskElementValuePrincipalItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public PrincipalType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the TaskElementValuePrincipalItem object itself.
     */
    public TaskElementValuePrincipalItem setType(PrincipalType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the TaskElementValuePrincipalItem object itself.
     */
    public TaskElementValuePrincipalItem setName(String name) {
        this.name = name;
        return this;
    }
}