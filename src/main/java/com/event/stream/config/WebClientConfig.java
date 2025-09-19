package com.event.stream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${stream.url}")
    private String streamUrl;

    @Value("${stream.auth.username}")
    private String username;

    @Value("${stream.auth.password}")
    private String password;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(streamUrl)
                .defaultHeaders(header -> {
                    header.setBasicAuth(username, password);
                    header.setContentType(MediaType.APPLICATION_JSON);
                });
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.build();
    }
}
