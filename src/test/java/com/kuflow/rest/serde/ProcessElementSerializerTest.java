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
import com.kuflow.rest.client.resource.ProcessElementValueWrapperResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessElementSerializerTest {

    @Test
    @DisplayName("GIVEN element values as simple object WHEN serialize THEN json string")
    public void givenElementValuesAsSimpleObjectWhenserializeThenJsonString() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        {
            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of("aString");
            String json = mapper.writeValueAsString(resource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":\"aString\"}");
        }

        {
            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of("aString");
            resource.valid(false);
            String json = mapper.writeValueAsString(resource);
            assertThat(json).isEqualTo("{\"valid\":false,\"value\":\"aString\"}");
        }

        {
            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of(123D);
            String json = mapper.writeValueAsString(resource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":123.0}");
        }

        {
            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of(123.123);
            String json = mapper.writeValueAsString(resource);
            assertThat(json).isEqualTo("{\"valid\":true,\"value\":123.123}");
        }
    }
}
