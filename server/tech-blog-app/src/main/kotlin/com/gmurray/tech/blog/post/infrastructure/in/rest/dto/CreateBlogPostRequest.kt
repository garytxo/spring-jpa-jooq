package com.gmurray.tech.blog.post.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class CreateBlogPostRequest (

    @field:Schema(
        name = "authorId", required = true,
        title = "Authors' unique identifier",
        example = "1212"
    )
    val authorId: Long,

    @field:Schema(
        name = "title", required = true,
        title = "Blog post title",
        example = "A title"
    )
    val title: String,

    @field:Schema(
        name = "description", required = true,
        title = "Blog post description",
        example = "A post description"
    )
    val description: String,


    @field:Schema(
        name = "category", required = true,
        title = "Blog post category",
        example = "TECHNOLOGY",
        allowableValues = [  "TECHNOLOGY", "FINANCE", "ENTERTAINMENT", "SCIENCE", "MUSIC", "OTHER]"]
    )
    val categories: Set<String>,


)