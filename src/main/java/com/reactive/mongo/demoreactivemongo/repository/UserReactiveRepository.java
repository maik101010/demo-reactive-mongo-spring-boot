package com.reactive.mongo.demoreactivemongo.repository;

import com.reactive.mongo.demoreactivemongo.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReactiveRepository extends ReactiveMongoRepository<User, String> {
}
