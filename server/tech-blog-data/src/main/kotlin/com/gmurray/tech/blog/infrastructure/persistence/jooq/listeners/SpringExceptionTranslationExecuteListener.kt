package com.volkswagenag.recall2.datatransfer.shared.infrastructure.persistence.jooq.listener

import org.jooq.ExecuteContext
import org.jooq.ExecuteListener
import org.jooq.SQLDialect
import org.springframework.jdbc.UncategorizedSQLException
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
import org.springframework.jdbc.support.SQLExceptionTranslator
import java.util.Objects

class SpringExceptionTranslationExecuteListener : ExecuteListener {

    override fun exception(ctx: ExecuteContext) {
        val dialect = ctx.configuration().dialect()
        val translator: SQLExceptionTranslator = getSqlExceptionTranslator(dialect)
        val dataAccessException = translator.translate(
            "Data access using JOOQ", ctx.sql(),
            ctx.sqlException()!!
        )
        val translation = Objects.requireNonNullElseGet(dataAccessException) {
            UncategorizedSQLException(
                "translation of exception", ctx.sql(),
                ctx.sqlException()!!
            )
        }
        ctx.exception(translation)
    }

    private fun getSqlExceptionTranslator(dsl: SQLDialect): SQLExceptionTranslator {
        return SQLErrorCodeSQLExceptionTranslator(dsl.name)
    }
}