package com.samflix.backend.api.controller.category;

import com.samflix.backend.domain.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.samflix.backend.utils.OpenApiSchemaExamples.UUID_EXAMPLE;

@Tag(name = "Categories")
public interface CategoryControllerV1 {

    @Operation(
            summary = "Cria uma categoria",
            description = "Cria uma categoria"
    )
    Category create();

    @Operation(
            summary = "Atualiza uma categoria",
            description = "Atualiza uma categoria"
    )
    Category update(@Parameter(description = "O ID de uma categoria", example = UUID_EXAMPLE) String categoryId);

    @Operation(
            summary = "Lista as categorias",
            description = "Lista as categorias"
    )
    Page<Category> list(Pageable pageable);

    @Operation(
            summary = "Exclui uma categoria",
            description = "Exclui uma categoria"
    )
    void delete(@Parameter(description = "O ID de uma categoria", example = UUID_EXAMPLE) String categoryId);

}
