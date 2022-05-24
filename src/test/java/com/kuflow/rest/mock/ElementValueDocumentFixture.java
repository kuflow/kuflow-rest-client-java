/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.mock;

import com.kuflow.rest.client.resource.TaskElementValueDocumentResource;

public class ElementValueDocumentFixture {

    public static TaskElementValueDocumentResource getElementValueDocument0() {
        TaskElementValueDocumentResource elementValueDocumentResource = new TaskElementValueDocumentResource();
        elementValueDocumentResource.setId("145fd460-5e52-4160-a0e4-64fd1c9ef380");
        elementValueDocumentResource.setContentLength(10748L);
        elementValueDocumentResource.setContentType("application/pdf");
        elementValueDocumentResource.setContentPath("contentPath");
        elementValueDocumentResource.setName("name");

        return elementValueDocumentResource;
    }
}
