/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
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
//package com.kuflow.rest.mock;
//
//import com.kuflow.rest.client.resource.PrincipalTypeResource;
//import com.kuflow.rest.client.resource.TaskElementValuePrincipalItemResource;
//import com.kuflow.rest.client.resource.TaskElementValuePrincipalResource;
//import com.kuflow.rest.client.resource.TaskElementValueTypeResource;
//import java.util.UUID;
//
//public class ElementValuePrincipalResourceFixture {
//
//    public static TaskElementValuePrincipalResource getPrincipalUser0() {
//        TaskElementValuePrincipalItemResource principalItemResource = new TaskElementValuePrincipalItemResource();
//        principalItemResource.setId(UUID.fromString("9b03e7f2-ecb5-4634-b20f-f528a09ffc9a"));
//        principalItemResource.setName("My name");
//        principalItemResource.setType(PrincipalTypeResource.USER);
//
//        TaskElementValuePrincipalResource principalValueResource = new TaskElementValuePrincipalResource();
//        principalValueResource.setType(TaskElementValueTypeResource.PRINCIPAL);
//        principalValueResource.setValue(principalItemResource);
//
//        return principalValueResource;
//    }
//}
