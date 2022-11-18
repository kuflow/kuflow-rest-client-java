/*
 * The MIT License
 * Copyright © 2022-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.rest.client.util;

import static java.util.stream.Collectors.toList;

import com.kuflow.rest.client.KuFlowRestClientException;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;

public class TaskHelper {

    public static Boolean getElementValueOfValid(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> elementValues = getElementValuesOf(taskElementValueAccessor);
        if (elementValues.isEmpty()) {
            return null;
        }

        return elementValues.stream().filter(v -> Boolean.FALSE.equals(v.isValid())).findAny().isEmpty();
    }

    public static Boolean getElementValueOfValidAt(TaskElementValueAccessor taskElementValueAccessor, int index) {
        List<TaskElementValue> elementValues = getElementValuesOf(taskElementValueAccessor);

        return elementValues.get(index).isValid();
    }

    public static void setElementValueOfValid(TaskElementValueAccessor taskElementValueAccessor, Boolean valid) {
        List<TaskElementValue> elementValues = getElementValuesOf(taskElementValueAccessor);
        elementValues.forEach(it -> it.setValid(valid));
    }

    public static void setElementValueOfValidAt(TaskElementValueAccessor taskElementValueAccessor, Boolean valid, int index) {
        List<TaskElementValue> elementValues = getElementValuesOf(taskElementValueAccessor);
        elementValues.get(index).setValid(valid);
    }

    public static void addElementValueOf(TaskElementValueAccessor taskElementValueAccessor, Object elementValue) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValue != null) {
            elementValueList.add(elementValue);
        }

        addElementValuesOf(taskElementValueAccessor, elementValueList);
    }

    public static void addElementValuesOf(TaskElementValueAccessor taskElementValueAccessor, List<?> elementValues) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValues != null) {
            elementValueList.addAll(elementValues);
        }

        List<TaskElementValue> taskElementValues = elementValueList.stream().map(TaskHelper::toTaskElementValue).collect(toList());

        List<TaskElementValue> taskElementValueList = taskElementValueAccessor.getElementValues();

        if (taskElementValueList == null) {
            taskElementValueList = new LinkedList<>();
        }
        taskElementValueList.addAll(taskElementValues);

        taskElementValueAccessor.setElementValues(taskElementValueList);
    }

    public static void putElementValueOf(TaskElementValueAccessor taskElementValueAccessor, Object elementValue) {
        List<Object> valueList = new LinkedList<>();
        if (elementValue != null) {
            valueList.add(elementValue);
        }

        putElementValuesOf(taskElementValueAccessor, valueList);
    }

    public static void putElementValuesOf(TaskElementValueAccessor taskElementValueAccessor, List<?> elementValues) {
        if (elementValues == null) {
            elementValues = new LinkedList<>();
        }

        List<TaskElementValue> taskElementValues = elementValues.stream().map(TaskHelper::toTaskElementValue).collect(toList());

        taskElementValueAccessor.setElementValues(taskElementValues);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    private static TaskElementValue toTaskElementValue(@Nonnull Object value) {
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

    public static String getElementValueOfAsString(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueOfAsString(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static Optional<String> findElementValueOfAsString(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValueOf(taskElementValueAccessor);

        return taskElementValue.map(TaskHelper::getElementValueOfAsString);
    }

    public static List<String> getElementValueOfAsStringList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValuesOf(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskHelper::getElementValueOfAsString).collect(toList());
    }

    private static String getElementValueOfAsString(TaskElementValue elementValue) {
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

    public static Optional<Double> findElementValueOfAsDouble(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValueOf(taskElementValueAccessor);

        return taskElementValue.map(TaskHelper::getElementValueOfAsDouble);
    }

    public static Double getElementValueOfAsDouble(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueOfAsDouble(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<Double> getElementValueOfAsDoubleList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValuesOf(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskHelper::getElementValueOfAsDouble).collect(toList());
    }

    private static Double getElementValueOfAsDouble(TaskElementValue elementValue) {
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

    public static Optional<LocalDate> findElementValueOfAsLocalDate(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValueOf(taskElementValueAccessor);

        return taskElementValue.map(TaskHelper::getElementValueOfAsLocalDate);
    }

    public static LocalDate getElementValueOfAsLocalDate(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueOfAsLocalDate(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<LocalDate> getElementValueOfAsLocalDateList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValuesOf(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskHelper::getElementValueOfAsLocalDate).collect(toList());
    }

    private static LocalDate getElementValueOfAsLocalDate(TaskElementValue elementValue) {
        String valueString = getElementValueOfAsString(elementValue);

        if (valueString == null) {
            return null;
        }

        try {
            return LocalDate.parse(valueString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a date", valueString), e);
        }
    }

    public static Optional<Map<String, Object>> findElementValueOfAsMap(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValueOf(taskElementValueAccessor);

        return taskElementValue.map(TaskHelper::getElementValueOfAsMap);
    }

    public static Map<String, Object> getElementValueOfAsMap(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueOfAsMap(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<Map<String, Object>> getElementValueOfAsMapList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValuesOf(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskHelper::getElementValueOfAsMap).collect(toList());
    }

    private static Map<String, Object> getElementValueOfAsMap(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValueObject)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not an Object", elementValue));
        }

        TaskElementValueObject elementValueObject = (TaskElementValueObject) elementValue;

        if (elementValueObject.getValue() == null) {
            return null;
        }

        return elementValueObject.getValue();
    }

    public static Optional<TaskElementValueDocumentItem> findElementValueOfAsDocument(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValueOf(taskElementValueAccessor);

        return taskElementValue.map(TaskHelper::getElementValueOfAsDocument);
    }

    public static TaskElementValueDocumentItem getElementValueOfAsDocument(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueOfAsDocument(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<TaskElementValueDocumentItem> getElementValueOfAsDocumentList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValuesOf(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskHelper::getElementValueOfAsDocument).collect(toList());
    }

    private static TaskElementValueDocumentItem getElementValueOfAsDocument(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValueDocument)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a Document", elementValue));
        }

        TaskElementValueDocument elementValueDocument = (TaskElementValueDocument) elementValue;

        return elementValueDocument.getValue();
    }

    public static Optional<TaskElementValuePrincipalItem> findElementValueOfAsPrincipal(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValueOf(taskElementValueAccessor);

        return taskElementValue.map(TaskHelper::getElementValueOfAsPrincipal);
    }

    public static TaskElementValuePrincipalItem getElementValueOfAsPrincipal(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueOfAsPrincipal(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<TaskElementValuePrincipalItem> getElementValueOfAsPrincipalList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValuesOf(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskHelper::getElementValueOfAsPrincipal).collect(toList());
    }

    private static TaskElementValuePrincipalItem getElementValueOfAsPrincipal(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValuePrincipal)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a Principal", elementValue));
        }

        TaskElementValuePrincipal elementValuePrincipal = (TaskElementValuePrincipal) elementValue;

        return elementValuePrincipal.getValue();
    }

    @Nonnull
    private static Optional<TaskElementValue> findElementValueOf(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = taskElementValueAccessor.getElementValues();
        if (taskElementValues == null || taskElementValues.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(taskElementValues.get(0));
    }

    @Nonnull
    private static List<TaskElementValue> getElementValuesOf(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = taskElementValueAccessor.getElementValues();
        if (taskElementValues == null || taskElementValues.isEmpty()) {
            return new ArrayList<>();
        }

        return taskElementValues;
    }
}
