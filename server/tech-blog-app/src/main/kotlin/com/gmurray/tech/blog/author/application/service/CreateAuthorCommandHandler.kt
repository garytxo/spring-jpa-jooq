package com.gmurray.tech.blog.author.application.service

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorUseCase
import com.gmurray.tech.blog.author.application.port.out.AuthorStoragePort
import com.gmurray.tech.blog.author.application.port.out.CreateAuthorPort
import com.gmurray.tech.blog.author.domain.AuthorId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateAuthorCommandHandler(private val createAuthorPort: CreateAuthorPort) : CreateAuthorUseCase {

    @Transactional
    override fun create(newAuthorCommand: CreateAuthorUseCase.NewAuthorCommand): AuthorId {
       return AuthorId(createAuthorPort.create(newAuthorCommand))
    }
}