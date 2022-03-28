/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.serde;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.client.resource.TaskElementValueWrapperResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskElementDeserializerTest {

    @Test
    @DisplayName("GIVEN json with null or empty object value WHEN deserialize THEN binding to null")
    public void givenJsonWithNullOrEmptyObjectValueWhenDeserializeThenBindingToNull() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "null";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }
        {
            String json = "{}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }
    }

    @Test
    @DisplayName("GIVEN json with string value WHEN deserialize THEN binding object")
    public void givenJsonWithStringValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\": \"aString\"}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isTrue();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
        }

        {
            String json = "{\"valid\": false, \"value\": \"aString\"}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isFalse();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
        }

        {
            String json = "{\"value\": \"aString\"}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> readValue.getValueAsDouble());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsDocument());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsMap());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsDouble());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsDocument());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsMap());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsString());
        }
    }

    @Test
    @DisplayName("GIVEN json with number value WHEN deserialize THEN binding object")
    public void givenJsonWithNumberValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\": 123}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDouble()).isEqualTo(123);
        }

        {
            String json = "{\"valid\": false, \"value\": 123.123}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDouble()).isEqualTo(123.123);
        }
    }

    @Test
    @DisplayName("GIVEN json with form value WHEN deserialize THEN binding object")
    public void givenJsonWithFormValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\":{\"form_field_1\":\"string1\",\"form_field_2\":\"string2\"}}";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsMap().get("form_field_1")).isEqualTo("string1");
        }

        {
            String json = "[{\"valid\": false, \"value\":{\"form_field_1\":\"string1\",\"form_field_2\":\"string2\"}}]";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsMap().get(0).get("form_field_1")).isEqualTo("string1");
            assertThat(readValue.getValues().get(0).getValid()).isFalse();
        }
    }

    @Test
    @DisplayName("GIVEN json with array value WHEN deserialize THEN binding object")
    public void givenJsonWithArrayValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "[]";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
        }

        {
            String json = "[{\"value\": \"aString\"}]";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            readValue.isValid();
            assertThat(readValue.getValuesAsString().size()).isEqualTo(1);
            assertThat(readValue.getValuesAsString().get(0)).isEqualTo("aString");
        }

        {
            String json = "[{\"value\": \"one\"},{\"value\": \"two\"},{\"value\": \"three\"}]";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsString().size()).isEqualTo(3);
            assertThat(readValue.getValuesAsString().get(2)).isEqualTo("three");
        }

        {
            String json = "[{\"value\": 1},{\"value\": 2},{\"value\": 3}]";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsDouble().size()).isEqualTo(3);
            assertThat(readValue.getValuesAsDouble().get(2)).isEqualTo(3);
        }

        {
            String json = "[{\"valid\": false, \"value\": 1.1}, {\"valid\": false, \"value\": 2.2}, {\"valid\": false, \"value\": 3.3}]";
            TaskElementValueWrapperResource readValue = mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsDouble().size()).isEqualTo(3);
            assertThat(readValue.getValuesAsDouble().get(0)).isEqualTo(1.1);
        }
    }
}
