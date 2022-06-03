/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.mock;

import com.kuflow.rest.client.resource.PrincipalTypeResource;
import com.kuflow.rest.client.resource.TaskElementValuePrincipalItemResource;
import com.kuflow.rest.client.resource.TaskElementValuePrincipalResource;
import com.kuflow.rest.client.resource.TaskElementValueTypeResource;
import java.util.UUID;

public class ElementValuePrincipalResourceFixture {

    public static TaskElementValuePrincipalResource getPrincipalUser0() {
        TaskElementValuePrincipalItemResource principalItemResource = new TaskElementValuePrincipalItemResource();
        principalItemResource.setId(UUID.fromString("9b03e7f2-ecb5-4634-b20f-f528a09ffc9a"));
        principalItemResource.setName("My name");
        principalItemResource.setType(PrincipalTypeResource.USER);

        TaskElementValuePrincipalResource principalValueResource = new TaskElementValuePrincipalResource();
        principalValueResource.setType(TaskElementValueTypeResource.PRINCIPAL);
        principalValueResource.setValue(principalItemResource);

        return principalValueResource;
    }
}
