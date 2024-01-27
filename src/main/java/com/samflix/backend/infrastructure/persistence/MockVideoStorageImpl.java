package com.samflix.backend.infrastructure.persistence;

import java.io.InputStream;
import java.util.UUID;

public class MockVideoStorageImpl implements VideoStorage {

    @Override
    public String storeVideo(InputStream videoFile) {
        return UUID.randomUUID().toString();
    }

}
