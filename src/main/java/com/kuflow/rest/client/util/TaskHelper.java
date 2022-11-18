package com.kuflow.rest.client.util;

import static java.util.stream.Collectors.toList;

import com.kuflow.rest.client.KuFlowRestClientException;
import com.kuflow.rest.client.models.Task;
import com.kuflow.rest.client.models.TaskElementValue;
import com.kuflow.rest.client.models.TaskElementValueDocument;
import com.kuflow.rest.client.models.TaskElementValueDocumentItem;
import com.kuflow.rest.client.models.TaskElementValueNumber;
import com.kuflow.rest.client.models.TaskElementValueObject;
import com.kuflow.rest.client.models.TaskElementValuePrincipal;
import com.kuflow.rest.client.models.TaskElementValuePrincipalItem;
import com.kuflow.rest.client.models.TaskElementValueString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskHelper {

    public static Boolean getElementValueValid(Task task, String elementDefinitionCode) {
        List<TaskElementValue> elementValues = getElementValues(task, elementDefinitionCode);

        return elementValues.stream().filter(v -> Boolean.FALSE.equals(v.isValid())).findAny().isEmpty();
    }

    public static Boolean getElementValueValidAt(Task task, String elementDefinitionCode, int index) {
        List<TaskElementValue> elementValues = getElementValues(task, elementDefinitionCode);

        return elementValues.get(index).isValid();
    }

    public static Task setElementValueValid(Task task, String elementDefinitionCode, Boolean valid) {
        List<TaskElementValue> elementValues = getElementValues(task, elementDefinitionCode);
        elementValues.forEach(it -> it.setValid(valid));

        return task;
    }

    public static Task setElementValueValidAt(Task task, String elementDefinitionCode, Boolean valid, int index) {
        List<TaskElementValue> elementValues = getElementValues(task, elementDefinitionCode);
        elementValues.get(index).setValid(valid);

        return task;
    }

    public static Task addElementValue(Task task, String elementDefinitionCode, Object elementValue) {
        List<Object> valueList = new LinkedList<>();
        if (elementValue != null) {
            valueList.add(elementValue);
        }

        List<TaskElementValue> taskElementValues = task.getElementValues().get(elementDefinitionCode);
        if (taskElementValues == null) {
            taskElementValues = new LinkedList<>();
        }

        TaskElementValue taskElementValue = toTaskElementValue(elementValue);
        taskElementValues.add(taskElementValue);

        task.getElementValues().put(elementDefinitionCode, taskElementValues);

        return putElementValue(task, elementDefinitionCode, valueList);
    }

    public static Task putElementValues(Task task, String elementDefinitionCode, Object... elementValues) {
        List<Object> valueList = new LinkedList<>();
        if (elementValues != null) {
            valueList.addAll(List.of(elementValues));
        }

        return putElementValue(task, elementDefinitionCode, valueList);
    }

    private static Task putElementValue(Task task, String code, List<Object> values) {
        List<TaskElementValue> taskElementValues = values
            .stream()
            .map(TaskHelper::toTaskElementValue)
            .collect(toList());

        if (task.getElementValues() == null) {
            task.setElementValues(new HashMap<>());
        }

        task.getElementValues().put(code, taskElementValues);

        return task;
    }

    @SuppressWarnings("unchecked")
    private static TaskElementValue toTaskElementValue(Object value) {
        if (value instanceof String) {
            return toElementValueResourceString((String) value);
        } else if (value instanceof Double) {
            return toElementValueResourceNumber((Double) value);
        } else if (value instanceof LocalDate) {
            return toElementValueResourceString(value.toString());
        } else if (value instanceof Map) {
            return toElementValueResourceObject((Map<String, Object>) value);
        } else if (value instanceof TaskElementValueDocumentItem) {
            return toElementValueResourceDocument((TaskElementValueDocumentItem) value);
        } else if (value instanceof TaskElementValuePrincipalItem) {
            return toElementValueResourcePrincipal((TaskElementValuePrincipalItem) value);
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }
    }

    private static TaskElementValueString toElementValueResourceString(String value) {
        return new TaskElementValueString().setValue(value);
    }

    private static TaskElementValueNumber toElementValueResourceNumber(Double value) {
        return new TaskElementValueNumber().setValue(value);
    }

    private static TaskElementValueObject toElementValueResourceObject(Map<String, Object> value) {
        return new TaskElementValueObject().setValue(value);
    }

    private static TaskElementValueDocument toElementValueResourceDocument(TaskElementValueDocumentItem value) {
        return new TaskElementValueDocument().setValue(value);
    }

    private static TaskElementValuePrincipal toElementValueResourcePrincipal(TaskElementValuePrincipalItem value) {
        return new TaskElementValuePrincipal().setValue(value);
    }

    public static String getElementValueAsString(Task task, String elementDefinitionCode) {
        return findElementValueAsString(task, elementDefinitionCode)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static Optional<String> findElementValueAsString(Task task, String elementDefinitionCode) {
        Optional<TaskElementValue> taskElementValue = findElementValue(task, elementDefinitionCode);

        return taskElementValue.map(TaskHelper::getElementValueAsString);
    }

    public static List<String> getElementValueAsStringList(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = getElementValues(task, elementDefinitionCode);

        return taskElementValues.stream().map(TaskHelper::getElementValueAsString).collect(toList());
    }

    private static String getElementValueAsString(TaskElementValue elementValue) {
        if (elementValue instanceof TaskElementValueString) {
            TaskElementValueString valueString = (TaskElementValueString) elementValue;

            return valueString.getValue();
        }

        if (elementValue instanceof TaskElementValueNumber) {
            TaskElementValueNumber valueNumber = (TaskElementValueNumber) elementValue;

            return valueNumber.getValue() != null ? valueNumber.getValue().toString() : null;
        }

        throw new KuFlowRestClientException(String.format("elementValue %s is not a String", elementValue));
    }

    public static Optional<Double> findElementValueAsDouble(Task task, String elementDefinitionCode) {
        Optional<TaskElementValue> taskElementValue = findElementValue(task, elementDefinitionCode);

        return taskElementValue.map(TaskHelper::getElementValueAsDouble);
    }

    public static Double getElementValueAsDouble(Task task, String elementDefinitionCode) {
        return findElementValueAsDouble(task, elementDefinitionCode)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }


    public static List<Double> getElementValueAsDoubleList(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = getElementValues(task, elementDefinitionCode);

        return taskElementValues.stream().map(TaskHelper::getElementValueAsDouble).collect(toList());
    }

    private static Double getElementValueAsDouble(TaskElementValue elementValue) {
        if (elementValue instanceof TaskElementValueNumber) {
            TaskElementValueNumber valueNumber = (TaskElementValueNumber) elementValue;

            return valueNumber.getValue();
        }

        if (elementValue instanceof TaskElementValueString) {
            TaskElementValueString valueString = (TaskElementValueString) elementValue;

            try {
                if (valueString.getValue() == null) {
                    return null;
                }
                return Double.valueOf(valueString.getValue());
            } catch (NumberFormatException e) {
                throw new KuFlowRestClientException(String.format("elementValue %s is not a number", valueString), e);
            }
        }

        throw new KuFlowRestClientException(String.format("elementValue %s is not a Number", elementValue));
    }

    public static Optional<LocalDate> findElementValueAsLocalDate(Task task, String elementDefinitionCode) {
        Optional<TaskElementValue> taskElementValue = findElementValue(task, elementDefinitionCode);

        return taskElementValue.map(TaskHelper::getElementValueAsLocalDate);
    }

    public static LocalDate getElementValueAsLocalDate(Task task, String elementDefinitionCode) {
        return findElementValueAsLocalDate(task, elementDefinitionCode)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<LocalDate> getElementValueAsLocalDateList(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = getElementValues(task, elementDefinitionCode);

        return taskElementValues.stream().map(TaskHelper::getElementValueAsLocalDate).collect(toList());
    }

    private static LocalDate getElementValueAsLocalDate(TaskElementValue elementValue) {
        String valueString = getElementValueAsString(elementValue);

        if (valueString == null) {
            return null;
        }

        try {
            return LocalDate.parse(valueString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a date", valueString), e);
        }
    }

    public static Optional<Map<String, Object>> findElementValueAsMap(Task task, String elementDefinitionCode) {
        Optional<TaskElementValue> taskElementValue = findElementValue(task, elementDefinitionCode);

        return taskElementValue.map(TaskHelper::getElementValueAsMap);
    }

    public static Map<String, Object> getElementValueAsMap(Task task, String elementDefinitionCode) {
            return findElementValueAsMap(task, elementDefinitionCode)
                .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<Map<String, Object>> getElementValueAsMapList(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = getElementValues(task, elementDefinitionCode);

        return taskElementValues.stream().map(TaskHelper::getElementValueAsMap).collect(toList());
    }

    private static Map<String, Object> getElementValueAsMap(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValueObject)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not an Object", elementValue));
        }

        TaskElementValueObject elementValueObject = (TaskElementValueObject) elementValue;

        if (elementValueObject.getValue() == null) {
            return null;
        }

        return elementValueObject.getValue();
    }

    public static Optional<TaskElementValueDocumentItem> findElementValueAsDocument(Task task, String elementDefinitionCode) {
        Optional<TaskElementValue> taskElementValue = findElementValue(task, elementDefinitionCode);

        return taskElementValue.map(TaskHelper::getElementValueAsDocument);
    }

    public static TaskElementValueDocumentItem getElementValueAsDocument(Task task, String elementDefinitionCode) {
        return findElementValueAsDocument(task, elementDefinitionCode)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<TaskElementValueDocumentItem> getElementValueAsDocumentList(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = getElementValues(task, elementDefinitionCode);

        return taskElementValues.stream().map(TaskHelper::getElementValueAsDocument).collect(toList());
    }

    private static TaskElementValueDocumentItem getElementValueAsDocument(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValueDocument)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a Document", elementValue));
        }

        TaskElementValueDocument elementValueDocument = (TaskElementValueDocument) elementValue;

        return elementValueDocument.getValue();
    }

    public static Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(Task task, String elementDefinitionCode) {
        Optional<TaskElementValue> taskElementValue = findElementValue(task, elementDefinitionCode);

        return taskElementValue.map(TaskHelper::getElementValueAsPrincipal);
    }

    public static TaskElementValuePrincipalItem getElementValueAsPrincipal(Task task, String elementDefinitionCode) {
        return findElementValueAsPrincipal(task, elementDefinitionCode)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = getElementValues(task, elementDefinitionCode);

        return taskElementValues.stream().map(TaskHelper::getElementValueAsPrincipal).collect(toList());
    }

    private static TaskElementValuePrincipalItem getElementValueAsPrincipal(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValuePrincipal)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a Principal", elementValue));
        }

        TaskElementValuePrincipal elementValuePrincipal = (TaskElementValuePrincipal) elementValue;

        return elementValuePrincipal.getValue();
    }

    private static Optional<TaskElementValue> findElementValue(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = task.getElementValues().get(elementDefinitionCode);
        if (taskElementValues == null || taskElementValues.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(taskElementValues.get(0));
    }

    private static List<TaskElementValue> getElementValues(Task task, String elementDefinitionCode) {
        List<TaskElementValue> taskElementValues = task.getElementValues().get(elementDefinitionCode);
        if (taskElementValues == null || taskElementValues.isEmpty()) {
            return new ArrayList<>();
        }

        return taskElementValues;
    }




}
