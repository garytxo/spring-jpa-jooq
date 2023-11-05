package com.gmurray.tech.blog.author.infrastructure.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

class CreateAuthorRequest(

    @field:Schema(
        name = "firstName", required = true,
        title = "First name",
        example = "Joe"
    )
    val firstName: String,


    @field:Schema(
        name = "lastName", required = true,
        title = "Last name",
        example = "Bloggs"
    )
    val lastName: String
)