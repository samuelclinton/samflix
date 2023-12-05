package com.samflix.backend.utils;

import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.model.VideoStatus;

import java.time.Instant;
import java.util.UUID;

import static com.samflix.backend.utils.CategoryHelper.generateCategory;
import static com.samflix.backend.utils.UserHelper.generateUser;

public abstract class VideoHelper {

    public static final String VIDEO_ID = "656ad2bae58b4709fa419b46";
    public static final String VIDEO_TITLE = "Video title";
    public static final String VIDEO_DESCRIPTION = "This is a test video entity";
    public static final UUID VIDEO_FILE = UUID.fromString("ce8fa71f-d2e2-489a-9b4b-ca2172b3271d");
    public static final String UPDATED_VIDEO_TITLE = "New video title";

    public static Video generateVideo() {
        return Video.builder()
                .id(VIDEO_ID)
                .title(VIDEO_TITLE)
                .description(VIDEO_DESCRIPTION)
                .file(VIDEO_FILE)
                .status(VideoStatus.PUBLICADO)
                .creationDate(Instant.now())
                .views(10L)
                .likes(5L)
                .creatorUsername(generateUser().getUsername())
                .category(generateCategory().getName())
                .build();
    }

}
