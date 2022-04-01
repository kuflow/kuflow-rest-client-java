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
import com.kuflow.rest.mock.ElementValueDocumentFixture;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskElementSerializerTest {

    private final ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();

    @Test
    @DisplayName("GIVEN a null value WHEN instance a task element THEN throws a illegal argument")
    public void gibenAnullValueWhenInstanceAtaskElementTheThrowsAIllegalArgument() throws JsonMappingException, JsonProcessingException {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> TaskElementValueWrapperResource.of((String) null));
    }

    @Test
    @DisplayName("GIVEN element with simple value WHEN serialize THEN json")
    public void givenTaskElementWithSimpleValueWhenSerializeThenJson() throws JsonMappingException, JsonProcessingException {
        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of("aString");
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":\"aString\"}");
        }

        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of("aString");
            elementValuesResource.valid(false);
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":false,\"value\":\"aString\"}");
        }

        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(123D);
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":123.0}");
        }

        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(123.123);
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":123.123}");
        }

        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of("one", "two");
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("[{\"valid\":true,\"value\":\"one\"},{\"valid\":true,\"value\":\"two\"}]");
        }

        {
            TaskElementValueWrapperResource resource = TaskElementValueWrapperResource.of(LocalDate.of(2022, 1, 1));
            String json = this.mapper.writeValueAsString(resource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":\"2022-01-01\"}");
        }
    }

    @Test
    @DisplayName("GIVEN element with document value WHEN serialize THEN json")
    public void givenTaskElementWithDocumentValueWhenSerializeThenJson() throws JsonMappingException, JsonProcessingException {
        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(
                ElementValueDocumentFixture.getElementValueDocument0()
            );

            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json)
                .isEqualTo(
                    "{\"id\":\"145fd460-5e52-4160-a0e4-64fd1c9ef380\"," +
                    "\"name\":\"name\"," +
                    "\"contentPath\":\"contentPath\"," +
                    "\"contentType\":\"application/pdf\"," +
                    "\"contentLength\":10748}"
                );
        }

        {
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(
                ElementValueDocumentFixture.getElementValueDocument0(),
                ElementValueDocumentFixture.getElementValueDocument0(),
                ElementValueDocumentFixture.getElementValueDocument0()
            );
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(StringUtils.countMatches(json, ElementValueDocumentFixture.getElementValueDocument0().getId().toString()))
                .isEqualTo(3);
        }
    }

    @Test
    @DisplayName("GIVEN task element with form value WHEN serialize THEN json")
    public void givenTaskElementWithFormValueWhenSerializeThenJson() throws JsonMappingException, JsonProcessingException {
        {
            Map<String, Serializable> form = Map.of("key1", "value", "key2", 12);
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(form);
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(json).contains("\"key1\":\"value\"");
            assertThat(json).contains("\"key2\":12");
            assertThat(json).startsWith("{\"valid\":true");
        }

        {
            Map<String, Serializable> form = Map.of("key1", "value", "key2", 12);
            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(List.of(form, form));
            String json = this.mapper.writeValueAsString(elementValuesResource);
            assertThat(StringUtils.countMatches(json, "key1")).isEqualTo(2);
        }
    }
}
