//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import static com.kuflow.rest.client.util.TaskElementValueAccessorTaskSaveElementCommand.of;
import static com.kuflow.rest.client.util.TaskHelper.addElementValueOf;
import static com.kuflow.rest.client.util.TaskHelper.addElementValuesOf;
import static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsDocument;
import static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsDouble;
import static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsLocalDate;
import static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsMap;
import static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsPrincipal;
import static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsString;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDocument;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDocumentList;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDouble;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDoubleList;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsLocalDate;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsLocalDateList;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsMap;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsMapList;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsPrincipal;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsPrincipalList;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsString;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsStringList;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfValid;
import static com.kuflow.rest.client.util.TaskHelper.getElementValueOfValidAt;
import static com.kuflow.rest.client.util.TaskHelper.putElementValueOf;
import static com.kuflow.rest.client.util.TaskHelper.putElementValuesOf;
import static com.kuflow.rest.client.util.TaskHelper.setElementValueOfValid;
import static com.kuflow.rest.client.util.TaskHelper.setElementValueOfValidAt;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** The TaskSaveElementCommand model. */
@Fluent
public final class TaskSaveElementCommand {
    /*
     * The elementDefinitionCode property.
     */
    @JsonProperty(value = "elementDefinitionCode", required = true)
    private String elementDefinitionCode;

    /*
     * The elementValues property.
     */
    @JsonProperty(value = "elementValues")
    private List<TaskElementValue> elementValues;

    /** Creates an instance of TaskSaveElementCommand class. */
    public TaskSaveElementCommand() {}

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
     * @return the TaskSaveElementCommand object itself.
     */
    public TaskSaveElementCommand setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;
        return this;
    }

    /**
     * Get the elementValues property: The elementValues property.
     *
     * @return the elementValues value.
     */
    public List<TaskElementValue> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: The elementValues property.
     *
     * @param elementValues the elementValues value to set.
     * @return the TaskSaveElementCommand object itself.
     */
    public TaskSaveElementCommand setElementValues(List<TaskElementValue> elementValues) {
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
    public TaskSaveElementCommand setElementValueValid(Boolean valid) {
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
    public TaskSaveElementCommand setElementValueValidAt(Boolean valid, int index) {
        setElementValueOfValidAt(of(this), valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsString(String elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsStringList(List<String> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsString(String elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsStringList(List<String> elementValues) {
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
    public TaskSaveElementCommand putElementValueAsDouble(Double elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsDoubleList(List<Double> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsDouble(Double elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsDoubleList(List<Double> elementValues) {
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
    public TaskSaveElementCommand putElementValueAsLocalDate(LocalDate elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsLocalDateList(List<LocalDate> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsLocalDate(LocalDate elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsLocalDateList(List<LocalDate> elementValues) {
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

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsMap(Map<String, Object> elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsMapList(List<Map<String, Object>> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsMap(Map<String, Object> elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsMapList(List<Map<String, Object>> elementValues) {
        addElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Map
     *
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public Map<String, Object> getElementValueAsMap() {
        return getElementValueOfAsMap(of(this));
    }

    /**
     * Get all elements as Map
     *
     * @return the elements values.
     */
    public List<Map<String, Object>> getElementValueAsMapList() {
        return getElementValueOfAsMapList(of(this));
    }

    /**
     * Try to get an element as Map
     *
     * @return the element value if exists.
     */
    public Optional<Map<String, Object>> findElementValueAsMap() {
        return findElementValueOfAsMap(of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        addElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Document
     *
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public TaskElementValueDocumentItem getElementValueAsDocument() {
        return getElementValueOfAsDocument(of(this));
    }

    /**
     * Get all elements as Document
     *
     * @return the elements values.
     */
    public List<TaskElementValueDocumentItem> getElementValueAsDocumentList() {
        return getElementValueOfAsDocumentList(of(this));
    }

    /**
     * Try to get an element as Document
     *
     * @return the element value if exists.
     */
    public Optional<TaskElementValueDocumentItem> findElementValueAsDocument() {
        return findElementValueOfAsDocument(of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        putElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskSaveElementCommand putElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        putElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        addElementValueOf(of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskSaveElementCommand addElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        addElementValuesOf(of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Principal
     *
     * @return the element value.
     * @throws com.kuflow.rest.client.KuFlowRestClientException If element value doesn't exists
     */
    public TaskElementValuePrincipalItem getElementValueAsPrincipal() {
        return getElementValueOfAsPrincipal(of(this));
    }

    /**
     * Get all elements as Principal
     *
     * @return the elements values.
     */
    public List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList() {
        return getElementValueOfAsPrincipalList(of(this));
    }

    /**
     * Try to get an element as Principal
     *
     * @return the element value if exists.
     */
    public Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal() {
        return findElementValueOfAsPrincipal(of(this));
    }
}
