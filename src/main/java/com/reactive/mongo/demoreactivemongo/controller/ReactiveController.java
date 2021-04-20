package com.reactive.mongo.demoreactivemongo.controller;

import com.reactive.mongo.demoreactivemongo.document.User;
import com.reactive.mongo.demoreactivemongo.repository.UserReactiveRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ReactiveController {
    private final UserReactiveRepository userReactiveRepository;

    public ReactiveController(UserReactiveRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    @GetMapping("/users")
    Flux<User> userFlux() {
        return userReactiveRepository.findAll();
    }
}
