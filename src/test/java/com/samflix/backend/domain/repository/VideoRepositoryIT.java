package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.model.VideoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.UUID;

import static com.samflix.backend.utils.UserHelper.generateUser;
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

        var creator = generateUser();
        var video = saveVideo(generateVideo());

        assertThat(video)
                .isNotNull()
                .isInstanceOf(Video.class);

        assertThat(video.getId())
                .isEqualTo(VIDEO_ID);

        assertThat(video.getTitle())
                .isEqualTo(VIDEO_TITLE);

        assertThat(video.getFile())
                .isEqualTo(VIDEO_FILE);

        assertThat(video.getCreatorUsername())
                .isEqualTo(creator.getUsername());

        assertThat(video.getCategory())
                .isEqualTo(VIDEO_CATEGORY_NAME);
    }

    @Test
    void shouldUpdateVideoTitle() {
        var video = saveVideo(generateVideo());

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
        var video = saveVideo(generateVideo());

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
        var videos = new ArrayList<Video>();

        videos.add(generateVideo());
        videos.add(Video
                .builder()
                .id("655e64277caa1eae06196427")
                .title("Second video")
                .description("Second video description")
                .file(UUID.fromString("88ec77f6-3ffc-4bfc-941a-fb0000d019b6") + ".mp4")
                .status(VideoStatus.PUBLISHED)
                .views(15L)
                .likes(10L)
                .creatorUsername(generateUser().getUsername())
                .category("Second video category")
                .build());

        videoRepository.saveAll(videos).blockLast();

        var savedVideosList = new ArrayList<Video>();

        videoRepository.findAll().toIterable().forEach(savedVideosList::add);

        assertThat(savedVideosList)
                .isNotNull()
                .hasSize(2)
                .containsAll(videos);
    }

    @Test
    void shouldDeleteVideo() {
        var video = saveVideo(generateVideo());

        assertThat(video.getId())
                .isNotNull()
                .isEqualTo(VIDEO_ID);

        videoRepository.deleteById(VIDEO_ID).block();

        var deletedVideo = videoRepository.findById(VIDEO_ID).block();

        assertThat(deletedVideo).isNull();
    }

    public Video saveVideo(Video video) {
        Mono<Video> save = videoRepository.save(video);
        Mono<Video> find = videoRepository.findById(video.getId());
        Mono<Video> mono = Mono.from(save).then(find);
        return mono.block();
    }

}
