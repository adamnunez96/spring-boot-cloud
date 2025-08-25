package com.anunez.mcs.orders.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.anunez.mcs.orders.dto.BaseResponse;
import com.anunez.mcs.orders.service.HttpClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HttpClientServiceImpl implements HttpClientService {

    private final WebClient.Builder webClientBuilder;

    @Value("${inventory.service.url:http://localhost:8081/api/v1}")
    private String inventoryServiceUrl;
    private static final String STOCK_URI = "/inventory/in-stock";

    public HttpClientServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public BaseResponse sendRequest(Object request) {

        WebClient webClient = webClientBuilder.baseUrl(inventoryServiceUrl).build();

        return webClient.post()
                .uri(STOCK_URI)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }
}
