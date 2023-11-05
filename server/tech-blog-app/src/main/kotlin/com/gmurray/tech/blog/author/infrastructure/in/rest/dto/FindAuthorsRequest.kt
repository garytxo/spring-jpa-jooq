package com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class FindAuthorsRequest(

    @field:Schema(
        name = "name", required = true,
        title = "Author name",
        description = "Get author by name",
        example = "Joe"
    )
    val name: String
)