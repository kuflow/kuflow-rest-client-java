package com.kuflow.rest.client.util;

import static java.util.stream.Collectors.toList;

import com.kuflow.rest.client.KuFlowRestClientException;
import com.kuflow.rest.client.models.ProcessElementValue;
import com.kuflow.rest.client.models.ProcessElementValueNumber;
import com.kuflow.rest.client.models.ProcessElementValueString;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProcessHelper {

    public static Boolean getElementValueOfValid(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> elementValues = getElementValuesOf(processElementValueAccessor);
        if (elementValues.isEmpty()) {
            return null;
        }

        return elementValues.stream().filter(v -> Boolean.FALSE.equals(v.isValid())).findAny().isEmpty();
    }

    public static Boolean getElementValueOfValidAt(ProcessElementValueAccessor processElementValueAccessor, int index) {
        List<ProcessElementValue> elementValues = getElementValuesOf(processElementValueAccessor);

        return elementValues.get(index).isValid();
    }

    public static void setElementValueOfValid(ProcessElementValueAccessor processElementValueAccessor, Boolean valid) {
        List<ProcessElementValue> elementValues = getElementValuesOf(processElementValueAccessor);
        elementValues.forEach(it -> it.setValid(valid));
    }

    public static void setElementValueOfValidAt(ProcessElementValueAccessor processElementValueAccessor, Boolean valid, int index) {
        List<ProcessElementValue> elementValues = getElementValuesOf(processElementValueAccessor);
        elementValues.get(index).setValid(valid);
    }

    public static void addElementValueOf(ProcessElementValueAccessor processElementValueAccessor, Object elementValue) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValue != null) {
            elementValueList.add(elementValue);
        }

        addElementValuesOf(processElementValueAccessor, elementValueList);
    }

    public static void addElementValuesOf(ProcessElementValueAccessor processElementValueAccessor, List<?> elementValues) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValues != null) {
            elementValueList.addAll(elementValues);
        }

        List<ProcessElementValue> processElementValues = elementValueList
            .stream()
            .map(ProcessHelper::toProcessElementValue)
            .collect(toList());

        List<ProcessElementValue> taskElementValueList = processElementValueAccessor.getElementValues();

        if (taskElementValueList == null) {
            taskElementValueList = new LinkedList<>();
        }
        taskElementValueList.addAll(processElementValues);

        processElementValueAccessor.setElementValues(taskElementValueList);
    }

    public static void putElementValueOf(ProcessElementValueAccessor processElementValueAccessor, Object elementValue) {
        List<Object> valueList = new LinkedList<>();
        if (elementValue != null) {
            valueList.add(elementValue);
        }

        putElementValuesOf(processElementValueAccessor, valueList);
    }

    public static void putElementValuesOf(ProcessElementValueAccessor processElementValueAccessor, List<?> elementValues) {
        if (elementValues == null) {
            elementValues = new LinkedList<>();
        }

        List<ProcessElementValue> processElementValues = elementValues
            .stream()
            .map(ProcessHelper::toProcessElementValue)
            .collect(toList());

        processElementValueAccessor.setElementValues(processElementValues);
    }

    @Nonnull
    private static ProcessElementValue toProcessElementValue(@Nonnull Object value) {
        if (value instanceof String) {
            return toElementValueResourceString((String) value);
        } else if (value instanceof Double) {
            return toElementValueResourceNumber((Double) value);
        } else if (value instanceof LocalDate) {
            return toElementValueResourceString(value.toString());
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }
    }

    private static ProcessElementValueString toElementValueResourceString(String value) {
        return new ProcessElementValueString().setValue(value);
    }

    private static ProcessElementValueNumber toElementValueResourceNumber(Double value) {
        return new ProcessElementValueNumber().setValue(value);
    }

    public static String getElementValueOfAsString(ProcessElementValueAccessor processElementValueAccessor) {
        return findElementValueOfAsString(processElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static Optional<String> findElementValueOfAsString(ProcessElementValueAccessor processElementValueAccessor) {
        Optional<ProcessElementValue> taskElementValue = findElementValueOf(processElementValueAccessor);

        return taskElementValue.map(ProcessHelper::getElementValueOfAsString);
    }

    public static List<String> getElementValueOfAsStringList(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = getElementValuesOf(processElementValueAccessor);

        return processElementValues.stream().map(ProcessHelper::getElementValueOfAsString).collect(toList());
    }

    private static String getElementValueOfAsString(ProcessElementValue elementValue) {
        if (elementValue instanceof ProcessElementValueString) {
            ProcessElementValueString valueString = (ProcessElementValueString) elementValue;

            return valueString.getValue();
        }

        if (elementValue instanceof ProcessElementValueNumber) {
            ProcessElementValueNumber valueNumber = (ProcessElementValueNumber) elementValue;

            return valueNumber.getValue() != null ? valueNumber.getValue().toString() : null;
        }

        throw new KuFlowRestClientException(String.format("elementValue %s is not a String", elementValue));
    }

    public static Optional<Double> findElementValueOfAsDouble(ProcessElementValueAccessor processElementValueAccessor) {
        Optional<ProcessElementValue> taskElementValue = findElementValueOf(processElementValueAccessor);

        return taskElementValue.map(ProcessHelper::getElementValueOfAsDouble);
    }

    public static Double getElementValueOfAsDouble(ProcessElementValueAccessor processElementValueAccessor) {
        return findElementValueOfAsDouble(processElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }


    public static List<Double> getElementValueOfAsDoubleList(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = getElementValuesOf(processElementValueAccessor);

        return processElementValues.stream().map(ProcessHelper::getElementValueOfAsDouble).collect(toList());
    }

    private static Double getElementValueOfAsDouble(ProcessElementValue elementValue) {
        if (elementValue instanceof ProcessElementValueNumber) {
            ProcessElementValueNumber valueNumber = (ProcessElementValueNumber) elementValue;

            return valueNumber.getValue();
        }

        if (elementValue instanceof ProcessElementValueString) {
            ProcessElementValueString valueString = (ProcessElementValueString) elementValue;

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

    public static Optional<LocalDate> findElementValueOfAsLocalDate(ProcessElementValueAccessor processElementValueAccessor) {
        Optional<ProcessElementValue> taskElementValue = findElementValueOf(processElementValueAccessor);

        return taskElementValue.map(ProcessHelper::getElementValueOfAsLocalDate);
    }

    public static LocalDate getElementValueOfAsLocalDate(ProcessElementValueAccessor processElementValueAccessor) {
        return findElementValueOfAsLocalDate(processElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<LocalDate> getElementValueOfAsLocalDateList(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = getElementValuesOf(processElementValueAccessor);

        return processElementValues.stream().map(ProcessHelper::getElementValueOfAsLocalDate).collect(toList());
    }

    private static LocalDate getElementValueOfAsLocalDate(ProcessElementValue elementValue) {
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

    @Nonnull
    private static Optional<ProcessElementValue> findElementValueOf(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = processElementValueAccessor.getElementValues();
        if (processElementValues == null || processElementValues.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(processElementValues.get(0));
    }

    @Nonnull
    private static List<ProcessElementValue> getElementValuesOf(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = processElementValueAccessor.getElementValues();
        if (processElementValues == null || processElementValues.isEmpty()) {
            return new ArrayList<>();
        }

        return processElementValues;
    }
}
