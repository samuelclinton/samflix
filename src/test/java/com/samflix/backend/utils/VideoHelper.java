package com.samflix.backend.utils;

import java.util.UUID;

public abstract class VideoHelper {

    public static final String VIDEO_ID = "656ad2bae58b4709fa419b46";
    public static final String VIDEO_TITLE = "Video title";
    public static final String VIDEO_DESCRIPTION = "This is a test video entity";
    public static final String VIDEO_FILE = UUID.fromString("ce8fa71f-d2e2-489a-9b4b-ca2172b3271d") + ".mp4";
    public static final String UPDATED_VIDEO_TITLE = "New video title";
    public static final String VIDEO_CATEGORY_NAME = "Category";
    public static final String VIDEO_CATEGORY_UPDATED_NAME = "New category name";

//    public static Video generateVideo() {
//        return Video.builder()
//                .id(VIDEO_ID)
//                .title(VIDEO_TITLE)
//                .description(VIDEO_DESCRIPTION)
//                .url(VIDEO_FILE)
//                .views(10L)
//                .liked(false)
//                .category(VIDEO_CATEGORY_NAME)
//                .build();
//    }

}
