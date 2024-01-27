package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Statistics;
import reactor.core.publisher.Mono;

public interface GetVideoStatisticsUseCase {

    Mono<Statistics> getVideoStatistics();

}
