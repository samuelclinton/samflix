package com.samflix.backend.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Document
public class User {

    @Id
    private String id;

    private String username;

    @DBRef
    private Set<Video> likes;

}
