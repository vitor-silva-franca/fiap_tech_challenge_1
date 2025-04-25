package com.vitorsilvafranca.fiap_tech_challenge_1.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FIAP - Tech Challenge - Fase 1 de Arquitetura e Desenvolvimento Java")
                        .description("API desenvolvida para o Tech Challenge da Fase 1 de 5.\n\n" +
                                "Esta aplicação simula um sistema de pedidos com usuários e gerenciamento de status, " +
                                "seguindo princípios de Clean Architecture, DDD e boas práticas com Java, Spring Boot e PostgreSQL.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Vitor França")
                                .email("contato@vitorsilvafranca.com")
                                .url("https://vitorsilvafranca.com/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Técnica Completa")
                        .url("https://github.com/vitor-silva-franca/fiap_tech_challenge_1"))
                .addTagsItem(new Tag()
                        .name("Usuários")
                        .description("Operações relacionadas a usuários da aplicação"))
                .addTagsItem(new Tag()
                        .name("Pedidos")
                        .description("Operações relacionadas a pedidos feitos pelos usuários"));
    }
}
