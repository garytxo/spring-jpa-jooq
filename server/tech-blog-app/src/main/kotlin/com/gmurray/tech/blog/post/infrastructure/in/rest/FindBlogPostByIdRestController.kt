package com.gmurray.tech.blog.post.infrastructure.`in`.rest

import com.gmurray.tech.blog.post.application.port.`in`.FindBlogPostByIdUseCase
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.post.infrastructure.`in`.rest.dto.FindBlogPostResponse
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
    description = "Find blog post REST endpoint",
    name = "FindBlogPostByIdRestController",
)
@RequestMapping(
    produces = [MimeTypeUtils.APPLICATION_JSON_VALUE],
    consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE]
)
@RestController
class FindBlogPostByIdRestController(
    private val findBlogPostByIdUseCase: FindBlogPostByIdUseCase
) {

    @GetMapping(path = ["v1/blog/posts/{postId}"])
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get blog pos by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Get unique identifier", content = [
                    (Content(
                        array = (ArraySchema(schema = Schema(implementation = FindBlogPostResponse::class)))
                    ))]
            )
        ]
    )
    fun findBy(
        @PathVariable postId: Long
    ) =
        findBlogPostByIdUseCase.getBlogPostById(PostId(postId))
            .toResponse()


    private fun Post.toResponse() =
        FindBlogPostResponse(
            id = this.id.value(),
            title = this.title.value,
            description = this.description.value,
            categories = this.categories.map { it.value }.toSet(),
        )

}