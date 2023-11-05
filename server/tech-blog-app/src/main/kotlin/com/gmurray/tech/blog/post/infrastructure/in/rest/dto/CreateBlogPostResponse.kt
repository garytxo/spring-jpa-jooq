package com.gmurray.tech.blog.post.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class CreateBlogPostResponse (
    @field:Schema(
        name = "id", required = true,
        title = "Blog post unique identifier",
        example = "11"
    )
    val id: Long,
)