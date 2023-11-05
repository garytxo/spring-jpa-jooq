package com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class FindAuthorsResponse(
    @field:Schema(
        name = "id", required = true,
        title = "Author id",
        description = "Author unique identifier",
        example = "1212"
    )
    val id: String,

    @field:Schema(
        name = "fullName", required = true,
        title = "Author name",
        description = "Author full name",
        example = "Joe Bloggs"
    )
    val fullName: String
)