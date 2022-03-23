/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.mock;

import com.kuflow.rest.client.resource.ElementValueDocumentResource;
import java.util.UUID;

public class ElementValueDocumentFixture {

    public static ElementValueDocumentResource getElementValueDocument0() {
        ElementValueDocumentResource elementValueDocumentResource = new ElementValueDocumentResource();
        elementValueDocumentResource.setId(UUID.fromString("145fd460-5e52-4160-a0e4-64fd1c9ef380"));
        elementValueDocumentResource.setContentLength(10748L);
        elementValueDocumentResource.setContentPath("contentPath");
        elementValueDocumentResource.setName("name");

        return elementValueDocumentResource;
    }
}
