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

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

public abstract class AbstractOperationTest {

    @RegisterExtension
    private static final WireMockExtension WIRE_MOCK_EXTENSION = WireMockExtension
        .newInstance()
        .options(wireMockConfig().dynamicPort().notifier(new Slf4jNotifier(true)).usingFilesUnderDirectory("src/test/resources/wiremock/"))
        .build();

    @BeforeAll
    public static void setupTestSuite() {
        WireMockRuntimeInfo wmRuntimeInfo = WIRE_MOCK_EXTENSION.getRuntimeInfo();
        WireMock.configureFor(wmRuntimeInfo.getHttpPort());
    }

    protected KuFlowRestClient kuFlowRestClient;

    @BeforeEach
    public void setupTest() {
        WireMock.resetToDefault();

        this.kuFlowRestClient = this.getKuFlowClient();
    }

    private KuFlowRestClient getKuFlowClient() {
        WireMockRuntimeInfo wmRuntimeInfo = WIRE_MOCK_EXTENSION.getRuntimeInfo();
        String endpoint = String.format("http://localhost:%d/apis/external/v2022-10-08", wmRuntimeInfo.getHttpPort());

        HttpLogOptions logOptions = new HttpLogOptions();
        logOptions.setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS);

        return new KuFlowRestClientBuilder()
            .clientId("CLIENT_ID")
            .clientSecret("CLIENT_SECRET")
            .endpoint(endpoint)
            .allowInsecureConnection(true)
            .httpLogOptions(logOptions)
            .buildClient();
    }
}
