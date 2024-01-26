package com.samflix.backend.api.controller;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.filter.VideoFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.samflix.backend.config.springdoc.OpenApiSchemaExamples.ID_EXAMPLE;

@Tag(name = "Videos")
public interface VideoControllerV1 {

    @Operation(summary = "Cria um vídeo", description = "Cria um vídeo")
    Mono<Video> create(NewVideoDto newVideoDto);

    @Operation(summary = "Atualiza um vídeo", description = "Atualiza um vídeo")
    Video update(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId,
                 UpdateVideoDto updateVideoDto);

    @Operation(summary = "Busca um vídeo", description = "Busca um vídeo")
    Video play(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId);

    @Operation(summary = "Lista os vídeos", description = "Lista os vídeos")
    Flux<Video> list(VideoFilter filter, Pageable pageable);

    @Operation(summary = "Retorna uma lista de vídeos recomendados", description = "Retorna uma lista de vídeos recomendados")
    Flux<Video> getRecommendations(Pageable pageable);

    @Operation(summary = "Exclui um vídeo", description = "Exclui um vídeo")
    Mono<Void> delete(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId);

}