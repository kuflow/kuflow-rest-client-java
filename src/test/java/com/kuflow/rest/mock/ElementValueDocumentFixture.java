/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.mock;

import com.kuflow.rest.client.resource.TaskElementValueDocumentItemResource;
import com.kuflow.rest.client.resource.TaskElementValueDocumentResource;
import com.kuflow.rest.client.resource.TaskElementValueTypeResource;

public class ElementValueDocumentFixture {

    public static TaskElementValueDocumentResource getElementValueDocument0() {
        TaskElementValueDocumentItemResource elementValueDocumentItemResource = new TaskElementValueDocumentItemResource();
        elementValueDocumentItemResource.setId("145fd460-5e52-4160-a0e4-64fd1c9ef380");
        elementValueDocumentItemResource.setContentLength(10748L);
        elementValueDocumentItemResource.setContentType("application/pdf");
        elementValueDocumentItemResource.setContentPath("contentPath");
        elementValueDocumentItemResource.setName("name");

        TaskElementValueDocumentResource elementValueDocumentResource = new TaskElementValueDocumentResource();
        elementValueDocumentResource.setType(TaskElementValueTypeResource.DOCUMENT);
        elementValueDocumentResource.setValue(elementValueDocumentItemResource);

        return elementValueDocumentResource;
    }
}
