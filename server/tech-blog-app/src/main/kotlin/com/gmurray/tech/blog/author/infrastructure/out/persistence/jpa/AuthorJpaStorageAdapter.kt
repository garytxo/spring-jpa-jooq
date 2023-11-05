package com.gmurray.tech.blog.author.infrastructure.out.persistence.jpa

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorUseCase
import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.application.port.out.CreateAuthorPort
import com.gmurray.tech.blog.author.application.port.out.FindAuthorsPort
import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaEntity
import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaRepository
import org.slf4j.LoggerFactory

open class AuthorJpaStorageAdapter(private val authorJpaRepository: AuthorJpaRepository): FindAuthorsPort,CreateAuthorPort {

    private val logger = LoggerFactory.getLogger(AuthorJpaStorageAdapter::class.java)

    override fun create(newAuthorCommand: CreateAuthorUseCase.NewAuthorCommand): Long {
        logger.info("Saving author $newAuthorCommand")
       val createdAuthor = authorJpaRepository.save(newAuthorCommand.toJpa())
       return createdAuthor.id!!
    }

    override fun findBy(searchAuthorQuery: FindAuthorsUseCase.SearchAuthorQuery)=
        authorJpaRepository.findAllByFirstNameContainingIgnoreCase(searchAuthorQuery.firstName)
            .map { it.toDomain() }
            .toSet()


    private fun AuthorJpaEntity.toDomain() =
        BlogAuthor.toAuthor(
            id = this.id!!,
            firstName = this.firstName,
            lastName = this.lastName
        )
    private fun CreateAuthorUseCase.NewAuthorCommand.toJpa() =
        AuthorJpaEntity(
            firstName = this.firstName,
            lastName = this.lastName
        )
}