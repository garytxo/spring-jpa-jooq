package com.gmurray.tech.blog.author.application.port.out

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorCommand

interface CreateAuthorPort {


    fun create(newAuthorCommand: CreateAuthorCommand): Long

}