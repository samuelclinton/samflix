package com.samflix.backend.core.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        var tags = new ArrayList<Tag>();
        tags.add(new Tag().name("Videos").description("Gerencia os vídeos"));
        tags.add(new Tag().name("Categories").description("Gerencia as categorias"));
        tags.add(new Tag().name("Users").description("Gerencia os usuários"));
        tags.add(new Tag().name("Status").description("Prove informação sobre o status do serviço"));
        return new OpenAPI()
                .info(new Info()
                        .title("Samflix")
                        .version("v1")
                        .description("API da aplicação de vídeos Samflix"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório")
                        .url("https://github.com/samuelclinton/samflix"))
                .tags(tags);
    }

}
