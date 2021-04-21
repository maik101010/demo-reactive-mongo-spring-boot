package com.reactive.mongo.demoreactivemongo.repository;

import com.reactive.mongo.demoreactivemongo.DemoReactiveMongoApplication;
import com.reactive.mongo.demoreactivemongo.document.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoReactiveMongoApplication.class)
public class UserReactiveRepositoryTest {

    @Autowired
    UserReactiveRepository userReactiveRepository;

    @Test
    public void givenUser_whenSave_thenSave() {
        Mono<User> accountMono = userReactiveRepository.save(new User(112l, "usertest", "usertest", null, null));
        StepVerifier
                .create(accountMono)
                .assertNext(account -> assertNotNull(account.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void givenId_whenFindById_thenFindUser() {
        User inserted = userReactiveRepository.save(new User(113L, "Omar", "omar@gmail.com", LocalDate.of(2020, 1, 2), null)).block();
        Mono<User> accountMono = userReactiveRepository.findById(inserted.getId());

        StepVerifier
                .create(accountMono)
                .assertNext(user -> {
                    assertEquals("Omar", user.getName());
                    assertEquals(113L, user.getCode());
                    assertEquals("omar@gmail.com", user.getEmail());
                    assertNotNull(user.getId());
                })
                .expectComplete()
                .verify();
    }


    @Test
    public void givenUsers_whenFindAll_thenFindAll() {
        Flux<User> userReactiveRepositoryAll = userReactiveRepository.findAll();

        StepVerifier.create(userReactiveRepositoryAll)
                .assertNext(user -> {
                    Assertions.assertEquals("Test1", user.getName());
                    Assertions.assertEquals("Test1@domain.com", user.getEmail());
                    Assertions.assertNotNull(user.getCode());
                })
                .assertNext(user -> {
                    Assertions.assertEquals("Test2", user.getName());
                    Assertions.assertEquals("Test2@domain.com", user.getEmail());
                    Assertions.assertNotNull(user.getCode());
                })
                .assertNext(user -> {
                    Assertions.assertEquals("Test3", user.getName());
                    Assertions.assertEquals("Test3@domain.com", user.getEmail());
                    Assertions.assertNotNull(user.getCode());
                })
                .thenCancel()
                .verify();
    }
}
