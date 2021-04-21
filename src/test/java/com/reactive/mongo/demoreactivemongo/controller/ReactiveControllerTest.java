package com.reactive.mongo.demoreactivemongo.controller;

import com.reactive.mongo.demoreactivemongo.document.User;
import com.reactive.mongo.demoreactivemongo.repository.UserReactiveRepository;
import com.reactive.mongo.demoreactivemongo.repository.UserRxJavaReactiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.regex.Matcher;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    UserReactiveRepository userReactiveRepository;

    @MockBean
    UserRxJavaReactiveRepository userRxJavaReactiveRepository;

    @Test
    public void getAllUsers() {
        Flux<User> userFluxActual = webTestClient
                .get().uri("/api/users/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(User.class)
                .getResponseBody();
        Assertions.assertNotNull(userFluxActual);
    }

    @Test
    public void givenAId_whenGetUserById_thenResponseUser() {
        User user = new User();
        user.setId("123123asd");
        user.setCode(100l);
        user.setName("Test");
        user.setEmail("test@gmail.com");

        Mockito
                .when(userReactiveRepository.findById("123123asd"))
                .thenReturn(Mono.just(user));

        webTestClient.get().uri("/api/users/{id}", "123123asd")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.id").isEqualTo("123123asd")
                .jsonPath("$.name").isEqualTo("Test")
                .jsonPath("$.email").isEqualTo("test@gmail.com");

        Mockito.verify(userReactiveRepository, times(1)).findById("123123asd");
    }

    @Test
    void getIsDataReady() {
        User userOne = new User();
        userOne.setId("123123asd");
        userOne.setCode(100l);
        userOne.setName("Test");
        userOne.setEmail("test@gmail.com");
        User userTwo = new User();
        userTwo.setId("123124asd");
        userTwo.setCode(102l);
        userTwo.setName("Test2");
        userTwo.setEmail("test2@gmail.com");


        Mockito
                .when(userReactiveRepository.findAll())
                .thenReturn(Flux.just(userOne, userTwo));
        webTestClient.get().uri("/api/users/")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo("123123asd")
                .jsonPath("$.[0].name").isEqualTo("Test")
                .jsonPath("$.[0].email").isEqualTo("test@gmail.com")
                .jsonPath("$.[1].id").isEqualTo("123124asd")
                .jsonPath("$.[1].name").isEqualTo("Test2")
                .jsonPath("$.[1].email").isEqualTo("test2@gmail.com");

        Mockito.verify(userReactiveRepository, times(1)).findAll();

    }
}
