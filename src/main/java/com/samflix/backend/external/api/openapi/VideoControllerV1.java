package com.samflix.backend.external.api.openapi;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.external.api.model.NewVideoDto;
import com.samflix.backend.external.api.model.UpdateVideoDto;
import com.samflix.backend.external.api.model.VideoFilter;
import com.samflix.backend.external.springdoc.OpenApiSchemaExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Tag(name = "Videos")
public interface VideoControllerV1 {

    @Operation(summary = "Cria um vídeo", description = "Cria um vídeo")
    Mono<Video> create(NewVideoDto newVideoDto) throws IOException;

    @Operation(summary = "Atualiza um vídeo", description = "Atualiza um vídeo")
    Mono<Video> update(@Parameter(description = "O ID de um vídeo", example = OpenApiSchemaExamples.ID_EXAMPLE) String videoId,
                 UpdateVideoDto updateVideoDto);

    @Operation(summary = "Executa um vídeo", description = "Executa um vídeo")
    Mono<Video> play(@Parameter(description = "O nome do arquivo de um vídeo",
            example = OpenApiSchemaExamples.FILENAME_EXAMPLE) String videoId);

    @Operation(summary = "Lista os vídeos", description = "Lista os vídeos")
    Flux<Video> list(VideoFilter filter, int page, int size, String property, String order);

    @Operation(summary = "Retorna uma lista de vídeos recomendados", description = "Retorna uma lista de vídeos recomendados")
    Flux<Video> getRecommendations(int page, int size, String property, String order);

    @Operation(summary = "Exclui um vídeo", description = "Exclui um vídeo")
    Mono<Void> delete(@Parameter(description = "O ID de um vídeo", example = OpenApiSchemaExamples.ID_EXAMPLE) String videoId);

}
