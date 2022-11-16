//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/** The ProcessDefinitionSummary model. */
@Fluent
public final class ProcessDefinitionSummary {
    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * The version property.
     */
    @JsonProperty(value = "version")
    private UUID version;

    /*
     * The name property.
     */
    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    /** Creates an instance of ProcessDefinitionSummary class. */
    public ProcessDefinitionSummary() {}

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
     * @return the ProcessDefinitionSummary object itself.
     */
    public ProcessDefinitionSummary setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the version property: The version property.
     *
     * @return the version value.
     */
    public UUID getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version property.
     *
     * @param version the version value to set.
     * @return the ProcessDefinitionSummary object itself.
     */
    public ProcessDefinitionSummary setVersion(UUID version) {
        this.version = version;
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
}
