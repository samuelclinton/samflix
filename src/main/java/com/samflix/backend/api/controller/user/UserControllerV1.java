package com.samflix.backend.api.controller.user;

import com.samflix.backend.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.samflix.backend.utils.OpenApiSchemaExamples.ID_EXAMPLE;

@Tag(name = "Users")
public interface UserControllerV1 {

    @Operation(
            summary = "Cria um usuário",
            description = "Cria um usuário"
    )
    User create();

    @Operation(
            summary = "Atualiza um usuário",
            description = "Atualiza um usuário"
    )
    User update(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId);

    @Operation(
            summary = "Busca um usuário",
            description = "Cria um usuário por ID"
    )
    User get(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId);

    @Operation(
            summary = "Exclui um usuário",
            description = "Exclui um usuário"
    )
    void delete(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId);

    @Operation(
            summary = "Curte um vídeo",
            description = "Adiciona um vídeo na lista de vídeos curtidos pelo usuário"
    )
    void likeVideo(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId);

    @Operation(
            summary = "Remove a curtida de um vídeo",
            description = "Remove um vídeo da lista de vídeos curtidos pelo usuário"
    )
    void dislikeVideo(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId,
                      @Parameter(description = "O ID de um vídeo", example = ID_EXAMPLE) String videoId);

}
