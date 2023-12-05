package com.gmurray.tech.blog.author.infrastructure.`in`.rest

import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsQuery
import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto.FindAuthorsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(
    description = "Get author REST endpoint",
    name = "FindAuthorsRestController",
)
@RequestMapping(
    produces = [MimeTypeUtils.APPLICATION_JSON_VALUE]
)
@RestController
class FindAuthorsRestController(
    private val findAuthorsUseCase: FindAuthorsUseCase
) {


    @GetMapping(path = ["v1/blog/authors/{name}"])
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Find author by first name ")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "GetAuthorResponse", content = [
                    (Content(
                        array = (ArraySchema(schema = Schema(implementation = FindAuthorsResponse::class)))
                    ))]
            )
        ]
    )
    fun findByName(
        @PathVariable name: String
    ): Set<FindAuthorsResponse> {
        return findAuthorsUseCase.findBy(FindAuthorsQuery(name))
            .map { it.toResponse() }
            .toSet()
    }

    private fun BlogAuthor.toResponse() =
        FindAuthorsResponse(
            id = this.id().toString(),
            fullName = this.fullName()
        )


}