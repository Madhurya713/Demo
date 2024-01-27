package com.example.demo.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class HttpClientConfig {

    @Value("${http.client.maxTotal}")
    private int maxTotal;

    @Value("${http.client.defaultMaxPerRoute}")
    private int defaultMaxPerRoute;

    @Value("${http.client.validateAfterInactivity}")
    private int validateAfterInactivity;

    @Bean
    public PoolingHttpClientConnectionManager poolingConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setValidateAfterInactivity(validateAfterInactivity);
        return connectionManager;
    }

    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager connectionManager) {
        return HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .build();
    }
}
