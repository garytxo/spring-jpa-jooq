package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.shared.application.command.Command

class CreateAuthorCommand(
    val firstName:String,
    val lastName:String,
    val email:String,
):Command<AuthorId>