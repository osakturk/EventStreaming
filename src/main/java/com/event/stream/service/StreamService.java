package com.event.stream.service;

import com.event.stream.dto.EventParser;
import com.event.stream.dto.StreamingResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class StreamService {

    private final WebClient webClient;
    private final EventParser eventParser;

    public StreamService(WebClient webClient, EventParser eventParser) {
        this.webClient = webClient;
        this.eventParser = eventParser;
    }


    public Flux<StreamingResponseDTO> netflixData() {
        return streamJsonData("/sytflix");
    }

    public Flux<StreamingResponseDTO> amazonData() {
        return streamJsonData("/sytazon");
    }

    public Flux<StreamingResponseDTO> disneyData() {
        return streamJsonData("/sysney");
    }

    public Flux<StreamingResponseDTO> streamJsonData(String uri) {
        return webClient.get()
                .uri(uri) // Replace with your actual stream endpoint
                .retrieve()
                .bodyToFlux(byte[].class) // Assuming the response is a Flux of strings
                .map(eventParser::parseJsonFromByteArray);
    }
}
