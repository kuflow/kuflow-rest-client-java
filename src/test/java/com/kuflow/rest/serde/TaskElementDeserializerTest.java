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
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.kuflow.rest.client.resource.TaskElementValueWrapperResource;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskElementDeserializerTest {

    private final ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();

    @Test
    @DisplayName("GIVEN json with null or empty object value WHEN deserialize THEN binding to null")
    public void givenJsonWithNullOrEmptyObjectValueWhenDeserializeThenBindingToNull() throws JsonMappingException, JsonProcessingException {
        {
            String json = "null";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }
        {
            String json = "{}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }
    }

    @Test
    @DisplayName("GIVEN json with string value WHEN deserialize THEN binding object")
    public void givenJsonWithStringValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String json = "{\"value\": \"aString\"}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValid()).isTrue();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
        }

        {
            String json = "{\"valid\": false, \"value\": \"aString\"}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValid()).isFalse();
            assertThat(readValue.getValueAsString()).isEqualTo("aString");
        }

        {
            String json = "{\"value\": \"aString\"}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> readValue.getValueAsDouble());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsDocument());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsMap());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsDoubleList());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsDocumentList());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsMapList());
            assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> readValue.getValueAsStringList());
        }
    }

    @Test
    @DisplayName("GIVEN json with number value WHEN deserialize THEN binding object")
    public void givenJsonWithNumberValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String json = "{\"value\": 123}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDouble()).isEqualTo(123);
        }

        {
            String json = "{\"valid\": false, \"value\": 123.123}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDouble()).isEqualTo(123.123);
        }
    }

    @Test
    @DisplayName("GIVEN json with local date value WHEN deserialize THEN binding object")
    public void givenJsonWithLocalDateValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String json = "{\"value\": \"2022-01-01\"}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsLocalDate()).isEqualTo(LocalDate.of(2022, 1, 1));
        }

        {
            String json = "{\"valid\": false, \"value\": \"2022-01-01\"}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsLocalDate()).isEqualTo(LocalDate.of(2022, 1, 1));
        }
    }

    @Test
    @DisplayName("GIVEN json with form value WHEN deserialize THEN binding object")
    public void givenJsonWithFormValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String json = "{\"value\":{\"form_field_1\":\"string1\",\"form_field_2\":\"string2\"}}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsMap().get("form_field_1")).isEqualTo("string1");
        }

        {
            String json = "[{\"valid\": false, \"value\":{\"form_field_1\":\"string1\",\"form_field_2\":\"string2\"}}]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsMapList().get(0).get("form_field_1")).isEqualTo("string1");
            assertThat(readValue.getValueList().get(0).getValid()).isFalse();
        }
    }

    @Test
    @DisplayName("GIVEN json with document value WHEN deserialize THEN binding object")
    public void givenJsonWithDocumentValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String json =
                "{\"id\":\"30b33063-a1b4-4d07-a789-c5593893eb54\",\"name\":\"document-accent-o-xxx1.pdf\",\"contentPath\":\"tenants/f46ed181-f124-4e85-95be-ceecaf168b4b/2022/03/30/922f6c3d-0b27-4555-a852-e554dc52b4c7\",\"contentType\":\"application/pdf;charset=UTF-8\",\"contentLength\":16}";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
        }
    }

    @Test
    @DisplayName("GIVEN json with array value WHEN deserialize THEN binding object")
    public void givenJsonWithArrayValueWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String json = "[]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNull();
        }

        {
            String json = "[{\"value\": \"aString\"}]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsStringList().size()).isEqualTo(1);
            assertThat(readValue.getValueAsStringList().get(0)).isEqualTo("aString");
        }

        {
            String json = "[{\"value\": \"one\"},{\"value\": \"two\"},{\"value\": \"three\"}]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsStringList().size()).isEqualTo(3);
            assertThat(readValue.getValueAsStringList().get(2)).isEqualTo("three");
        }

        {
            String json = "[{\"value\": 1},{\"value\": 2},{\"value\": 3}]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDoubleList().size()).isEqualTo(3);
            assertThat(readValue.getValueAsDoubleList().get(2)).isEqualTo(3);
        }

        {
            String json = "[{\"valid\": false, \"value\": 1.1}, {\"valid\": false, \"value\": 2.2}, {\"valid\": false, \"value\": 3.3}]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDoubleList().size()).isEqualTo(3);
            assertThat(readValue.getValueAsDoubleList().get(0)).isEqualTo(1.1);
        }

        {
            String json = "[{\"value\": \"2022-01-01\"},{\"value\": \"2022-01-02\"},{\"value\": \"2022-01-03\"}]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsLocalDateList().size()).isEqualTo(3);
            assertThat(readValue.getValueAsLocalDateList().get(2)).isEqualTo(LocalDate.of(2022, 1, 3));
        }
    }

    @Test
    @DisplayName("GIVEN json with array document WHEN deserialize THEN binding object")
    public void givenJsonWithArrayDocumentWhenDeserializeThenBindingObject() throws JsonMappingException, JsonProcessingException {
        {
            String element =
                "{\"id\":\"30b33063-a1b4-4d07-a789-c5593893eb54\",\"name\":\"document-accent-o-xxx1.pdf\",\"contentPath\":\"tenants/f46ed181-f124-4e85-95be-ceecaf168b4b/2022/03/30/922f6c3d-0b27-4555-a852-e554dc52b4c7\",\"contentType\":\"application/pdf;charset=UTF-8\",\"contentLength\":16}";
            String json = "[" + element + "," + element + "]";
            TaskElementValueWrapperResource readValue = this.mapper.readValue(json, TaskElementValueWrapperResource.class);
            assertThat(readValue).isNotNull();
            assertThat(readValue.getValueAsDocumentList().size()).isEqualTo(2);
            assertThat(readValue.getValueAsDocumentList().get(0).getId())
                .isEqualTo(UUID.fromString("30b33063-a1b4-4d07-a789-c5593893eb54"));
        }
    }
}
