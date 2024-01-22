package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.exception.VideoNotFoundException;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public Mono<Video> get(String videoId) {
        return videoRepository
                .findById(videoId)
                .switchIfEmpty(Mono.error(new VideoNotFoundException(videoId)));
    }

    @Override
    @Transactional
    public Mono<Video> create(NewVideoDto newVideoDto) {
        // TODO - Implementar envio de vídeos local para storage com retorno de um url/uuid
        String nomeArquivo = UUID.randomUUID() + ".mp4";
        Video video = new Video(newVideoDto, nomeArquivo);
        return videoRepository.save(video);
    }

    @Override
    @Transactional
    public Mono<Video> update(String videoId, UpdateVideoDto updateVideoDto) {
        return get(videoId)
                .flatMap(video -> {
                    video.setTitle(updateVideoDto.getTitle());
                    video.setDescription(updateVideoDto.getDescription());
                    video.setCategory(updateVideoDto.getCategory());
                    return videoRepository.save(video);
                });
    }

    @Override
    public Mono<Void> delete(String videoId) {
        return get(videoId).flatMap(videoRepository::delete);
    }

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

}
