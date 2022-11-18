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
//package com.kuflow.rest.mock;
//
//import com.kuflow.rest.client.resource.TaskElementValueDocumentItemResource;
//import com.kuflow.rest.client.resource.TaskElementValueDocumentResource;
//import com.kuflow.rest.client.resource.TaskElementValueTypeResource;
//import java.util.UUID;
//
//public class ElementValueDocumentFixture {
//
//    public static TaskElementValueDocumentResource getElementValueDocument0() {
//        TaskElementValueDocumentItemResource elementValueDocumentItemResource = new TaskElementValueDocumentItemResource();
//        elementValueDocumentItemResource.setId(UUID.fromString("145fd460-5e52-4160-a0e4-64fd1c9ef380"));
//        elementValueDocumentItemResource.setContentLength(10748L);
//        elementValueDocumentItemResource.setContentType("application/pdf");
//        elementValueDocumentItemResource.setContentPath("contentPath");
//        elementValueDocumentItemResource.setName("name");
//
//        TaskElementValueDocumentResource elementValueDocumentResource = new TaskElementValueDocumentResource();
//        elementValueDocumentResource.setType(TaskElementValueTypeResource.DOCUMENT);
//        elementValueDocumentResource.setValue(elementValueDocumentItemResource);
//
//        return elementValueDocumentResource;
//    }
//}
