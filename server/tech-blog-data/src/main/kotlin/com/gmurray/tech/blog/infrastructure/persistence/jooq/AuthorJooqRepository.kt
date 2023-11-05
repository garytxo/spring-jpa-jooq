package com.gmurray.tech.blog.infrastructure.persistence.jooq

import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogAuthorDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogAuthor
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class AuthorJooqRepository(dslContext: DSLContext,authorDao: BlogAuthorDao) :
    AbstractBaseJooqRepository<BlogAuthorDao>(dslContext,authorDao) {


    /**
     * Save using  the JOOQ generate POJOs and DAOs
     */
    fun save(author: AuthorJooqEntity):Long{
        val blogAuthor = author.toJooqBlogAuthor()
        dao.insert(blogAuthor)
        return blogAuthor.id!!

    }

    private fun AuthorJooqEntity.toJooqBlogAuthor() =
        BlogAuthor(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email
        )


    fun findById(authorId: Long) =
        dao.fetchOneById(authorId)?.toJooqEntity()

    fun searchBy(fname: String) =
        dao.fetchByFirstName(fname)
            .map { it.toJooqEntity() }


    private fun BlogAuthor.toJooqEntity() =
        AuthorJooqEntity(
            id = this.id!!,
            firstName = this.firstName!!,
            lastName = this.lastName!!,
            email = this.email!!
        )

}