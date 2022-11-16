//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The TaskElementValueString model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonTypeName("STRING")
@Fluent
public final class TaskElementValueString extends TaskElementValue {
    /*
     * The type property.
     */
    @JsonTypeId
    @JsonProperty(value = "type", required = true)
    private TaskElementValueType type = TaskElementValueType.STRING;

    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private String value;

    /** Creates an instance of TaskElementValueString class. */
    public TaskElementValueString() {}

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public TaskElementValueType getType() {
        return this.type;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the TaskElementValueString object itself.
     */
    public TaskElementValueString setValue(String value) {
        this.value = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskElementValueString setValid(Boolean valid) {
        super.setValid(valid);
        return this;
    }
}
