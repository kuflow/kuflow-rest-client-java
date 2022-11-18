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
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.kuflow.rest.client.resource.ProcessElementValueWrapperResource;
//import java.time.LocalDate;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class ProcessElementSerializerTest {
//
//    private final ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
//
//    @Test
//    @DisplayName("GIVEN element values as simple object WHEN serialize THEN json string")
//    public void givenElementValuesAsSimpleObjectWhenSerializeThenJsonString() throws Exception {
//        {
//            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of("aString");
//            String json = this.mapper.writeValueAsString(resource);
//            assertThat(json).isEqualTo("{\"value\":\"aString\",\"valid\":true,\"type\":\"STRING\"}");
//        }
//
//        {
//            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of("aString");
//            resource.valid(false);
//            String json = this.mapper.writeValueAsString(resource);
//            assertThat(json).isEqualTo("{\"value\":\"aString\",\"valid\":false,\"type\":\"STRING\"}");
//        }
//
//        {
//            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of(123D);
//            String json = this.mapper.writeValueAsString(resource);
//            assertThat(json).isEqualTo("{\"value\":123.0,\"valid\":true,\"type\":\"NUMBER\"}");
//        }
//
//        {
//            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of(123.123);
//            String json = this.mapper.writeValueAsString(resource);
//            assertThat(json).isEqualTo("{\"value\":123.123,\"valid\":true,\"type\":\"NUMBER\"}");
//        }
//
//        {
//            ProcessElementValueWrapperResource resource = ProcessElementValueWrapperResource.of(LocalDate.of(2022, 1, 1));
//            String json = this.mapper.writeValueAsString(resource);
//            assertThat(json).isEqualTo("{\"value\":\"2022-01-01\",\"valid\":true,\"type\":\"STRING\"}");
//        }
//    }
//}
