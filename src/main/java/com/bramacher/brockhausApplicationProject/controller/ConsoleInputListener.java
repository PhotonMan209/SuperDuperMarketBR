package com.bramacher.brockhausApplicationProject.controller;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ConsoleInputListener {

    public Flux<String> listenToConsoleInput() {
        return Flux.create(sink -> {
            new Thread(() -> {
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                while (true) {
                    String input = scanner.nextLine().trim();
                    if (!input.isEmpty()) {
                        sink.next(input);
                    }
                }
            }).start();
        });
    }
}
