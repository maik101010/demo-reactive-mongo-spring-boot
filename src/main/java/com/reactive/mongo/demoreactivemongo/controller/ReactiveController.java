package com.reactive.mongo.demoreactivemongo.controller;

import com.reactive.mongo.demoreactivemongo.document.User;
import com.reactive.mongo.demoreactivemongo.repository.UserReactiveRepository;
import com.reactive.mongo.demoreactivemongo.repository.UserRxJavaReactiveRepository;
import io.reactivex.Flowable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class ReactiveController {
    private final UserReactiveRepository userReactiveRepository;
    private final UserRxJavaReactiveRepository userRxJavaReactiveRepository;

    public ReactiveController(UserReactiveRepository userReactiveRepository, UserRxJavaReactiveRepository userRxJavaReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
        this.userRxJavaReactiveRepository = userRxJavaReactiveRepository;
    }

    @GetMapping("/")
    Flux<User> userFlux() {
        return userReactiveRepository.findAll();
    }
    @GetMapping("/rxjava")
    Flowable<User> userFluxRxJava() {
        return userRxJavaReactiveRepository.findAll();
    }


    @GetMapping("/{id}")
    Mono<User> userFlux(@PathVariable String id) {
        return userReactiveRepository.findById(id);
    }
}
