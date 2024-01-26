package com.samflix.backend.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.samflix.backend.utils.OpenApiSchemaExamples.ID_EXAMPLE;

@Tag(name = "Likes")
public interface VideoLikeControllerV1 {

    @Operation(summary = "Curte um vídeo",
            description = "Adiciona um vídeo na lista de vídeos curtidos pelo usuário")
    void likeVideo(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId);

    @Operation(summary = "Remove a curtida de um vídeo",
            description = "Remove um vídeo da lista de vídeos curtidos pelo usuário")
    void dislikeVideo(@Parameter(description = "O ID do vídeo a ser removido", example = ID_EXAMPLE) String videoId);



}
