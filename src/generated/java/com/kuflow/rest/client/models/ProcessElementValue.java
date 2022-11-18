//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The ProcessElementValue model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = ProcessElementValue.class)
@JsonTypeName("ProcessElementValue")
@JsonSubTypes(
    {
        @JsonSubTypes.Type(name = "STRING", value = ProcessElementValueString.class),
        @JsonSubTypes.Type(name = "NUMBER", value = ProcessElementValueNumber.class),
    }
)
@Fluent
public class ProcessElementValue {

    /*
     * The valid property.
     */
    @JsonProperty(value = "valid")
    private Boolean valid;

    /** Creates an instance of ProcessElementValue class. */
    public ProcessElementValue() {}

    /**
     * Get the valid property: The valid property.
     *
     * @return the valid value.
     */
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * Set the valid property: The valid property.
     *
     * @param valid the valid value to set.
     * @return the ProcessElementValue object itself.
     */
    public ProcessElementValue setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }
}