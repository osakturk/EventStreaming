package com.event.stream.controller;


import com.event.stream.service.StreamService;
import com.event.stream.dto.StreamingResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(StreamController.class);
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(value = "/collect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<StreamingResponseDTO>> collectData() {
        Flux<StreamingResponseDTO> combinedStream = streamService.allPlatformStreams();

        Mono<Void> foundSignal = combinedStream
                .filter(data -> data.getData() != null)
                .filter(data -> "Sytac".equals(data.getData().getUser().getFirstName()))
                .elementAt(2) // Take the third occurrence of "Sytac" on any stream
                .then();

        return Flux.defer(() -> {
            long start = System.nanoTime();
            // Time starts only when the subscription to the Flux actually begins,
            // not when the controller method is called. This guarantees accurate measurement of
            // the stream processing duration, as the timer starts with the actual data flow and not before.
            // That's why we use Flux.defer to encapsulate the logic that starts the timer and processes the stream.
            return combinedStream
                    .take(Duration.ofSeconds(20))
                    .takeUntilOther(foundSignal)
                    .map(data -> ServerSentEvent.<StreamingResponseDTO>builder()
                            .data(data)
                            .build())
                    .doFinally(signalType -> {
                        long durationMs = (System.nanoTime() - start) / 1_000_000;
                        logger.info("Stream processing completed in {} ms", durationMs);
                    });
        });
    }
}
