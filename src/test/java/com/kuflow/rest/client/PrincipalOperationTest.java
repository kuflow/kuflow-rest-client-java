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
package com.kuflow.rest.client;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.client.models.FindPrincipalsOptions;
import com.kuflow.rest.client.models.Principal;
import com.kuflow.rest.client.models.PrincipalPage;
import com.kuflow.rest.client.models.PrincipalType;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrincipalOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list principals THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListPrincipalsThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/apis/external/v2022-10-08/principals")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("principals-api.list.ok.json"))
        );

        this.kuFlowRestClient.getPrincipalOperations().findPrincipals();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list principals THEN result is correctly parsed")
    public void givenAnAuthenticatedUserWhenListPrincipalsThenResultIsCorrectlyParsed() {
        givenThat(
            get("/apis/external/v2022-10-08/principals")
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("principals-api.list.ok.json"))
        );

        PrincipalPage principals = this.kuFlowRestClient.getPrincipalOperations().findPrincipals();
        assertThat(principals.getMetadata().getTotalElements()).isEqualTo(2);
        assertThat(principals.getContent().get(0).getType()).isEqualTo(PrincipalType.USER);
        assertThat(principals.getContent().get(0).getUser()).isNotNull();
        assertThat(principals.getContent().get(1).getType()).isEqualTo(PrincipalType.APPLICATION);
        assertThat(principals.getContent().get(1).getApplication()).isNotNull();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list principals using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListPrincipalsUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID groupId = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/apis/external/v2022-10-08/principals"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("groupId", equalTo(groupId.toString()))
                .withQueryParam("type", equalTo(PrincipalType.USER.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("principals-api.list.ok.json"))
        );

        FindPrincipalsOptions options = new FindPrincipalsOptions()
            .setSize(30)
            .setPage(2)
            .setSort("order1")
            .setGroupId(groupId)
            .setType(PrincipalType.USER);

        this.kuFlowRestClient.getPrincipalOperations().findPrincipals(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list principals using query params multivalues THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListPrincipalsUsingQueryParamsMultivaluesThenTheQueryParametersAreSend() {
        UUID groupId1 = UUID.randomUUID();
        UUID groupId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/apis/external/v2022-10-08/principals"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("groupId", equalTo(groupId1.toString()))
                .withQueryParam("groupId", equalTo(groupId2.toString()))
                .withQueryParam("type", equalTo(PrincipalType.USER.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("principals-api.list.ok.json"))
        );

        FindPrincipalsOptions options = new FindPrincipalsOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addGroupId(groupId1)
            .addGroupId(groupId2)
            .setType(PrincipalType.USER);

        this.kuFlowRestClient.getPrincipalOperations().findPrincipals(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve a principal THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenRetrieveAPrincipalThenAuthenticationHeaderIsAdded() throws Exception {
        UUID principalId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            get("/apis/external/v2022-10-08/principals/" + principalId)
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("principals-api.retrieve.ok.json"))
        );

        this.kuFlowRestClient.getPrincipalOperations().retrievePrincipal(principalId);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve a principal THEN result is correctly parsed")
    public void givenAnAuthenticatedUserWhenRetrieveAPrincipalThenResultIsCorrectlyParsed() {
        UUID principalId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            get("/apis/external/v2022-10-08/principals/" + principalId)
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("principals-api.retrieve.ok.json"))
        );

        Principal principal = this.kuFlowRestClient.getPrincipalOperations().retrievePrincipal(principalId);
        assertThat(principal.getType()).isEqualTo(PrincipalType.USER);
        assertThat(principal.getUser()).isNotNull();
    }
}
