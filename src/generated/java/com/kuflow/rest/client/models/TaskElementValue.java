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

/** The TaskElementValue model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = TaskElementValue.class,
        visible = true)
@JsonTypeName("TaskElementValue")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "STRING", value = TaskElementValueString.class),
    @JsonSubTypes.Type(name = "NUMBER", value = TaskElementValueNumber.class),
    @JsonSubTypes.Type(name = "OBJECT", value = TaskElementValueObject.class),
    @JsonSubTypes.Type(name = "DOCUMENT", value = TaskElementValueDocument.class),
    @JsonSubTypes.Type(name = "PRINCIPAL", value = TaskElementValuePrincipal.class)
})
@Fluent
public class TaskElementValue {
    /*
     * The valid property.
     */
    @JsonProperty(value = "valid")
    private Boolean valid;

    /** Creates an instance of TaskElementValue class. */
    public TaskElementValue() {}

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
     * @return the TaskElementValue object itself.
     */
    public TaskElementValue setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }
}
