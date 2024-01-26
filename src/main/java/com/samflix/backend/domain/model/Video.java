package com.samflix.backend.domain.model;

import com.samflix.backend.api.controller.model.NewVideoDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "videos")
public class Video {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String title;
    private String description;
    private Long views = 0L;
    private Boolean liked;
    private String category;
    private String url;
    private LocalDate creationDate;

    public void like() {
        this.liked = true;
    }

    public void dislike() {
        this.liked = false;
    }

    public Video(NewVideoDto newVideoDto, String url) {
        this.title = newVideoDto.getTitle();
        this.description = newVideoDto.getDescription();
        this.category = newVideoDto.getCategory();
        this.liked = false;
        this.url = url;
        this.creationDate = LocalDate.now();
    }

}
