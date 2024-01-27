package com.samflix.backend.external.api.openapi;

import com.samflix.backend.domain.entities.Statistics;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "Estatísticas")
public interface VideoStatisticsControllerV1 {

    @Operation(summary = "Exibe um relatório", description = "Exibe um relatório de estatísticas sobre os vídeos")
    Mono<Statistics> statistics();



}
