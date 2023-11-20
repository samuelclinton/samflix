package com.samflix.backend.api.controller.video;

import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.samflix.backend.utils.OpenApiSchemaExamples.UUID_EXAMPLE;

@Tag(name = "Videos")
public interface VideoControllerV1 {

    @Operation(
            summary = "Cria um vídeo",
            description = "Cria um vídeo"
    )
    Video create();

    @Operation(
            summary = "Atualiza um vídeo",
            description = "Atualiza um vídeo"
    )
    Video update(@Parameter(description = "O ID de um vídeo", example = UUID_EXAMPLE) String videoId);

    @Operation(
            summary = "Busca um vídeo",
            description = "Busca um vídeo"
    )
    Video play(@Parameter(description = "O ID de um vídeo", example = UUID_EXAMPLE) String videoId);

    @Operation(
            summary = "Lista os vídeos",
            description = "Lista os vídeos"
    )
    Page<Video> list(Pageable pageable);

    @Operation(
            summary = "Exclui um vídeo",
            description = "Exclui um vídeo"
    )
    void delete(@Parameter(description = "O ID de um vídeo", example = UUID_EXAMPLE) String videoId);

    @Operation(
            summary = "Exibe um relatório",
            description = "Exibe um relatório de estatísticas sobre os vídeos"
    )
    Report statistics();

}
