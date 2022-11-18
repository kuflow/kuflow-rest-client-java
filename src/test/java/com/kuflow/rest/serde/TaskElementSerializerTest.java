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
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.kuflow.rest.client.resource.TaskElementValueWrapperResource;
//import com.kuflow.rest.mock.ElementValueDocumentFixture;
//import com.kuflow.rest.mock.ElementValuePrincipalResourceFixture;
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.Map;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class TaskElementSerializerTest {
//
//    private final ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
//
//    @Test
//    @DisplayName("GIVEN a null value WHEN instance a task element THEN throws a illegal argument")
//    public void givenANullValueWhenInstanceATaskElementTheThrowsAIllegalArgument() {
//        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> TaskElementValueWrapperResource.of((String) null));
//    }
//
//    @Test
//    @DisplayName("GIVEN element with simple value WHEN serialize THEN json")
//    public void givenTaskElementWithSimpleValueWhenSerializeThenJson() throws Exception {
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of("aString");
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json).isEqualTo("{\"value\":\"aString\",\"valid\":true,\"type\":\"STRING\"}");
//        }
//
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of("aString");
//            elementValuesResource.valid(false);
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json).isEqualTo("{\"value\":\"aString\",\"valid\":false,\"type\":\"STRING\"}");
//        }
//
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(123D);
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json).isEqualTo("{\"value\":123.0,\"valid\":true,\"type\":\"NUMBER\"}");
//        }
//
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(123.123);
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json).isEqualTo("{\"value\":123.123,\"valid\":true,\"type\":\"NUMBER\"}");
//        }
//
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of("one", "two");
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json)
//                .isEqualTo(
//                    "[{\"value\":\"one\",\"valid\":true,\"type\":\"STRING\"},{\"value\":\"two\",\"valid\":true,\"type\":\"STRING\"}]"
//                );
//        }
//
//        {
//            TaskElementValueWrapperResource resource = TaskElementValueWrapperResource.of(LocalDate.of(2022, 1, 1));
//            String json = this.mapper.writeValueAsString(resource);
//            assertThat(json).isEqualTo("{\"value\":\"2022-01-01\",\"valid\":true,\"type\":\"STRING\"}");
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN element with simple value WHEN serialize and no one value is read THEN the tree node is used")
//    public void givenElementWithSimpleValueWhenSerializeAndNoOneValueIsReadThenTheTreeNodeIsUsed() throws Exception {
//        {
//            String jsonExpected = "{\"value\":\"aString\",\"valid\":true,\"type\":\"STRING\"}";
//            TaskElementValueWrapperResource readValue = this.mapper.readValue(jsonExpected, TaskElementValueWrapperResource.class);
//
//            String jsonActual = this.mapper.writeValueAsString(readValue);
//            assertThat(jsonActual).isEqualTo(jsonExpected);
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN element with document value WHEN serialize THEN json")
//    public void givenTaskElementWithDocumentValueWhenSerializeThenJson() throws Exception {
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(
//                ElementValueDocumentFixture.getElementValueDocument0()
//            );
//
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json)
//                .isEqualTo(
//                    "{\"value\":{\"id\":\"145fd460-5e52-4160-a0e4-64fd1c9ef380\",\"uri\":null,\"name\":\"name\",\"contentPath\":\"contentPath\",\"contentType\":\"application/pdf\",\"contentLength\":10748},\"valid\":true,\"type\":\"DOCUMENT\"}"
//                );
//        }
//
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(
//                ElementValueDocumentFixture.getElementValueDocument0(),
//                ElementValueDocumentFixture.getElementValueDocument0(),
//                ElementValueDocumentFixture.getElementValueDocument0()
//            );
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(StringUtils.countMatches(json, ElementValueDocumentFixture.getElementValueDocument0().getValue().getId().toString()))
//                .isEqualTo(3);
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN task element with form value WHEN serialize THEN json")
//    public void givenTaskElementWithFormValueWhenSerializeThenJson() throws Exception {
//        {
//            Map<String, Serializable> form = Map.of("key1", "value", "key2", 12);
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(form);
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json).contains("\"key1\":\"value\"");
//            assertThat(json).contains("\"key2\":12");
//            assertThat(json).endsWith("\"valid\":true,\"type\":\"OBJECT\"}");
//        }
//
//        {
//            Map<String, Serializable> form = Map.of("key1", "value", "key2", 12);
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(form, form);
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(StringUtils.countMatches(json, "key1")).isEqualTo(2);
//        }
//    }
//
//    @Test
//    @DisplayName("GIVEN element with principal value WHEN serialize THEN json")
//    public void givenTaskElementWithPrincipalValueWhenSerializeThenJson() throws Exception {
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(
//                ElementValuePrincipalResourceFixture.getPrincipalUser0()
//            );
//
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(json)
//                .isEqualTo(
//                    "{\"value\":{\"id\":\"9b03e7f2-ecb5-4634-b20f-f528a09ffc9a\",\"type\":\"USER\",\"name\":\"My name\"},\"valid\":true,\"type\":\"PRINCIPAL\"}"
//                );
//        }
//
//        {
//            TaskElementValueWrapperResource elementValuesResource = TaskElementValueWrapperResource.of(
//                ElementValuePrincipalResourceFixture.getPrincipalUser0(),
//                ElementValuePrincipalResourceFixture.getPrincipalUser0(),
//                ElementValuePrincipalResourceFixture.getPrincipalUser0()
//            );
//
//            String json = this.mapper.writeValueAsString(elementValuesResource);
//            assertThat(
//                StringUtils.countMatches(json, ElementValuePrincipalResourceFixture.getPrincipalUser0().getValue().getId().toString())
//            )
//                .isEqualTo(3);
//        }
//    }
//}
