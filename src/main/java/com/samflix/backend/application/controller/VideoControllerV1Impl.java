package com.samflix.backend.application.controller;

import com.samflix.backend.application.usecases.GetVideoRecommendationsUseCase;
import com.samflix.backend.application.usecases.ListVideosUseCase;
import com.samflix.backend.application.usecases.PlayVideoUseCase;
import com.samflix.backend.application.usecases.VideoPersistenceUseCases;
import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.external.api.model.NewVideoDto;
import com.samflix.backend.external.api.model.UpdateVideoDto;
import com.samflix.backend.external.api.model.VideoFilter;
import com.samflix.backend.external.api.openapi.VideoControllerV1;
import com.samflix.backend.infrastructure.persistence.VideoStorage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/v1/videos")
public class VideoControllerV1Impl implements VideoControllerV1 {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    private final VideoStorage videoStorage;
    private final PlayVideoUseCase playVideoUseCase;
    private final ListVideosUseCase listVideosUseCase;
    private final VideoPersistenceUseCases videoPersistenceUseCases;
    private final GetVideoRecommendationsUseCase getVideoRecommendationsUseCase;

    public VideoControllerV1Impl(VideoStorage videoStorage,
                                 PlayVideoUseCase playVideoUseCase,
                                 ListVideosUseCase listVideosUseCase,
                                 VideoPersistenceUseCases videoPersistenceUseCases,
                                 GetVideoRecommendationsUseCase getVideoRecommendationsUseCase) {
        this.videoStorage = videoStorage;
        this.playVideoUseCase = playVideoUseCase;
        this.listVideosUseCase = listVideosUseCase;
        this.videoPersistenceUseCases = videoPersistenceUseCases;
        this.getVideoRecommendationsUseCase = getVideoRecommendationsUseCase;
    }

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Video> create(@Valid NewVideoDto newVideoDto) throws IOException {
        String fileName = videoStorage.storeVideo(newVideoDto.getVideoFile().getInputStream());
        Video video = new Video(
                newVideoDto.getTitle(),
                newVideoDto.getDescription(),
                newVideoDto.getCategory(),
                fileName
        );
        return videoPersistenceUseCases.create(video);
    }

    @Override
    @PutMapping(value = "/{videoId}", consumes = JSON, produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> update(@PathVariable String videoId, @RequestBody @Valid UpdateVideoDto updateVideoDto) {
        Video video = new Video(
                updateVideoDto.getTitle(),
                updateVideoDto.getDescription(),
                null,
                null
        );
        return videoPersistenceUseCases.update(videoId, video);
    }

    @Override
    @GetMapping(value = "/{videoFileName}/play", produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> play(@PathVariable String videoFileName) {
        return playVideoUseCase.play(videoFileName);
    }

    @Override
    @GetMapping(produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> list(VideoFilter filter,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "id") String property,
                            @RequestParam(defaultValue = "asc") String order) {
        return listVideosUseCase.list(size, page, order, property,
                filter.getTitle(), filter.getCreationDate());
    }

    @Override
    @GetMapping("/recommendations")
    public Flux<Video> getRecommendations(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "views") String property,
                                          @RequestParam(defaultValue = "desc") String order) {
        return getVideoRecommendationsUseCase.getRecommendations(size, page, order, property);
    }

    @Override
    @DeleteMapping("/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String videoId) {
        return videoPersistenceUseCases.delete(videoId);
    }

}
