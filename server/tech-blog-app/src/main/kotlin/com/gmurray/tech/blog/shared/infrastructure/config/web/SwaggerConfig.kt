package com.gmurray.tech.blog.shared.infrastructure.config.web

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info

import jakarta.servlet.ServletContext
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {


    @Bean
    fun migratorApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("default")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun metaData(servletContext: ServletContext): OpenAPI {
        val server = io.swagger.v3.oas.models.servers.Server().url(servletContext.contextPath)
        return OpenAPI()
            .servers(listOf(server))
            .info(
                Info().title("TECH BLOG REST API")
                    .description("Testing playground for spring boot application")
            )
    }

}
