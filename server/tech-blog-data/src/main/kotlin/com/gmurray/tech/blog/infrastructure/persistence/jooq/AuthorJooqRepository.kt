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
    fun save(blogAuthor: BlogAuthor):Long{
        dao.insert(blogAuthor)
        return blogAuthor.id!!

    }

    fun findById(authorId:Long)=
        dao.fetchOneById(authorId)

    fun searchBy(fname:String) =
             dao.fetchByFirstName(fname)

}