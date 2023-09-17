package com.event.stream.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeaders(header -> {
                    header.setBasicAuth("sytac", "4p9g-Dv7T-u8fe-iz6y-SRW2");
                    header.setContentType(new MediaType("application", "json"));
                });
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.build();
    }
}
