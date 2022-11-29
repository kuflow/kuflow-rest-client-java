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

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.client.models.Authentication;
import com.kuflow.rest.client.models.AuthenticationType;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthenticationOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN create a authentication THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenCreateAAuthenticationThenAuthenticationHeaderIsAdded() {
        givenThat(
            post("/apis/external/v2022-10-08/authentications")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("authentication-api.ok.json"))
        );

        Authentication authentication = new Authentication();
        authentication.setType(AuthenticationType.ENGINE);
        this.kuFlowRestClient.getAuthenticationOperations().createAuthentication(authentication);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN create a authentication THEN authentication is created")
    public void givenAnAuthenticatedUserWhenCreateAAuthenticationThenAuthenticationIsCreated() {
        givenThat(
            post("/apis/external/v2022-10-08/authentications")
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(matchingJsonPath("$.type", equalTo(AuthenticationType.ENGINE.toString())))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("authentication-api.ok.json"))
        );

        Authentication authentication = new Authentication();
        authentication.setType(AuthenticationType.ENGINE);

        Authentication authenticationCreated = this.kuFlowRestClient.getAuthenticationOperations().createAuthentication(authentication);
        assertThat(authenticationCreated.getToken()).isEqualTo("DUMMY_TOKEN");
        assertThat(authenticationCreated.getExpiredAt()).isEqualTo(OffsetDateTime.parse("2022-03-01T08:42:48Z"));
    }
}
