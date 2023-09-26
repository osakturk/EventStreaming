package com.event.stream.controller;


import com.event.stream.service.StreamService;
import com.event.stream.dto.StreamingResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/stream")
public class StreamController {

    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(value = "/collect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<StreamingResponseDTO>> collectData() {
        Flux<StreamingResponseDTO> netflixStream = streamService.netflixData();
        Flux<StreamingResponseDTO> amazonStream = streamService.amazonData();
        Flux<StreamingResponseDTO> disneyStream = streamService.disneyData();
        Flux<StreamingResponseDTO> combinedStream = Flux.concat(netflixStream, amazonStream, disneyStream);

        Flux<StreamingResponseDTO> filterStream = combinedStream
                .filter(data -> data.getData() != null)
                .filter(data -> "NameFilter".equals(data.getData().getUser().getFirstName()))
                .take(1); // Take the first occurrence of "NameFilter" on any stream

        Flux<ServerSentEvent<StreamingResponseDTO>> eventStream = combinedStream
                .map(data -> ServerSentEvent.<StreamingResponseDTO>builder()
                        .data(data)
                        .build())
                .take(Duration.ofSeconds(20));

        Mono<Void> foundSignal = filterStream.then(); // Convert the foundSignal to a Mono<Void>

        return Flux.merge(
                eventStream,
                filterStream
                        .map(data -> ServerSentEvent.<StreamingResponseDTO>builder()
                                .data(data)
                                .build())
        ).takeUntilOther(foundSignal); // Stop when "NameFilter" is found on any stream
    }
}
