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
import com.kuflow.rest.client.resource.ProcessElementValueWrapperResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessElementDeserializerTest {

    @Test
    @DisplayName("GIVEN json with null or empty object value WHEN deserialize THEN binding to null")
    public void givenJsonWithNullOrEmptyObjectValueWhenDeserializeThenBindingToNull() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "null";
            ProcessElementValueWrapperResource readValue = mapper.readValue(json, ProcessElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }
        {
            String json = "{}";
            ProcessElementValueWrapperResource readValue = mapper.readValue(json, ProcessElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }
    }

    @Test
    @DisplayName("GIVEN json with string value WHEN deserialize THEN binding object")
    public void givenJsonWithStringValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\": \"aString\"}";
            ProcessElementValueWrapperResource readValue = mapper.readValue(json, ProcessElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isTrue();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
            assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> readValue.getValueAsDouble());
        }

        {
            String json = "{\"valid\": false, \"value\": \"aString\"}";
            ProcessElementValueWrapperResource readValue = mapper.readValue(json, ProcessElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isFalse();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
            assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> readValue.getValueAsDouble());
        }
    }

    @Test
    @DisplayName("GIVEN json with number value WHEN deserialize THEN binding object")
    public void givenJsonWithNumberValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "{\"value\": 123}";
            ProcessElementValueWrapperResource readValue = mapper.readValue(json, ProcessElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsString()).isEqualTo("123.0");
            assertThat(readValue.getValueAsDouble()).isEqualTo(123);
        }

        {
            String json = "{\"valid\": false, \"value\": 123.123}";
            ProcessElementValueWrapperResource readValue = mapper.readValue(json, ProcessElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.isValid()).isFalse();
            assertThat(readValue.getValueAsString()).isEqualTo("123.123");
            assertThat(readValue.getValueAsDouble()).isEqualTo(123.123);
        }
    }

    @Test
    @DisplayName("GIVEN json with array value WHEN deserialize THEN throw exception")
    public void givenJsonWithArrayValueWhenDeserializeThenThrowException() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            String json = "[]";
            assertThatExceptionOfType(JsonMappingException.class)
                .isThrownBy(() -> mapper.readValue(json, ProcessElementValueWrapperResource.class));
        }
    }
}
