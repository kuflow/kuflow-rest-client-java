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
import com.kuflow.rest.client.resource.ElementValuesResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeserializerTest {

    @Test
    @DisplayName("GIVEN json with null or empty object value WHEN deserialize THEN binding to null")
    public void givenJsonWithNullOrEmptyObjectValueWhenDeserializeThenBindingToNull() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "null";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNull();
        }
        {
            String json = "{}";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNull();
        }
    }

    @Test
    @DisplayName("GIVEN json with string value WHEN deserialize THEN binding object")
    public void givenJsonWithStringValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\": \"aString\"}";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isTrue();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsInteger());
        }

        {
            String json = "{\"valid\": false, \"value\": \"aString\"}";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isFalse();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
        }
    }

    @Test
    @DisplayName("GIVEN json with integer value WHEN deserialize THEN binding object")
    public void givenJsonWithNumberValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\": 123}";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsString());
            assertThat(readValue.getValueAsInteger()).isEqualTo(123);
        }

        {
            String json = "{\"valid\": false, \"value\": 123.123}";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsString());
            assertThat(readValue.getValueAsDouble()).isEqualTo(123.123d);
        }
    }

    @Test
    @DisplayName("GIVEN json with array value WHEN deserialize THEN binding object")
    public void givenJsonWithArrayValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "[]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
        }

        {
            String json = "[{\"value\": \"aString\"}]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsString().size()).isEqualTo(1);
            assertThat(readValue.getValuesAsString().get(0)).isEqualTo("aString");
        }

        {
            String json = "[{\"value\": \"one\"},{\"value\": \"two\"},{\"value\": \"three\"}]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsString().size()).isEqualTo(3);
            assertThat(readValue.getValuesAsString().get(2)).isEqualTo("three");
        }

        {
            String json = "[{\"value\": 1},{\"value\": 2},{\"value\": 3}]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsString());
            assertThat(readValue.getValuesAsInteger().size()).isEqualTo(3);
            assertThat(readValue.getValuesAsInteger().get(2)).isEqualTo(3);
        }

        {
            String json = "[{\"valid\": false, \"value\": 1.1}, {\"valid\": false, \"value\": 2.2}, {\"valid\": false, \"value\": 3.3}]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsString());
            assertThat(readValue.getValuesAsDouble().size()).isEqualTo(3);
            assertThat(readValue.getValuesAsDouble().get(0)).isEqualTo(1.1);
        }
    }

    @Test
    @DisplayName("GIVEN json with list value WHEN deserialize THEN binding object")
    public void givenJsonWithListValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "[{\"value\": \"aString\"}]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValuesAsString()).contains("aString");
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValuesAsInteger());
        }

        {
            String json = "[{\"valid\": false, \"value\": \"aString\"}]";
            ElementValuesResource readValue = mapper.readValue(json, ElementValuesResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValues().get(0).getValid()).isFalse();
        }
    }
}
