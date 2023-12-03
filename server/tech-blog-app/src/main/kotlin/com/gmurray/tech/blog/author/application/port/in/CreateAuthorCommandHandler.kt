package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.application.port.out.CreateAuthorPort
import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.shared.application.annotation.SyncCommandHandler
import com.gmurray.tech.blog.shared.application.service.CommandHandler

@SyncCommandHandler
class CreateAuthorCommandHandler(private val createAuthorPort: CreateAuthorPort) :
    CommandHandler<AuthorId, CreateAuthorCommand> {

    override fun execute(command: CreateAuthorCommand): AuthorId {
        return AuthorId(createAuthorPort.create(command))
    }
}