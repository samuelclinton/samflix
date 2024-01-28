package com.samflix.backend.infrastructure.persistence;

import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static com.samflix.backend.utils.VideoHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureDataMongo
class VideoRepositoryIT {

    @Autowired
    private VideoRepository videoRepository;

    @BeforeEach
    public void setup() {
        videoRepository.deleteAll().block();
    }

    @Test
    void shouldSaveVideo() {

        var video = saveVideo(generateVideoDatabaseEntity());

        assertThat(video)
                .isNotNull()
                .isInstanceOf(VideoDatabaseEntity.class);

        assertThat(video.getId())
                .isEqualTo(VIDEO_ID);

        assertThat(video.getTitle())
                .isEqualTo(VIDEO_TITLE);

        assertThat(video.getFileName())
                .isEqualTo(VIDEO_FILE);

        assertThat(video.getCategory())
                .isEqualTo(VIDEO_CATEGORY_NAME);
    }

    @Test
    void shouldUpdateVideoTitle() {
        var video = saveVideo(generateVideoDatabaseEntity());

        assertThat(video.getTitle())
                .isEqualTo(VIDEO_TITLE);

        video.setTitle(UPDATED_VIDEO_TITLE);

        var updatedVideo = saveVideo(video);

        assertThat(updatedVideo.getTitle())
                .isNotNull()
                .isEqualTo(UPDATED_VIDEO_TITLE);
    }

    @Test
    void shouldUpdateVideoCategory() {
        var video = saveVideo(generateVideoDatabaseEntity());

        assertThat(video.getCategory())
                .isEqualTo(VIDEO_CATEGORY_NAME);

        video.setCategory(VIDEO_CATEGORY_UPDATED_NAME);

        var updatedVideo = saveVideo(video);

        assertThat(updatedVideo.getCategory())
                .isNotNull()
                .isEqualTo(VIDEO_CATEGORY_UPDATED_NAME);
    }

    @Test
    void shouldListVideos() {
        var videos = new ArrayList<VideoDatabaseEntity>();

        videos.add(generateVideoDatabaseEntity());
        videos.add(VideoDatabaseEntity
                .builder()
                .id("655e64277caa1eae06196427")
                .title("Second video")
                .description("Second video description")
                .fileName("88ec77f6-3ffc-4bfc-941a-fb0000d019b6")
                .views(15L)
                .liked(false)
                .category("Second video category")
                .build());

        videoRepository.saveAll(videos).blockLast();

        var savedVideosList = new ArrayList<VideoDatabaseEntity>();

        videoRepository.findAll().toIterable().forEach(savedVideosList::add);

        assertThat(savedVideosList)
                .isNotNull()
                .hasSize(2)
                .containsAll(videos);
    }

    @Test
    void shouldDeleteVideo() {
        var video = saveVideo(generateVideoDatabaseEntity());

        assertThat(video.getId())
                .isNotNull()
                .isEqualTo(VIDEO_ID);

        videoRepository.deleteById(VIDEO_ID).block();

        var deletedVideo = videoRepository.findById(VIDEO_ID).block();

        assertThat(deletedVideo).isNull();
    }

    public VideoDatabaseEntity saveVideo(VideoDatabaseEntity video) {
        Mono<VideoDatabaseEntity> save = videoRepository.save(video);
        Mono<VideoDatabaseEntity> find = videoRepository.findById(video.getId());
        Mono<VideoDatabaseEntity> mono = Mono.from(save).then(find);
        return mono.block();
    }

}
