package com.samflix.backend.api.controller;

import com.samflix.backend.domain.model.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "Estatísticas")
public interface VideoStatisticsControllerV1 {

    @Operation(summary = "Exibe um relatório", description = "Exibe um relatório de estatísticas sobre os vídeos")
    Mono<Report> statistics();



}
