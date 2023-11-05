package com.gmurray.tech.blog.infrastructure.persistence.jooq

import org.jooq.DSLContext
import org.jooq.impl.DAOImpl
import org.slf4j.LoggerFactory

abstract class AbstractBaseJooqRepository<DAO>(
    private val dslContext: DSLContext,
    protected val dao: DAO

)where DAO : DAOImpl<*, *, *> {

    private val logger = LoggerFactory.getLogger(AbstractBaseJooqRepository::class.java)


    init {
        logger.info(
            "DAO:${dao::class.java.simpleName} configured"
        )
    }

    fun dslContext() = dslContext.dsl()

}