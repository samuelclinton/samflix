package com.samflix.backend.api.controller.video;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.OpenApiSchemaExamples.ID_EXAMPLE;

@Tag(name = "Videos")
public interface VideoControllerV1 {

    @Operation(summary = "Cria um vídeo", description = "Cria um vídeo")
    Mono<Video> create(NewVideoDto newVideoDto);

    @Operation(summary = "Atualiza um vídeo", description = "Atualiza um vídeo")
    Mono<Video> update(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId,
                 UpdateVideoDto updateVideoDto);

    @Operation(summary = "Busca um vídeo", description = "Busca um vídeo")
    Video play(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId);

    @Operation(summary = "Lista os vídeos", description = "Lista os vídeos")
    Page<Video> list(Pageable pageable);

    @Operation(summary = "Exclui um vídeo", description = "Exclui um vídeo")
    Mono<Void> delete(@Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId);

    @Operation(summary = "Exibe um relatório", description = "Exibe um relatório de estatísticas sobre os vídeos")
    Report statistics();

}
