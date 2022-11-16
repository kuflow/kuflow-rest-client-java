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
import java.util.Map;

/** The TaskElementValueObject model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonTypeName("OBJECT")
@Fluent
public final class TaskElementValueObject extends TaskElementValue {
    /*
     * The type property.
     */
    @JsonTypeId
    @JsonProperty(value = "type", required = true)
    private TaskElementValueType type = TaskElementValueType.OBJECT;

    /*
     * Dictionary of <any>
     */
    @JsonProperty(value = "value")
    private Map<String, Object> value;

    /** Creates an instance of TaskElementValueObject class. */
    public TaskElementValueObject() {}

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public TaskElementValueType getType() {
        return this.type;
    }

    /**
     * Get the value property: Dictionary of &lt;any&gt;.
     *
     * @return the value value.
     */
    public Map<String, Object> getValue() {
        return this.value;
    }

    /**
     * Set the value property: Dictionary of &lt;any&gt;.
     *
     * @param value the value value to set.
     * @return the TaskElementValueObject object itself.
     */
    public TaskElementValueObject setValue(Map<String, Object> value) {
        this.value = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskElementValueObject setValid(Boolean valid) {
        super.setValid(valid);
        return this;
    }
}
