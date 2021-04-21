package com.reactive.mongo.demoreactivemongo.repository;

import com.reactive.mongo.demoreactivemongo.document.User;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRxJavaReactiveRepository extends RxJava2CrudRepository<User, String> {
}
