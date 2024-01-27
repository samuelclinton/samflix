package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Statistics;
import com.samflix.backend.domain.entities.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface VideoGateway {

    Mono<Video> findByFileName(String videoFileName);
    Mono<Video> findById(String videoId);
    Mono<Video> saveVideo(Video video);
    Mono<Video> incrementView(String videoId);
    Mono<Video> updateVideo(String videoId, Video video);
    Mono<Void> deleteVideo(String videoId);
    Flux<Video> listVideos(int size, int page,
                           String sortDirection, String sortProperty,
                           String title, LocalDate creationDate);

    Flux<Video> findTopLikedByCategory(String category,
                                       int size, int page,
                                       String sortDirection, String sortProperty);
    Mono<Statistics> getVideoStatistics();

}
