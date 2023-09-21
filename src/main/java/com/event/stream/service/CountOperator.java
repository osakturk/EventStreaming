package com.event.stream.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CountOperator {

    public static Mono<Void> countOccurrences(Flux<String> stream) {
        return stream
                .filter(name -> name.equals("Name"))
                .take(1) // Take the first three occurrences of "Name"
                .then();
    }
}
