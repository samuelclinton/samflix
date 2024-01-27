package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface ListVideosUseCase {

    Flux<Video> list(int size, int page,
                           String sortDirection, String sortProperty,
                           String title, LocalDate creationDate);

}
