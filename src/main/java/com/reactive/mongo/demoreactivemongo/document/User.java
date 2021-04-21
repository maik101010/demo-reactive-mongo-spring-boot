package com.reactive.mongo.demoreactivemongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "users")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private Long code;
    private String name;
    private String email;
    private LocalDate birthDate;
    private List<Post> posts;

    public User(Long code, String name, String email, LocalDate birthDate, List<Post> posts) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.posts = posts;
    }
}
