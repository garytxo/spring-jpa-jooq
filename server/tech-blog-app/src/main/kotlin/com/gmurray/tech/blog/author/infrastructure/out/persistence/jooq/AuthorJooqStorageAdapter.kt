package com.gmurray.tech.blog.author.infrastructure.out.persistence.jooq

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorUseCase
import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.application.port.out.CreateAuthorPort
import com.gmurray.tech.blog.author.application.port.out.FindAuthorsPort
import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.infrastructure.persistence.jooq.AuthorJooqRepository
import org.slf4j.LoggerFactory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogAuthor as BlogAuthorPojo


open class AuthorJooqStorageAdapter(private val authorJooqRepository: AuthorJooqRepository):
    CreateAuthorPort , FindAuthorsPort {

    private val logger = LoggerFactory.getLogger(AuthorJooqStorageAdapter::class.java)

    override fun create(newAuthorCommand: CreateAuthorUseCase.NewAuthorCommand): Long {
        logger.info("Saving author $newAuthorCommand")
        return authorJooqRepository.save(newAuthorCommand.toJooqPojo())
    }

    override fun findBy(searchAuthorQuery: FindAuthorsUseCase.SearchAuthorQuery): Set<BlogAuthor> {
        return authorJooqRepository.searchBy(searchAuthorQuery.firstName)
            .map { it.toDomain() }
            .toSet()

    }

    private fun BlogAuthorPojo.toDomain() =
        BlogAuthor.toAuthor(
            id = this.id!!,
            firstName = this.firstName!!,
            lastName = this.lastName!!
        )

    private fun CreateAuthorUseCase.NewAuthorCommand.toJooqPojo() =
        com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogAuthor(
            firstName = this.firstName,
            lastName = this.lastName
        )
}