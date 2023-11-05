package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.domain.AuthorId

interface CreateAuthorUseCase {


    fun create(newAuthorCommand: NewAuthorCommand): AuthorId

    data class NewAuthorCommand(
        val firstName:String,
        val lastName:String
    )
}