//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import static com.kuflow.rest.client.util.ProcessElementValueAccessorProcessSaveElementCommand.of;
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
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Command to save process element. */
@Fluent
public final class ProcessSaveElementCommand {

    /*
     * The elementDefinitionCode property.
     */
    @JsonProperty(value = "elementDefinitionCode", required = true)
    private String elementDefinitionCode;

    /*
     * The elementValues property.
     */
    @JsonProperty(value = "elementValues")
    private List<ProcessElementValue> elementValues;

    /** Creates an instance of ProcessSaveElementCommand class. */
    public ProcessSaveElementCommand() {}

    /**
     * Get the elementDefinitionCode property: The elementDefinitionCode property.
     *
     * @return the elementDefinitionCode value.
     */
    public String getElementDefinitionCode() {
        return this.elementDefinitionCode;
    }

    /**
     * Set the elementDefinitionCode property: The elementDefinitionCode property.
     *
     * @param elementDefinitionCode the elementDefinitionCode value to set.
     * @return the ProcessSaveElementCommand object itself.
     */
    public ProcessSaveElementCommand setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;
        return this;
    }

    /**
     * Get the elementValues property: The elementValues property.
     *
     * @return the elementValues value.
     */
    public List<ProcessElementValue> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: The elementValues property.
     *
     * @param elementValues the elementValues value to set.
     * @return the ProcessSaveElementCommand object itself.
     */
    public ProcessSaveElementCommand setElementValues(List<ProcessElementValue> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public Boolean getElementValueValid() {
        return getElementValueOfValid(of(this));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param index Element value index
     * @return The requested valid value
     */
    public Boolean getElementValueValidAt(int index) {
        return getElementValueOfValidAt(of(this), index);
    }

    /**
     * Set valid to all values
     *
     * @param valid Valid value
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand setElementValueValid(Boolean valid) {
        setElementValueOfValid(of(this), valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand setElementValueValidAt(Boolean valid, int index) {
        setElementValueOfValidAt(of(this), valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand putElementValueAsString(String elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand putElementValueAsStringList(List<String> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand addElementValueAsString(String elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand addElementValueAsStringList(List<String> elementValues) {
        addElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public String getElementValueAsString() {
        return getElementValueOfAsString(of(this));
    }

    /**
     * Get all elements as String
     *
     * @return the elements values.
     */
    public List<String> getElementValueAsStringList() {
        return getElementValueOfAsStringList(of(this));
    }

    /**
     * Try to get an element as String
     *
     * @return the element value if exists.
     */
    public Optional<String> findElementValueAsString() {
        return findElementValueOfAsString(of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand putElementValueAsDouble(Double elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand putElementValueAsDoubleList(List<Double> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand addElementValueAsDouble(Double elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand addElementValueAsDoubleList(List<Double> elementValues) {
        addElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public Double getElementValueAsDouble() {
        return getElementValueOfAsDouble(of(this));
    }

    /**
     * Get all elements as Double
     *
     * @return the elements values.
     */
    public List<Double> getElementValueAsDoubleList() {
        return getElementValueOfAsDoubleList(of(this));
    }

    /**
     * Try to get an element as Double
     *
     * @return the element value if exists.
     */
    public Optional<Double> findElementValueAsDouble() {
        return findElementValueOfAsDouble(of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand putElementValueAsLocalDate(LocalDate elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand putElementValueAsLocalDateList(List<LocalDate> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand addElementValueAsLocalDate(LocalDate elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public ProcessSaveElementCommand addElementValueAsLocalDateList(List<LocalDate> elementValues) {
        addElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public LocalDate getElementValueAsLocalDate() {
        return getElementValueOfAsLocalDate(of(this));
    }

    /**
     * Get all elements as LocalDate
     *
     * @return the elements values.
     */
    public List<LocalDate> getElementValueAsLocalDateList() {
        return getElementValueOfAsLocalDateList(of(this));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @return the element value if exists.
     */
    public Optional<LocalDate> findElementValueAsLocalDate() {
        return findElementValueOfAsLocalDate(of(this));
    }
}