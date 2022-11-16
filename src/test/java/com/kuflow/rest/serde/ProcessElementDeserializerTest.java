///*
// * Copyright (c) 2021-present KuFlow S.L.
// *
// * All rights reserved.
// */
//
//package com.kuflow.rest.serde;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.kuflow.rest.client.KuFlowRestClientException;
//import com.kuflow.rest.client.resource.ProcessElementValueWrapperResource;
//import java.time.LocalDate;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class ProcessElementDeserializerTest {
//
//    private final ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
//
//    @Test
//    @DisplayName("GIVEN json with null WHEN deserialize THEN binding to null")
//    public void givenJsonWithNullValueWhenDeserializeThenBindingToNull() throws Exception {
//        {
//            String json = "null";
//            ProcessElementValueWrapperResource readValue = this.mapper.readValue(json, ProcessElementValueWrapperResource.class);
//            assertThat(readValue).isNull();
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN json with string value WHEN deserialize THEN binding object")
//    public void givenJsonWithStringValueWhenDeserializeThenBindingObject() throws Exception {
//        {
//            String json = "{\"value\": \"aString\",\"type\":\"STRING\"}";
//            ProcessElementValueWrapperResource readValue = this.mapper.readValue(json, ProcessElementValueWrapperResource.class);
//            assertThat(readValue).isNotNull();
//            assertThat(readValue.getValid()).isTrue();
//            assertThat(readValue.getValueAsString()).isEqualTo("aString");
//            assertThatExceptionOfType(KuFlowRestClientException.class).isThrownBy(readValue::getValueAsDouble);
//        }
//
//        {
//            String json = "{\"valid\": false, \"value\": \"aString\",\"type\":\"STRING\"}";
//            ProcessElementValueWrapperResource readValue = this.mapper.readValue(json, ProcessElementValueWrapperResource.class);
//            assertThat(readValue).isNotNull();
//            assertThat(readValue.getValid()).isFalse();
//            assertThat(readValue.getValueAsString()).isEqualTo("aString");
//            assertThatExceptionOfType(KuFlowRestClientException.class).isThrownBy(readValue::getValueAsDouble);
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN json with number value WHEN deserialize THEN binding object")
//    public void givenJsonWithNumberValueWhenDeserializeThenBindingObject() throws Exception {
//        {
//            String json = "{\"value\": 123,\"type\":\"NUMBER\"}";
//            ProcessElementValueWrapperResource readValue = this.mapper.readValue(json, ProcessElementValueWrapperResource.class);
//            assertThat(readValue).isNotNull();
//            assertThat(readValue.getValueAsString()).isEqualTo("123.0");
//            assertThat(readValue.getValueAsDouble()).isEqualTo(123);
//        }
//
//        {
//            String json = "{\"valid\": false, \"value\": 123.123,\"type\":\"NUMBER\"}";
//            ProcessElementValueWrapperResource readValue = this.mapper.readValue(json, ProcessElementValueWrapperResource.class);
//            assertThat(readValue).isNotNull();
//            assertThat(readValue.getValid()).isFalse();
//            assertThat(readValue.getValueAsString()).isEqualTo("123.123");
//            assertThat(readValue.getValueAsDouble()).isEqualTo(123.123);
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN json with number value WHEN deserialize THEN binding object")
//    public void givenJsonWithLocalDateValueWhenDeserializeThenBindingObject() throws Exception {
//        {
//            String json = "{\"value\": \"2022-01-01\",\"type\":\"STRING\"}";
//            ProcessElementValueWrapperResource readValue = this.mapper.readValue(json, ProcessElementValueWrapperResource.class);
//            assertThat(readValue).isNotNull();
//            assertThat(readValue.getValueAsLocalDate()).isEqualTo(LocalDate.of(2022, 1, 1));
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN json with array value WHEN deserialize THEN throw exception")
//    public void givenJsonWithArrayValueWhenDeserializeThenThrowException() {
//        {
//            String json = "[]";
//            assertThatExceptionOfType(JsonMappingException.class)
//                .isThrownBy(() -> this.mapper.readValue(json, ProcessElementValueWrapperResource.class));
//        }
//    }
//}
