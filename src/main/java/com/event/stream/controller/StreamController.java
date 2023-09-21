package com.event.stream.controller;


import com.event.stream.dto.StreamingResponseDTO;
import com.event.stream.service.StreamService;
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
        Flux<StreamingResponseDTO> sytflixStream = streamService.sytflixData();
        Flux<StreamingResponseDTO> sytazonStream = streamService.sytazonData();
        Flux<StreamingResponseDTO> sysneyStream = streamService.sysneyData();
        Flux<StreamingResponseDTO> combinedStream = Flux.concat(sytflixStream, sytazonStream, sysneyStream);

        Flux<StreamingResponseDTO> sytacStream = combinedStream
                .filter(data -> data.getData() != null)
                .filter(data -> "Sytac".equals(data.getData().getUser().getFirstName()))
                .take(1); // Take the first occurrence of "Sytac" on any stream

        Flux<ServerSentEvent<StreamingResponseDTO>> eventStream = combinedStream
                .map(data -> ServerSentEvent.<StreamingResponseDTO>builder()
                        .data(data)
                        .build())
                .take(Duration.ofSeconds(20));

        Mono<Void> sytacFoundSignal = sytacStream.then(); // Convert the sytacStream to a Mono<Void>

        return Flux.merge(
                eventStream,
                sytacStream
                        .map(data -> ServerSentEvent.<StreamingResponseDTO>builder()
                                .data(data)
                                .build())
        ).takeUntilOther(sytacFoundSignal); // Stop when "Sytac" is found on any stream
    }
}
