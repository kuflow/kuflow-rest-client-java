/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.serde;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.client.resource.ElementValueWrapperResource;
import com.kuflow.rest.mock.ElementValueDocumentFixture;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SerializerTest {

    @Test
    @DisplayName("GIVEN element values as simple object WHEN serialize THEN json string")
    public void givenElementValuesAsSimpleObjectWhenserializeThenJsonString() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of("aString");
            String json = mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":\"aString\"}");
        }

        {
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of("aString");
            elementValuesResource.valid(false);
            String json = mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":false,\"value\":\"aString\"}");
        }

        {
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of(123);
            String json = mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":123}");
        }

        {
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of(123.123);
            String json = mapper.writeValueAsString(elementValuesResource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":123.123}");
        }
    }

    @Test
    @DisplayName("GIVEN element values as simple objects WHEN serialize THEN json string")
    public void givenElementValuesAsSimpleObjectsWhenserializeThenJsonString() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of("one", "two");
            String json = mapper.writeValueAsString(elementValuesResource);
            System.out.println(json);
            assertThat(json).isEqualTo("[{\"valid\":true,\"value\":\"one\"},{\"valid\":true,\"value\":\"two\"}]");
        }
    }

    @Test
    @DisplayName("GIVEN element values as Documents WHEN serialize THEN json string")
    public void givenElementValuesAsDocumentsWhenserializeThenJsonString() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        {
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of(ElementValueDocumentFixture.getElementValueDocument0());

            String json = mapper.writeValueAsString(elementValuesResource);
            System.out.println(json);
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
            ElementValueWrapperResource elementValuesResource = ElementValueWrapperResource.of(
                ElementValueDocumentFixture.getElementValueDocument0(),
                ElementValueDocumentFixture.getElementValueDocument0(),
                ElementValueDocumentFixture.getElementValueDocument0()
            );
            String json = mapper.writeValueAsString(elementValuesResource);
            assertThat(StringUtils.countMatches(json, ElementValueDocumentFixture.getElementValueDocument0().getId().toString()))
                .isEqualTo(3);
        }
    }
}
