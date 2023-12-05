package com.gmurray.tech.blog.author.infrastructure.`in`.rest

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorCommand
import com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto.CreateAuthorRequest
import com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto.CreateAuthorResponse
import com.gmurray.tech.blog.shared.application.command.CommandBus
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(
    description = "Create author REST endpoint",
    name = "CreateAuthorRestController",
)
@RequestMapping(
    produces = [MimeTypeUtils.APPLICATION_JSON_VALUE],
    consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE]
)
@RestController
class CreateAuthorRestController(
    private val commandBus: CommandBus
) {


    @PostMapping(path = ["v1/blog/authors"])
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create author ")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Author unique identifier", content = [
                    (Content(
                        array = (ArraySchema(schema = Schema(implementation = CreateAuthorResponse::class)))
                    ))]
            )
        ]
    )
    fun create(
        @RequestBody(
            description = "New author request",
            content = [Content(schema = Schema(implementation = CreateAuthorRequest::class))]
        )
        @org.springframework.web.bind.annotation.RequestBody createAuthorRequest: CreateAuthorRequest
    ): CreateAuthorResponse {

        val newAuthor = createAuthorRequest.toNewAuthor()
        val id = commandBus.dispatch(newAuthor)

        return CreateAuthorResponse(id.value())
    }


    private fun CreateAuthorRequest.toNewAuthor() =
        CreateAuthorCommand(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email
        )


}