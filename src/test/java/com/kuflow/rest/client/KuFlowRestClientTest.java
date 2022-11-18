/*
 * The MIT License
 * Copyright © 2022-present KuFlow S.L.
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

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.kuflow.rest.client.model.Document;
import com.kuflow.rest.client.models.Process;
import com.kuflow.rest.client.models.ProcessDefinitionSummary;
import com.kuflow.rest.client.models.Task;
import com.kuflow.rest.client.models.TaskElementValueString;
import com.kuflow.rest.client.models.TaskSaveElementCommand;
import com.kuflow.rest.client.models.TaskSaveElementValueDocumentCommand;
import com.kuflow.rest.client.models.TasksDefinitionSummary;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class KuFlowRestClientTest {

    //
    //    @RegisterExtension
    //    private static final WireMockExtension wireMockExtension = WireMockExtension
    //        .newInstance()
    //        .options(wireMockConfig().dynamicPort().notifier(new Slf4jNotifier(true)).usingFilesUnderDirectory("src/test/resources/wiremock/"))
    //        .build();
    //
    //    @BeforeAll
    //    public static void setupTestSuite() {
    //        WireMockRuntimeInfo wmRuntimeInfo = wireMockExtension.getRuntimeInfo();
    //        WireMock.configureFor(wmRuntimeInfo.getHttpPort());
    //    }
    //
    //    private KuFlowRestClient kuFlowRestClient;
    //
    //    @BeforeEach
    //    public void setupTest() {
    //        WireMock.resetToDefault();
    //
    //        this.kuFlowRestClient = this.getKuFlowClient();
    //    }

    @Test
    public void dummy() throws IOException {
        HttpLogOptions logOptions = new HttpLogOptions();
        logOptions.setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS);

        KuFlowRestClient kuFlowRestClient = new KuFlowRestClientBuilder()
            .username("1e96158f-3dfd-4c4c-8aa2-4c61a7127bcf")
            .password("*pn8BMkt-KI0/08")
            .endpoint("http://localhost:8080/apis/external/v2022-10-08")
            .httpLogOptions(logOptions)
            .buildClient();

        Process process = new Process()
            .setId(UUID.fromString("cf9b199e-18b7-489a-811a-0132edef21b3"))
            .setProcessDefinition(new ProcessDefinitionSummary().setId(UUID.fromString("be35212b-deb8-4719-a10d-b8550219d156")));
        Process processCreated = kuFlowRestClient.getProcessOperations().createProcess(process);

        System.out.println(processCreated);

        Task task = new Task()
            .setId(UUID.fromString("a81b7d61-9619-4e1c-b2f4-e0e7de03d310"))
            .setProcessId(processCreated.getId())
            .setTaskDefinition(new TasksDefinitionSummary().setCode("TASK_0001"));

        List<Map<String, Object>> hashMaps = List.of(new HashMap<>());
        task.putElementValueAsMapList("", hashMaps);
        Task taskCreated = kuFlowRestClient.getTaskOperations().createTask(task);

        System.out.println(taskCreated);

        //        kuFlowRestClient.getTaskOperations().actionsTaskClaim(taskCreated.getId());

        // TODO IMPORVE HOW VALUES IS ADDED VALUES
        //        TaskSaveElementCommand command1 = new TaskSaveElementCommand()
        //            .setElementDefinitionCode("TEXT_001")
        //            .setValues(
        //                List.of(new TaskElementValueString().setValue("Valor del bueno"))
        //            );
        //        kuFlowRestClient.getTaskOperations().actionsTaskSaveElement(taskCreated.getId(), command1);

        TaskSaveElementCommand command2 = new TaskSaveElementCommand()
            .setElementDefinitionCode("TEXT_002")
            .setElementValues(
                List.of(
                    new TaskElementValueString().setValue("Valor del bueno uno"),
                    new TaskElementValueString().setValue("Valor del bueno dos")
                )
            );
        kuFlowRestClient.getTaskOperations().actionsTaskSaveElement(taskCreated.getId(), command2);

        //        TaskSaveElementCommand command3 = new TaskSaveElementCommand()
        //            .setElementDefinitionCode("NUMBER_001")
        //            .setValues(
        //                List.of(
        //                    new TaskElementValueNumber().setValue(50.0)
        //                )
        //            );
        //        kuFlowRestClient.getTaskOperations().actionsTaskSaveElement(taskCreated.getId(), command3);

        TaskSaveElementValueDocumentCommand command4 = new TaskSaveElementValueDocumentCommand().setElementDefinitionCode("DOC_001");

        BinaryData file = BinaryData.fromFile(Path.of("/Users/kuflow/Downloads/bugs-bunny.png"));
        Document document = new Document().setFileContent(file).setFileName("bugs-bunny.png").setContentType("image/png");
        kuFlowRestClient.getTaskOperations().actionsTaskSaveElementValueDocument(taskCreated.getId(), command4, document);
    }
    //
    //    @Test
    //    @DisplayName("GIVEN json request WHEN invoque a method THEN the json is sent and parsed correctly")
    //    public void givenJsonRequestWhenInvoqueAMethodThenTheJsonIsSentAndParsedCorrectly() {
    //        givenThat(
    //            post("/authentications")
    //                .withHeader("Content-Type", containing("application/json"))
    //                .withRequestBody(matchingJsonPath("$.type", equalTo(AuthenticationTypeResource.ENGINE.getValue())))
    //                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("authentication-api.ok.json"))
    //        );
    //
    //        AuthenticationResource resource = new AuthenticationResource();
    //        resource.setType(AuthenticationTypeResource.ENGINE);
    //
    //        AuthenticationResource authentication = this.kuFlowRestClient.getAuthenticationApi().createAuthentication(resource);
    //        assertThat(authentication.getToken()).isEqualTo("DUMMY_TOKEN");
    //        assertThat(authentication.getExpiredAt()).isEqualTo(Instant.parse("2022-03-01T08:42:48Z"));
    //    }
    //
    //    @Test
    //    @DisplayName("GIVEN a multipart request WHEN invoque a method THEN the json and the files are sent and response is parsed correctly")
    //    public void givenAMultipartRequestWhenInvoqueAMethodThenTheJsonAndTheFilesAreSentAndResponseIsParsedCorrectly() {
    //        givenThat(
    //            post(urlMatching("/tasks/([a-z0-9\\-]+)/~actions/save-element-value-document"))
    //                .withMultipartRequestBody(aMultipart().withName("json").withHeader("Content-Type", containing("application/json")))
    //                .withMultipartRequestBody(aMultipart().withName("file").withHeader("Content-Type", containing("text/plain")))
    //                .willReturn(ok().withBodyFile("task-api.ok.json"))
    //        );
    //
    //        UUID taskId = UUID.randomUUID();
    //        SaveElementValueDocumentCommandResource command = new SaveElementValueDocumentCommandResource();
    //        command.setCode("DOC");
    //        File file = this.getFile("sample.txt");
    //
    //        TaskResource task = this.kuFlowRestClient.getTaskApi().actionsSaveTaskElementValueDocument(taskId, command, file);
    //        assertThat(task.getId()).isNotNull();
    //    }
    //
    //    @Test
    //    @DisplayName("GIVEN a download request WHEN invoque a method THEN a feign response is passed")
    //    public void givenADownloadRequestWhenInvoqueAMethodThenAFeignResponseIsPassed() {
    //        File file = this.getFile("sample.txt");
    //
    //        givenThat(
    //            get(urlMatching("/tasks/([a-z0-9\\-]+)/~actions/download-element-value-document\\?.*"))
    //                .withQueryParam("documentId", matching("[a-z0-9\\-]+"))
    //                .willReturn(
    //                    ok()
    //                        .withHeader("Content-Type", "text/plain")
    //                        .withHeader("Content-Length", String.valueOf(file.length()))
    //                        .withBodyFile("sample.txt")
    //                )
    //        );
    //
    //        UUID taskId = UUID.randomUUID();
    //        UUID elementId = UUID.randomUUID();
    //
    //        Response response = this.kuFlowRestClient.getTaskApi().actionsDownloadTaskElementValueDocument(taskId, elementId);
    //        assertThat(response.status()).isEqualTo(200);
    //        assertThat(response.headers().get("Content-Type").iterator().next()).isEqualTo("text/plain");
    //        assertThat(response.body().length()).isEqualTo(12);
    //    }
    //
    //    private File getFile(String file) {
    //        try {
    //            return new File(Objects.requireNonNull(KuFlowRestClientTest.class.getResource("/wiremock/__files/" + file)).toURI());
    //        } catch (URISyntaxException e) {
    //            throw new RuntimeException(e);
    //        }
    //    }
    //
    //    private KuFlowRestClient getKuFlowClient() {
    //        WireMockRuntimeInfo wmRuntimeInfo = wireMockExtension.getRuntimeInfo();
    //        String endpoint = String.format("http://localhost:%d", wmRuntimeInfo.getHttpPort());
    //
    //        KuFlowRestClientProperties clientProperties = new KuFlowRestClientProperties();
    //        clientProperties.setEndpoint(endpoint);
    //        clientProperties.setApplicationId(UUID.randomUUID().toString());
    //        clientProperties.setToken(UUID.randomUUID().toString());
    //        clientProperties.setLoggerLevel(Level.FULL);
    //
    //        return new KuFlowRestClient(clientProperties);
    //    }
}
