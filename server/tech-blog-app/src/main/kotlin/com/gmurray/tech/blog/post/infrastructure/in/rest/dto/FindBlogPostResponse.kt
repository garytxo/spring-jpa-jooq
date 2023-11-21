package com.gmurray.tech.blog.post.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class FindBlogPostResponse(

    @field:Schema(
        name = "id", required = true,
        title = "Blog post unique identifier",
        example = "11"
    )
    val id: Long,

    @field:Schema(
        name = "title", required = true,
        title = "Blog post title",
        example = "Hello world"
    )
    val title: String,

    @field:Schema(
        name = "description", required = true,
        title = "Blog post description",
        example = "Hello world description"
    )
    val description: String,

    @field:Schema(
        name = "categories", required = true,
        title = "Blog post categories",
        example = "[SPORT]"
    )
    val categories: Set<String>

    )