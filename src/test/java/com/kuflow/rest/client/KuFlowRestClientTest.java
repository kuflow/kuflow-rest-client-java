/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aMultipart;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.kuflow.rest.client.KuFlowRestClientProperties.Level;
import com.kuflow.rest.client.resource.AuthenticationResource;
import com.kuflow.rest.client.resource.AuthenticationTypeResource;
import com.kuflow.rest.client.resource.SaveElementDocumentCommandResource;
import com.kuflow.rest.client.resource.TaskResource;
import feign.Response;
import java.io.File;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class KuFlowRestClientTest {

    @RegisterExtension
    private static final WireMockExtension wireMockExtension = WireMockExtension
        .newInstance()
        .options(wireMockConfig().dynamicPort().notifier(new Slf4jNotifier(true)).usingFilesUnderDirectory("src/test/resources/wiremock/"))
        .build();

    @BeforeAll
    public static void setupTestSuite() {
        WireMockRuntimeInfo wmRuntimeInfo = wireMockExtension.getRuntimeInfo();
        WireMock.configureFor(wmRuntimeInfo.getHttpPort());
    }

    private KuFlowRestClient kuFlowRestClient;

    @BeforeEach
    public void setupTest() {
        WireMock.resetToDefault();

        this.kuFlowRestClient = this.getKuFlowClient();
    }

    @Test
    @DisplayName("GIVEN json request WHEN invoque a method THEN the json is sent and parsed correctly")
    public void givenJsonRequestWhenInvoqueAMethodThenTheJsonIsSentAndParsedCorrectly() {
        givenThat(
            post("/authentications")
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(matchingJsonPath("$.type", equalTo(AuthenticationTypeResource.ENGINE.getValue())))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("authentication-api.ok.json"))
        );

        AuthenticationResource resource = new AuthenticationResource();
        resource.setType(AuthenticationTypeResource.ENGINE);

        AuthenticationResource authentication = this.kuFlowRestClient.getAuthenticationApi().createAuthentication(resource);
        assertThat(authentication.getToken()).isEqualTo("DUMMY_TOKEN");
        assertThat(authentication.getExpiredAt()).isEqualTo(Instant.parse("2022-03-01T08:42:48Z"));
    }

    @Test
    @DisplayName("GIVEN a multipart request WHEN invoque a method THEN the json and the files are sent and response is parsed correctly")
    public void givenAMultipartRequestWhenInvoqueAMethodThenTheJsonAndTheFilesAreSentAndResponseIsParsedCorrectly() {
        givenThat(
            post(urlMatching("/tasks/([a-z0-9\\-]+)/~actions/save-element-document"))
                .withMultipartRequestBody(aMultipart().withName("json").withHeader("Content-Type", containing("application/json")))
                .withMultipartRequestBody(aMultipart().withName("file").withHeader("Content-Type", containing("text/plain")))
                .willReturn(ok().withBodyFile("task-api.ok.json"))
        );

        UUID taskId = UUID.randomUUID();
        SaveElementDocumentCommandResource json = new SaveElementDocumentCommandResource();
        json.setCode("DOC");
        File file = this.getFile("sample.txt");

        TaskResource task = this.kuFlowRestClient.getTaskApi().actionsSaveElementDocument(taskId, json, file);
        assertThat(task.getId()).isNotNull();
    }

    @Test
    @DisplayName("GIVEN a download request WHEN invoque a method THEN a feign response is passed")
    public void givenADownloadRequestWhenInvoqueAMethodThenAFeignResponseIsPassed() {
        File file = this.getFile("sample.txt");

        givenThat(
            get(urlMatching("/tasks/([a-z0-9\\-]+)/~actions/download-element-document\\?.*"))
                .withQueryParam("documentId", matching("[a-z0-9\\-]+"))
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "text/plain")
                        .withHeader("Content-Length", String.valueOf(file.length()))
                        .withBodyFile("sample.txt")
                )
        );

        UUID taskId = UUID.randomUUID();
        String elementId = UUID.randomUUID().toString();

        Response response = this.kuFlowRestClient.getTaskApi().actionsDownloadElementDocument(taskId, elementId);
        assertThat(response.status()).isEqualTo(200);
        assertThat(response.headers().get("Content-Type").iterator().next()).isEqualTo("text/plain");
        assertThat(response.body().length()).isEqualTo(12);
    }

    private File getFile(String file) {
        try {
            return new File(Objects.requireNonNull(KuFlowRestClientTest.class.getResource("/wiremock/__files/" + file)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private KuFlowRestClient getKuFlowClient() {
        WireMockRuntimeInfo wmRuntimeInfo = wireMockExtension.getRuntimeInfo();
        String endpoint = String.format("http://localhost:%d", wmRuntimeInfo.getHttpPort());

        KuFlowRestClientProperties clientProperties = new KuFlowRestClientProperties();
        clientProperties.setEndpoint(endpoint);
        clientProperties.setApplicationId(UUID.randomUUID().toString());
        clientProperties.setToken(UUID.randomUUID().toString());
        clientProperties.setLoggerLevel(Level.FULL);

        return new KuFlowRestClient(clientProperties);
    }
}
