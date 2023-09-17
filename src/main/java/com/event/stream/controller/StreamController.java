package com.event.stream.controller;


import com.event.stream.service.StreamService;
import com.event.stream.dto.StreamingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/stream")
public class StreamController {

    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/sytflix")
    public ResponseEntity<Flux<StreamingResponseDTO>> showSytflixEvent(){
        return ResponseEntity.ok(streamService.sytflixData().take(Duration.ofSeconds(20)));
    }

    @GetMapping("/sytazon")
    public ResponseEntity<Flux<StreamingResponseDTO>> showSytazonEvent(){
        return ResponseEntity.ok(streamService.sytazonData().take(Duration.ofSeconds(20)));
    }

    @GetMapping("/sysney")
    public ResponseEntity<Flux<StreamingResponseDTO>> showSysneyEvent(){
        return ResponseEntity.ok(streamService.sysneyData().take(Duration.ofSeconds(20)));
    }
}
