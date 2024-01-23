package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.exception.VideoNotFoundException;
import com.samflix.backend.domain.model.User;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.VideoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    private final UserService userService;
    private final VideoRepository videoRepository;

    @Override
    public Video get(String videoId) {
        return videoRepository.findById(videoId)
                .switchIfEmpty(Mono.error(new VideoNotFoundException(videoId)))
                .block();
    }

    @Override
    public void addLike(Video video) {
        video.addLike();
        videoRepository.save(video).block();
    }

    @Override
    public void removeLike(Video video) {
        video.removeLike();
        videoRepository.save(video).block();
    }

    @Override
    public Mono<Video> create(NewVideoDto newVideoDto) {
        // TODO - Implementar envio de vídeos local para storage com retorno de um url/uuid
        String nomeArquivo = UUID.randomUUID() + ".mp4";
        Video video = new Video(newVideoDto, nomeArquivo);
        return videoRepository.save(video);
    }

    @Override
    public Video update(String videoId, UpdateVideoDto updateVideoDto) {
        Video video = get(videoId);
        video.setTitle(updateVideoDto.getTitle());
        video.setDescription(updateVideoDto.getDescription());
        video.setCategory(updateVideoDto.getCategory());
        return videoRepository.save(video).block();
    }

    @Override
    public Mono<Void> delete(String videoId) {
        return videoRepository.findById(videoId)
                .switchIfEmpty(Mono.error(new VideoNotFoundException(videoId)))
                .flatMap(videoRepository::delete);
    }

    @Override
    public void deleteAllVideosByUser(String userId) {
        User user = userService.get(userId);
        if (user != null) {
            videoRepository.findAllByCreatorUsername(user.getUsername())
                    .collectList()
                    .flatMapMany(videos -> {
                        Flux<Video> videoFlux = Flux.fromIterable(videos);
                        return videoRepository.deleteAll(videoFlux);
                    })
                    .subscribe();
        }
    }

    public VideoServiceImpl(UserService userService, VideoRepository videoRepository) {
        this.userService = userService;
        this.videoRepository = videoRepository;
    }

}
