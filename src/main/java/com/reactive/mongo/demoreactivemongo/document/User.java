package com.reactive.mongo.demoreactivemongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "json-data")
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
}
