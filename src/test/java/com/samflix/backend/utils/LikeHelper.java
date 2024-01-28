package com.samflix.backend.utils;

import com.samflix.backend.infrastructure.persistence.model.LikeDatabaseEntity;

import static com.samflix.backend.utils.VideoHelper.VIDEO_CATEGORY_NAME;
import static com.samflix.backend.utils.VideoHelper.VIDEO_ID;

public abstract class LikeHelper {

    public static final String LIKE_ID = "656ad2bae58b4709fa419b47";

    public static LikeDatabaseEntity generateLikeDatabaseEntity() {
        return LikeDatabaseEntity.builder()
                .id(LIKE_ID)
                .videoId(VIDEO_ID)
                .videoCategory(VIDEO_CATEGORY_NAME)
                .build();
    }

}
