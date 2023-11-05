package com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class CreateAuthorResponse(

    @field:Schema(
        name = "id", required = true,
        title = "Author unique identifier",
        example = "1231"
    )
    val id: Long,
)