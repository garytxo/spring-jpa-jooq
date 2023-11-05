package com.gmurray.tech.blog.infrastructure.persistence.jooq

import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.PostCategoryDao
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CategoryJooqRepository(dslContext: DSLContext,categoryDao: PostCategoryDao):
 AbstractBaseJooqRepository<PostCategoryDao>(dslContext,categoryDao){

}