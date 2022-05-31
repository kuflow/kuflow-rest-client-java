/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.mock;

import com.kuflow.rest.client.resource.PrincipalResource;
import com.kuflow.rest.client.resource.PrincipalTypeResource;
import java.util.UUID;

public class PrincipalFixture {

    public static PrincipalResource getPrincipalUser0() {
        PrincipalResource principal = new PrincipalResource();
        principal.setId(UUID.fromString("9b03e7f2-ecb5-4634-b20f-f528a09ffc9a"));
        principal.setName("My name");
        principal.setType(PrincipalTypeResource.USER);

        return principal;
    }
}
