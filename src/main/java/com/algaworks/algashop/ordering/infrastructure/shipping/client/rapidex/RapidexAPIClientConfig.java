package com.algaworks.algashop.ordering.infrastructure.shipping.client.rapidex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RapidexAPIClientConfig {

    @Bean
    public RapidexAPIClient rapidexApiClient(
            RestClient.Builder builder,
            @Value("${algashop.integrations.rapidex.url}") String rapidexUrl) {
        RestClient restClient = builder.baseUrl(rapidexUrl).build();
        RestClientAdapter adapterr = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapterr).build();
        return proxyFactory.createClient(RapidexAPIClient.class);
    }


}
