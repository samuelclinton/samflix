package com.samflix.backend.infrastructure.persistence;

import java.io.InputStream;

public interface VideoStorage {

    String storeVideo(InputStream videoFile);

}
