package com.samflix.backend.infrastructure.persistence;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MockVideoStorageTest {

    @Test
    void storeVideo_shouldCreateRandomUuid() {
        VideoStorage videoStorage = new MockVideoStorageImpl();

        String fileName = videoStorage.storeVideo(InputStream.nullInputStream());

        assertNotNull(fileName);
        assertEquals(36, fileName.length());
    }

}
