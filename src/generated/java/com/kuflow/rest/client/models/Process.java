//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import static com.kuflow.rest.client.util.ProcessElementValueAccessorProcess.of;
import static com.kuflow.rest.client.util.ProcessHelper.addElementValueOf;
import static com.kuflow.rest.client.util.ProcessHelper.addElementValuesOf;
import static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsDouble;
import static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsLocalDate;
import static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsString;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsDouble;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsDoubleList;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsLocalDate;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsLocalDateList;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsString;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsStringList;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfValid;
import static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfValidAt;
import static com.kuflow.rest.client.util.ProcessHelper.putElementValueOf;
import static com.kuflow.rest.client.util.ProcessHelper.putElementValuesOf;
import static com.kuflow.rest.client.util.ProcessHelper.setElementValueOfValid;
import static com.kuflow.rest.client.util.ProcessHelper.setElementValueOfValidAt;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/** The Process model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@odata\\.type")
@JsonTypeName("#KuFlow.Rest.Process")
@JsonFlatten
@Fluent
public class Process extends AbstractAudited {
    /*
     * Process ID.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * Process subject.
     */
    @JsonProperty(value = "subject")
    private String subject;

    /*
     * Process state
     */
    @JsonProperty(value = "state")
    private ProcessState state;

    /*
     * The processDefinition property.
     */
    @JsonProperty(value = "processDefinition", required = true)
    private ProcessDefinitionSummary processDefinition;

    /*
     * Process element values, en ElementValueDocument is not allowed.
     */
    @JsonProperty(value = "elementValues")
    private Map<String, List<ProcessElementValue>> elementValues;

    /*
     * The initiator property.
     */
    @JsonProperty(value = "initiator")
    private Principal initiator;

    /** Creates an instance of Process class. */
    public Process() {}

    /**
     * Get the id property: Process ID.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Process ID.
     *
     * @param id the id value to set.
     * @return the Process object itself.
     */
    public Process setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the subject property: Process subject.
     *
     * @return the subject value.
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Set the subject property: Process subject.
     *
     * @param subject the subject value to set.
     * @return the Process object itself.
     */
    public Process setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Get the state property: Process state.
     *
     * @return the state value.
     */
    public ProcessState getState() {
        return this.state;
    }

    /**
     * Set the state property: Process state.
     *
     * @param state the state value to set.
     * @return the Process object itself.
     */
    public Process setState(ProcessState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the processDefinition property: The processDefinition property.
     *
     * @return the processDefinition value.
     */
    public ProcessDefinitionSummary getProcessDefinition() {
        return this.processDefinition;
    }

    /**
     * Set the processDefinition property: The processDefinition property.
     *
     * @param processDefinition the processDefinition value to set.
     * @return the Process object itself.
     */
    public Process setProcessDefinition(ProcessDefinitionSummary processDefinition) {
        this.processDefinition = processDefinition;
        return this;
    }

    /**
     * Get the elementValues property: Process element values, en ElementValueDocument is not allowed.
     *
     * @return the elementValues value.
     */
    public Map<String, List<ProcessElementValue>> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: Process element values, en ElementValueDocument is not allowed.
     *
     * @param elementValues the elementValues value to set.
     * @return the Process object itself.
     */
    public Process setElementValues(Map<String, List<ProcessElementValue>> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * Get the initiator property: The initiator property.
     *
     * @return the initiator value.
     */
    public Principal getInitiator() {
        return this.initiator;
    }

    /**
     * Set the initiator property: The initiator property.
     *
     * @param initiator the initiator value to set.
     * @return the Process object itself.
     */
    public Process setInitiator(Principal initiator) {
        this.initiator = initiator;
        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public Boolean getElementValueValid(String elementDefinitionCode) {
        return getElementValueOfValid(of(this, elementDefinitionCode));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     */
    public Boolean getElementValueValidAt(String elementDefinitionCode, int index) {
        return getElementValueOfValidAt(of(this, elementDefinitionCode), index);
    }

    /**
     * Set valid to all values
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @return the Task object itself.
     */
    public Process setElementValueValid(String elementDefinitionCode, Boolean valid) {
        setElementValueOfValid(of(this, elementDefinitionCode), valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     */
    public Process setElementValueValidAt(String elementDefinitionCode, Boolean valid, int index) {
        setElementValueOfValidAt(of(this, elementDefinitionCode), valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public Process putElementValueAsString(String elementDefinitionCode, String elementValue) {
        putElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public Process putElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        putElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public Process addElementValueAsString(String elementDefinitionCode, String elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public Process addElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public String getElementValueAsString(String elementDefinitionCode) {
        return getElementValueOfAsString(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<String> getElementValueAsStringList(String elementDefinitionCode) {
        return getElementValueOfAsStringList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<String> findElementValueAsString(String elementDefinitionCode) {
        return findElementValueOfAsString(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public Process putElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        putElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public Process putElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        putElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public Process addElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public Process addElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public Double getElementValueAsDouble(String elementDefinitionCode) {
        return getElementValueOfAsDouble(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<Double> getElementValueAsDoubleList(String elementDefinitionCode) {
        return getElementValueOfAsDoubleList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<Double> findElementValueAsDouble(String elementDefinitionCode) {
        return findElementValueOfAsDouble(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public Process putElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        putElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public Process putElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        putElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public Process addElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public Process addElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public LocalDate getElementValueAsLocalDate(String elementDefinitionCode) {
        return getElementValueOfAsLocalDate(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<LocalDate> getElementValueAsLocalDateList(String elementDefinitionCode) {
        return getElementValueOfAsLocalDateList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<LocalDate> findElementValueAsLocalDate(String elementDefinitionCode) {
        return findElementValueOfAsLocalDate(of(this, elementDefinitionCode));
    }
}
