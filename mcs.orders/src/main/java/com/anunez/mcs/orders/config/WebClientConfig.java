package com.anunez.mcs.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // @Value("${product.client.connect.timeout:15000}")
    // private Integer productClientConnectTimeout;
    // @Value("${product.client.response.timeout:15000}")
    // private Integer productClientResponseTimeout;
    // @Value("${product.client.read.timeout:15000}")
    // private Integer productClientReadTimeout;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    // private HttpClient productHttpClient = HttpClient.create()
    //         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, productClientConnectTimeout)
    //         .responseTimeout(Duration.ofMillis(productClientResponseTimeout))
    //         .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(productClientReadTimeout, TimeUnit.MILLISECONDS)));

    // @Bean("productWebClient")
    // public WebClient productWebClient() {
    //     return webClientBuilder()
    //             .clientConnector(new ReactorClientHttpConnector(productHttpClient))
    //             .baseUrl(null)
    //             .build();
    // }

}
