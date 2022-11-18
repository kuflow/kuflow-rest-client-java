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

import static com.azure.core.util.AuthorizationChallengeHandler.WWW_AUTHENTICATE;
import static java.util.stream.Collectors.toList;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.client.traits.ConfigurationTrait;
import com.azure.core.client.traits.EndpointTrait;
import com.azure.core.client.traits.HttpTrait;
import com.azure.core.credential.BasicAuthenticationCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpPipelinePosition;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.AddDatePolicy;
import com.azure.core.http.policy.AddHeadersFromContextPolicy;
import com.azure.core.http.policy.AddHeadersPolicy;
import com.azure.core.http.policy.BearerTokenAuthenticationPolicy;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.HttpPolicyProviders;
import com.azure.core.http.policy.RequestIdPolicy;
import com.azure.core.http.policy.RetryOptions;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.HttpClientOptions;
import com.azure.core.util.builder.ClientBuilderUtil;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.SerializerAdapter;
import com.kuflow.rest.client.implementation.KuFlowClientImpl;
import com.kuflow.rest.client.implementation.KuFlowClientImplBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * KuFlowRestClientBuilder that creates SmsAsyncClient and SmsClient.
 */
@ServiceClientBuilder(serviceClients = { KuFlowRestClient.class })
public final class KuFlowRestClientBuilder
    implements ConfigurationTrait<KuFlowRestClientBuilder>, EndpointTrait<KuFlowRestClientBuilder>, HttpTrait<KuFlowRestClientBuilder> {

    private static final String SDK_NAME = "name";
    private static final String SDK_VERSION = "version";

    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("kuflow-rest-client.properties");

    private final ClientLogger logger = new ClientLogger(KuFlowRestClientBuilder.class);
    private String endpoint;
    private String username;
    private String password;
    private HttpClient httpClient;
    private HttpLogOptions httpLogOptions = new HttpLogOptions();
    private HttpPipeline pipeline;

    private Configuration configuration;
    private final List<HttpPipelinePolicy> pipelinePolicies = new ArrayList<>();
    private ClientOptions clientOptions;
    private RetryPolicy retryPolicy;
    private RetryOptions retryOptions;

    private SerializerAdapter serializerAdapter;

    /**
     * Set endpoint of the service
     *
     * @param endpoint url of the service
     * @return KuFlowRestClientBuilder
     */
    @Override
    public KuFlowRestClientBuilder endpoint(String endpoint) {
        this.endpoint = Objects.requireNonNull(endpoint, "'endpoint' cannot be null.");
        return this;
    }

    /**
     * Set endpoint of the service
     *
     * @param username username credentials
     * @return KuFlowRestClientBuilder
     */
    public KuFlowRestClientBuilder username(String username) {
        this.username = Objects.requireNonNull(username, "'username' cannot be null.");
        return this;
    }

    /**
     * Set endpoint of the service
     *
     * @param password username credentials
     * @return KuFlowRestClientBuilder
     */
    public KuFlowRestClientBuilder password(String password) {
        this.password = Objects.requireNonNull(password, "'password' cannot be null.");
        return this;
    }

    /**
     * Sets the {@link HttpPipeline} to use for the service client.
     *
     * <p><strong>Note:</strong> It is important to understand the precedence order of the HttpTrait APIs. In
     * particular, if a {@link HttpPipeline} is specified, this takes precedence over all other APIs in the trait, and
     * they will be ignored. If no {@link HttpPipeline} is specified, a HTTP pipeline will be constructed internally
     * based on the settings provided to this trait. Additionally, there may be other APIs in types that implement this
     * trait that are also ignored if an {@link HttpPipeline} is specified, so please be sure to refer to the
     * documentation of types that implement this trait to understand the full set of implications.</p>
     * <p>
     * If a pipeline is not supplied, the credential and httpClient fields must be set
     * </p>
     *
     * @param pipeline {@link HttpPipeline} to use for sending service requests and receiving responses.
     * @return KuFlowRestClientBuilder
     */
    @Override
    public KuFlowRestClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = Objects.requireNonNull(pipeline, "'pipeline' cannot be null.");
        return this;
    }

    /**
     * Sets the retry policy to use (using the RetryPolicy type).
     * <p>
     * Setting this is mutually exclusive with using {@link #retryOptions(RetryOptions)}.
     *
     * @param retryPolicy object to be applied
     * @return KuFlowRestClientBuilder
     */
    public KuFlowRestClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Sets the {@link RetryOptions} for all the requests made through the client.
     *
     * <p><strong>Note:</strong> It is important to understand the precedence order of the HttpTrait APIs. In
     * particular, if a {@link HttpPipeline} is specified, this takes precedence over all other APIs in the trait, and
     * they will be ignored. If no {@link HttpPipeline} is specified, a HTTP pipeline will be constructed internally
     * based on the settings provided to this trait. Additionally, there may be other APIs in types that implement this
     * trait that are also ignored if an {@link HttpPipeline} is specified, so please be sure to refer to the
     * documentation of types that implement this trait to understand the full set of implications.</p>
     * <p>
     * Setting this is mutually exclusive with using {@link #retryPolicy(RetryPolicy)}.
     *
     * @param retryOptions The {@link RetryOptions} to use for all the requests made through the client.
     * @return KuFlowRestClientBuilder
     */
    @Override
    public KuFlowRestClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * Sets the configuration object used to retrieve environment configuration values during building of the client.
     *
     * @param configuration Configuration store used to retrieve environment configurations.
     * @return the updated KuFlowRestClientBuilder object
     */
    @Override
    public KuFlowRestClientBuilder configuration(Configuration configuration) {
        this.configuration = Objects.requireNonNull(configuration, "'configuration' cannot be null.");
        return this;
    }

    /**
     * Sets the {@link HttpLogOptions logging configuration} to use when sending and receiving requests to and from
     * the service. If a {@code logLevel} is not provided, default value of {@link HttpLogDetailLevel#NONE} is set.
     *
     * <p><strong>Note:</strong> It is important to understand the precedence order of the HttpTrait APIs. In
     * particular, if a {@link HttpPipeline} is specified, this takes precedence over all other APIs in the trait, and
     * they will be ignored. If no {@link HttpPipeline} is specified, a HTTP pipeline will be constructed internally
     * based on the settings provided to this trait. Additionally, there may be other APIs in types that implement this
     * trait that are also ignored if an {@link HttpPipeline} is specified, so please be sure to refer to the
     * documentation of types that implement this trait to understand the full set of implications.</p>
     *
     * @param logOptions The {@link HttpLogOptions logging configuration} to use when sending and receiving requests to
     * and from the service.
     * @return the updated KuFlowRestClientBuilder object
     */
    @Override
    public KuFlowRestClientBuilder httpLogOptions(HttpLogOptions logOptions) {
        this.httpLogOptions = Objects.requireNonNull(logOptions, "'logOptions' cannot be null.");
        return this;
    }

    /**
     * Allows for setting common properties such as application ID, headers, proxy configuration, etc. Note that it is
     * recommended that this method be called with an instance of the {@link HttpClientOptions}
     * class (a subclass of the {@link ClientOptions} base class). The HttpClientOptions subclass provides more
     * configuration options suitable for HTTP clients, which is applicable for any class that implements this HttpTrait
     * interface.
     *
     * <p><strong>Note:</strong> It is important to understand the precedence order of the HttpTrait APIs. In
     * particular, if a {@link HttpPipeline} is specified, this takes precedence over all other APIs in the trait, and
     * they will be ignored. If no {@link HttpPipeline} is specified, a HTTP pipeline will be constructed internally
     * based on the settings provided to this trait. Additionally, there may be other APIs in types that implement this
     * trait that are also ignored if an {@link HttpPipeline} is specified, so please be sure to refer to the
     * documentation of types that implement this trait to understand the full set of implications.</p>
     *
     * @param clientOptions A configured instance of {@link HttpClientOptions}.
     * @return KuFlowRestClientBuilder
     * @see HttpClientOptions
     */
    @Override
    public KuFlowRestClientBuilder clientOptions(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        return this;
    }

    /**
     * Sets the {@link HttpClient} to use for sending and receiving requests to and from the service.
     *
     * <p><strong>Note:</strong> It is important to understand the precedence order of the HttpTrait APIs. In
     * particular, if a {@link HttpPipeline} is specified, this takes precedence over all other APIs in the trait, and
     * they will be ignored. If no {@link HttpPipeline} is specified, a HTTP pipeline will be constructed internally
     * based on the settings provided to this trait. Additionally, there may be other APIs in types that implement this
     * trait that are also ignored if an {@link HttpPipeline} is specified, so please be sure to refer to the
     * documentation of types that implement this trait to understand the full set of implications.</p>
     *
     * @param httpClient The {@link HttpClient} to use for requests.
     * @return KuFlowRestClientBuilder
     */
    @Override
    public KuFlowRestClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient, "'httpClient' cannot be null.");
        return this;
    }

    /**
     * Adds a {@link HttpPipelinePolicy pipeline policy} to apply on each request sent.
     *
     * <p><strong>Note:</strong> It is important to understand the precedence order of the HttpTrait APIs. In
     * particular, if a {@link HttpPipeline} is specified, this takes precedence over all other APIs in the trait, and
     * they will be ignored. If no {@link HttpPipeline} is specified, a HTTP pipeline will be constructed internally
     * based on the settings provided to this trait. Additionally, there may be other APIs in types that implement this
     * trait that are also ignored if an {@link HttpPipeline} is specified, so please be sure to refer to the
     * documentation of types that implement this trait to understand the full set of implications.</p>
     *
     * @param policy A {@link HttpPipelinePolicy pipeline policy}.
     * @return KuFlowRestClientBuilder
     * @throws NullPointerException If {@code policy} is {@code null}.
     */
    @Override
    public KuFlowRestClientBuilder addPolicy(HttpPipelinePolicy policy) {
        this.pipelinePolicies.add(Objects.requireNonNull(policy, "'policy' cannot be null."));
        return this;
    }

    /**
     * Sets The serializer to serialize an object into a string.
     *
     * @param serializerAdapter the serializerAdapter value.
     * @return the KuFlowRestClientBuilder.
     */
    @Generated
    public KuFlowRestClientBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
        return this;
    }

    //    /**
    //     * Create asynchronous client applying default policies.
    //     * Additional HttpPolicies specified by pipelinePolicies will be applied after them
    //     *
    //     * @return KuFlowRestClientAsync instance
    //     * @throws IllegalStateException If both {@link #retryOptions(RetryOptions)}
    //     * and {@link #retryPolicy(RetryPolicy)} have been set.
    //     */
    //    public KuFlowRestClientAsync buildClientAsync() {
    //        return new KuFlowRestClientAsync(createServiceImpl());
    //    }

    /**
     * Create synchronous  client applying default policies.
     * Additional HttpPolicies specified by pipelinePolicies will be applied after them
     *
     * @return KuFlowRestClient instance
     * @throws IllegalStateException If both {@link #retryOptions(RetryOptions)}
     * and {@link #retryPolicy(RetryPolicy)} have been set.
     */
    public KuFlowRestClient buildClient() {
        return new KuFlowRestClient(this.createServiceImpl());
    }

    private KuFlowClientImpl createServiceImpl() {
        Objects.requireNonNull(this.endpoint, "'endpoint' is required");

        HttpPipeline builderPipeline = this.createHttpPipeline();

        KuFlowClientImplBuilder clientBuilder = new KuFlowClientImplBuilder();
        clientBuilder.host(this.endpoint).serializerAdapter(this.serializerAdapter).pipeline(builderPipeline);

        return clientBuilder.buildClient();
    }

    private HttpPipeline createHttpPipeline() {
        if (this.pipeline != null) {
            return this.pipeline;
        }

        Configuration buildConfiguration = (this.configuration == null) ? Configuration.getGlobalConfiguration() : this.configuration;
        ClientOptions buildClientOptions = (this.clientOptions == null) ? new ClientOptions() : this.clientOptions;
        HttpLogOptions buildLogOptions = (this.httpLogOptions == null) ? new HttpLogOptions() : this.httpLogOptions;

        List<HttpPipelinePolicy> policies = new ArrayList<>();

        // Add required policies
        String clientName = PROPERTIES.getOrDefault(SDK_NAME, "UnknownName");
        String clientVersion = PROPERTIES.getOrDefault(SDK_VERSION, "UnknownVersion");

        policies.add(new UserAgentPolicy("", clientName, clientVersion, buildConfiguration));
        policies.add(new RequestIdPolicy());
        policies.add(new AddHeadersFromContextPolicy());
        HttpHeaders headers = new HttpHeaders();
        buildClientOptions.getHeaders().forEach(header -> headers.set(header.getName(), header.getValue()));
        if (headers.getSize() > 0) {
            policies.add(new AddHeadersPolicy(headers));
        }
        // Add additional policies
        policies.addAll(
            this.pipelinePolicies.stream().filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL).collect(toList())
        );
        HttpPolicyProviders.addBeforeRetryPolicies(policies);

        policies.add(ClientBuilderUtil.validateAndGetRetryPolicy(this.retryPolicy, this.retryOptions));
        policies.add(new AddDatePolicy());
        // auth policy is per request, should be after retry
        policies.add(this.createHttpPipelineAuthPolicy());
        policies.add(new CookiePolicy());

        // Add additional policies
        policies.addAll(
            this.pipelinePolicies.stream()
                .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)
                .collect(Collectors.toList())
        );
        HttpPolicyProviders.addAfterRetryPolicies(policies);

        // Add logging policy
        policies.add(new HttpLoggingPolicy(buildLogOptions));

        return new HttpPipelineBuilder().policies(policies.toArray(new HttpPipelinePolicy[0])).httpClient(this.httpClient).build();
    }

    private HttpPipelinePolicy createHttpPipelineAuthPolicy() {
        if (this.username == null || this.password == null) {
            throw this.logger.logExceptionAsError(new IllegalArgumentException("Both 'username' and 'username' are required."));
        }

        BasicAuthenticationCredential tokenCredential = new BasicAuthenticationCredential(this.username, this.password);
        return new BearerTokenAuthenticationPolicy(tokenCredential, "https://api.kuflow.com//v2022-10-08/.default") {
            @Override
            public Mono<HttpResponse> process(HttpPipelineCallContext context, HttpPipelineNextPolicy next) {
                //                if ("http".equals(context.getHttpRequest().getUrl().getProtocol())) {
                //                    return Mono.error(new RuntimeException("token credentials require a URL using the HTTPS protocol scheme"));
                //                }
                HttpPipelineNextPolicy nextPolicy = next.clone();

                return authorizeRequest(context)
                    .then(Mono.defer(next::process))
                    .flatMap(httpResponse -> {
                        String authHeader = httpResponse.getHeaderValue(WWW_AUTHENTICATE);
                        if (httpResponse.getStatusCode() == 401 && authHeader != null) {
                            return authorizeRequestOnChallenge(context, httpResponse)
                                .flatMap(retry -> {
                                    if (retry) {
                                        // Both Netty and OkHttp expect the requestBody to be closed after the response has been read.
                                        // Failure to do so results in memory leak.
                                        // In case of StreamResponse (or other scenarios where we do not eagerly read the response)
                                        // the response body may not be consumed.
                                        // This can cause potential leaks in the scenarios like above, where the policy
                                        // may intercept the response and it may never be read.
                                        // Forcing the read here - so that the memory can be released.
                                        return httpResponse.getBody().ignoreElements().then(nextPolicy.process());
                                    } else {
                                        return Mono.just(httpResponse);
                                    }
                                });
                        }
                        return Mono.just(httpResponse);
                    });
            }
        };
    }
}
