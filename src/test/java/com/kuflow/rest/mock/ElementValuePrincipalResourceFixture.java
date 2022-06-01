/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.mock;

import com.kuflow.rest.client.resource.PrincipalTypeResource;
import com.kuflow.rest.client.resource.TaskElementValuePrincipalResource;
import java.util.UUID;

public class ElementValuePrincipalResourceFixture {

    public static TaskElementValuePrincipalResource getPrincipalUser0() {
        TaskElementValuePrincipalResource principal = new TaskElementValuePrincipalResource();
        principal.setId(UUID.fromString("9b03e7f2-ecb5-4634-b20f-f528a09ffc9a"));
        principal.setName("My name");
        principal.setType(PrincipalTypeResource.USER);

        return principal;
    }
}
