package com.gmurray.tech.blog.post.infrastructure.`in`.rest

import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostCommand
import com.gmurray.tech.blog.post.domain.Categories
import com.gmurray.tech.blog.post.infrastructure.`in`.rest.dto.CreateBlogPostRequest
import com.gmurray.tech.blog.post.infrastructure.`in`.rest.dto.CreateBlogPostResponse
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
    description = "Create blog post REST endpoint",
    name = "CreateBlogPostRestController",
)
@RequestMapping(
    produces = [MimeTypeUtils.APPLICATION_JSON_VALUE],
    consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE]
)
@RestController
class CreateBlogPostRestController(
    private val commandBus: CommandBus
) {

    @PostMapping(path = ["v1/blog/posts"])
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create blog post")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Post unique identifier", content = [
                    (Content(
                        array = (ArraySchema(schema = Schema(implementation = CreateBlogPostResponse::class)))
                    ))]
            )
        ]
    )
    fun create(
        @RequestBody(
            description = "New author request",
            content = [Content(schema = Schema(implementation = CreateBlogPostRequest::class))]
        )
        @org.springframework.web.bind.annotation.RequestBody createBlogPostRequest: CreateBlogPostRequest
    ):CreateBlogPostResponse{
        val postId = commandBus.dispatch(createBlogPostRequest.toCommand())
       return CreateBlogPostResponse(postId.value())
    }

    fun CreateBlogPostRequest.toCommand() =
        CreateBlogPostCommand(
            authorId = this.authorId,
            title = this.title,
            description = this.description,
            categories = this.categories.map { Categories.valueOf(it) }.toSet()
        )
}