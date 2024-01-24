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

    private LocalDate creationDate;
    private String file;
    private String title;
    private String description;
    private VideoStatus status = VideoStatus.PUBLISHED;
    private Long views = 0L;
    private Long likes = 0L;
    private String creatorUsername;
    private String category;

    public void addLike() {
        this.likes++;
    }

    public void removeLike() {
        this.likes--;
    }

    public Video(NewVideoDto newVideoDto, String fileName) {
        this.creationDate = LocalDate.now();
        this.file = fileName;
        this.title = newVideoDto.getTitle();
        this.description = newVideoDto.getDescription();
        this.creatorUsername = newVideoDto.getCreatorUsername();
        this.category = newVideoDto.getCategory();
    }

}
