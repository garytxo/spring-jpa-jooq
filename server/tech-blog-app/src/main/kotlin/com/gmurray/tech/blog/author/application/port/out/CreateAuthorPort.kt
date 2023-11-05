package com.gmurray.tech.blog.author.application.port.out

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorUseCase

interface CreateAuthorPort {


    fun create(newAuthorCommand: CreateAuthorUseCase.NewAuthorCommand):Long

}