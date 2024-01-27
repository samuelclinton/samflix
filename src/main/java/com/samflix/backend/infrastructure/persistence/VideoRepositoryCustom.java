package com.samflix.backend.infrastructure.persistence;

import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface VideoRepositoryCustom {

    Flux<VideoDatabaseEntity> findAllByFilter(String title, LocalDate creationDate, Pageable pageable);

}
