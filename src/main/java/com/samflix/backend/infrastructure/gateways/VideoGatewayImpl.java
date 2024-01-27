package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Statistics;
import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.domain.exception.VideoNotFoundException;
import com.samflix.backend.infrastructure.persistence.VideoRepository;
import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.samflix.backend.infrastructure.gateways.PageableFactory.createPageable;

public class VideoGatewayImpl implements VideoGateway {

    private final VideoRepository videoRepository;

    public VideoGatewayImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    private Mono<VideoDatabaseEntity> findVideoByIdOrThrowException(String videoId) {
        return videoRepository.findById(videoId)
                .switchIfEmpty(Mono.error(new VideoNotFoundException("Nenhum vídeo encontrado com o ID " + videoId)));
    }

    @Override
    public Mono<Video> findByFileName(String fileName) {
        return videoRepository.findByFileName(fileName)
                .flatMap(videoDatabaseEntity -> Mono.just(new Video(videoDatabaseEntity)))
                .switchIfEmpty(Mono.error(
                        new VideoNotFoundException("Nenhum vídeo encontrado com o nome de arquivo " + fileName)));
    }

    @Override
    public Mono<Video> findById(String videoId) {
        return videoRepository.findById(videoId)
                .flatMap(videoDatabaseEntity -> Mono.just(new Video(videoDatabaseEntity)))
                .switchIfEmpty(Mono.error(
                        new VideoNotFoundException("Nenhum vídeo encontrado com o ID " + videoId)));
    }

    @Override
    public Mono<Video> saveVideo(Video video) {
        return videoRepository.save(new VideoDatabaseEntity(video))
                .flatMap(savedVideo -> Mono.just(new Video(savedVideo)));
    }

    @Override
    public Mono<Video> incrementView(String videoId) {
        return findVideoByIdOrThrowException(videoId)
                .flatMap(videoDatabaseEntity -> {
                    videoDatabaseEntity.incrementViews();
                    return videoRepository.save(videoDatabaseEntity)
                            .flatMap(savedVideo -> Mono.just(new Video(videoDatabaseEntity)));
                });
    }

    @Override
    public Mono<Video> updateVideo(String videoId, Video video) {
        return findVideoByIdOrThrowException(videoId)
                .flatMap(existingVideo -> {
                    String title = video.getTitle();
                    String description = video.getDescription();
                    if (title != null) {
                        existingVideo.setTitle(title);
                    }
                    if (description != null) {
                        existingVideo.setDescription(video.getDescription());
                    }
                    return videoRepository.save(existingVideo)
                            .flatMap(updatedVideo -> Mono.just(new Video(existingVideo)));
                });
    }

    @Override
    public Mono<Void> deleteVideo(String videoId) {
        return findVideoByIdOrThrowException(videoId)
                .flatMap(videoRepository::delete);
    }

    @Override
    public Flux<Video> listVideos(int size, int page,
                                  String sortDirection, String sortProperty,
                                  String title, LocalDate creationDate) {
        return videoRepository
                .findAllByFilter(title, creationDate,
                        createPageable(size, page, sortDirection, sortProperty))
                .flatMap(videoDatabaseEntity -> Mono.just(new Video(videoDatabaseEntity)));
    }

    @Override
    public Flux<Video> findTopLikedByCategory(String category,
                                              int size, int page,
                                              String sortDirection, String sortProperty) {
        return videoRepository.findTopLikedVideosByCategory(category,
                createPageable(size, page, sortDirection, sortProperty))
                .flatMap(videoDatabaseEntity -> Mono.just(new Video(videoDatabaseEntity)));
    }

    @Override
    public Mono<Statistics> getVideoStatistics() {
        Mono<Long> totalVideos = videoRepository.count();
        Mono<Long> totalLikedVideos = videoRepository.countByLikedTrue();
        Mono<ViewStatsAggregation> viewStats = videoRepository.viewStats();
        return Mono.zip(totalVideos, totalLikedVideos, viewStats)
                .map(tuple -> new Statistics(
                        tuple.getT1(),
                        tuple.getT2(),
                        tuple.getT3().totalViews(),
                        tuple.getT3().averageViews()));
    }

}
