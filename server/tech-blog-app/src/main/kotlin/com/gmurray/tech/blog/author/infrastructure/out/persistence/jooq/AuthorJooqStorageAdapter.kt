package com.gmurray.tech.blog.author.infrastructure.out.persistence.jooq

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorCommand
import com.gmurray.tech.blog.author.application.port.out.CreateAuthorPort
import com.gmurray.tech.blog.author.application.port.out.FindAuthorsPort
import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.author.domain.FindAuthorSearchParams
import com.gmurray.tech.blog.infrastructure.persistence.jooq.AuthorJooqEntity
import com.gmurray.tech.blog.infrastructure.persistence.jooq.AuthorJooqRepository
import org.slf4j.LoggerFactory


open class AuthorJooqStorageAdapter(private val authorJooqRepository: AuthorJooqRepository):
    CreateAuthorPort , FindAuthorsPort {

    private val logger = LoggerFactory.getLogger(AuthorJooqStorageAdapter::class.java)

    override fun create(newAuthorCommand: CreateAuthorCommand): Long {
        logger.info("Saving author $newAuthorCommand")
        return authorJooqRepository.save(newAuthorCommand.toAuthorJooqEntity())
    }

    override fun findBy(findAuthorSearchParams: FindAuthorSearchParams): Set<BlogAuthor> {
        return authorJooqRepository.searchBy(findAuthorSearchParams.firstName)
            .map { it.toDomain() }
            .toSet()

    }

    private fun AuthorJooqEntity.toDomain() =
        BlogAuthor.toAuthor(
            id = this.id!!,
            firstName = this.firstName,
            lastName = this.lastName
        )

    private fun CreateAuthorCommand.toAuthorJooqEntity() =
        AuthorJooqEntity(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email
        )
}