package com.volkswagenag.recall2.datatransfer.shared.infrastructure.persistence.jooq.listener

import com.gmurray.tech.blog.infrastructure.jooq.codegen.TechBlogAuditableAwareRecord
import com.gmurray.tech.blog.infrastructure.persistence.jooq.audit.AuditAwareService
import org.jooq.RecordContext
import org.jooq.RecordListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime


/**
 * The customized record listener kicks in only auditable record is insert or updated
 * it ensures that the audit field values are set before inserts, updates and deletes
 *
 * IF we had a paid version we could look into this
 * https://www.jooq.org/doc/latest/manual/code-generation/codegen-advanced/codegen-config-database/codegen-database-forced-types/codegen-database-forced-types-audit/
 */
//@Component
class JooqAuditRecordListener(val auditAwareService: AuditAwareService) : RecordListener {

    private val logger = LoggerFactory.getLogger(JooqAuditRecordListener::class.java)
    
    override fun insertStart(ctx: RecordContext) {
        ctx.getAuditableAwareRecord()?.let {
            val userId = auditAwareService.getCurrentUserId()

            it.rowCreatedBy = userId
            // Already set by default
            it.rowUpdatedBy = userId
            it.rowUpdatedOn = LocalDateTime.now()
            logger.debug("Insert ${ctx.getClassName()} audit fields for id:${it}")
        }


        super.insertStart(ctx)
    }

    override fun updateStart(ctx: RecordContext) {
        ctx.getAuditableAwareRecord()?.let {
            val userId = auditAwareService.getCurrentUserId()
            it.rowUpdatedBy = userId
            it.rowUpdatedOn = LocalDateTime.now()
            logger.debug("Update ${ctx.getClassName()}  audit fields for id:${it}")
        }
        super.updateStart(ctx)
    }

    override fun deleteStart(ctx: RecordContext) {
        if (ctx.record() is TechBlogAuditableAwareRecord) {
            val updatableRecord = ctx.record() as TechBlogAuditableAwareRecord
            logger.info("Deleting ${ctx.getClassName()}  with id:${updatableRecord}")
        }
        super.deleteStart(ctx)
    }

    private fun RecordContext.getClassName() =
        this.record()::class.simpleName ?: "unknown"

    private fun RecordContext.getAuditableAwareRecord(): TechBlogAuditableAwareRecord? {
        return if (this.record() is TechBlogAuditableAwareRecord) {
            this.record() as TechBlogAuditableAwareRecord
        } else {
            null
        }

    }

}