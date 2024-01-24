package com.samflix.backend.api.controller.user;

import com.samflix.backend.api.controller.model.LikeDto;
import com.samflix.backend.api.controller.model.UsernameDto;
import com.samflix.backend.domain.model.User;
import com.samflix.backend.domain.model.Video;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.OpenApiSchemaExamples.ID_EXAMPLE;

@Tag(name = "Users")
public interface UserControllerV1 {

    @Operation(summary = "Cria um usuário", description = "Cria um usuário")
    Mono<User> create(UsernameDto usernameDto);

    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário")
    Mono<User> update(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId,
                      UsernameDto usernameDto);

    @Operation(summary = "Busca um usuário", description = "Cria um usuário por ID")
    User get(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId);

    @Operation(summary = "Exclui um usuário", description = "Exclui um usuário")
    Mono<Void> delete(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId);

    @Operation(summary = "Curte um vídeo",
            description = "Adiciona um vídeo na lista de vídeos curtidos pelo usuário")
    void likeVideo(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId,
                         LikeDto likeDto);

    @Operation(summary = "Remove a curtida de um vídeo",
            description = "Remove um vídeo da lista de vídeos curtidos pelo usuário")
    void dislikeVideo(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId,
                            @Parameter(description = "O ID do vídeo a ser removido", example = ID_EXAMPLE) String videoId);

    @Operation(summary = "Retorna uma lista de vídeos recomendados", description = "Retorna uma lista de vídeos recomendados")
    Flux<Video> getRecommendations(@Parameter(description = "O ID de um usuário", example = ID_EXAMPLE) String userId,
                                   Pageable pageable);

}
