package com.samflix.backend.utils;

import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;

public abstract class VideoHelper {

    public static final String VIDEO_ID = "656ad2bae58b4709fa419b46";
    public static final String VIDEO_TITLE = "Video title";
    public static final String VIDEO_DESCRIPTION = "This is a test video entity";
    public static final String VIDEO_UPDATED_DESCRIPTION = "This is a new description";
    public static final String VIDEO_FILE = "ce8fa71f-d2e2-489a-9b4b-ca2172b3271d";
    public static final String UPDATED_VIDEO_TITLE = "New video title";
    public static final String VIDEO_CATEGORY_NAME = "Category";
    public static final String VIDEO_CATEGORY_UPDATED_NAME = "New category name";

    public static VideoDatabaseEntity generateVideoDatabaseEntity() {
        return VideoDatabaseEntity.builder()
                .id(VIDEO_ID)
                .title(VIDEO_TITLE)
                .description(VIDEO_DESCRIPTION)
                .fileName(VIDEO_FILE)
                .views(10L)
                .liked(false)
                .category(VIDEO_CATEGORY_NAME)
                .build();
    }

}
