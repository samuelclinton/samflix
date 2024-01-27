package com.samflix.backend.external.api.openapi;

import com.samflix.backend.external.springdoc.OpenApiSchemaExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "Likes")
public interface VideoLikeControllerV1 {

    @Operation(summary = "Curte um vídeo",
            description = "Adiciona um vídeo na lista de vídeos curtidos pelo usuário")
    Mono<Void> likeVideo(@Parameter(description = "O ID de um vídeo", example = OpenApiSchemaExamples.ID_EXAMPLE) String videoId);

    @Operation(summary = "Remove a curtida de um vídeo",
            description = "Remove um vídeo da lista de vídeos curtidos pelo usuário")
    Mono<Void> dislikeVideo(@Parameter(description = "O ID do vídeo a ser removido", example = OpenApiSchemaExamples.ID_EXAMPLE) String videoId);



}
