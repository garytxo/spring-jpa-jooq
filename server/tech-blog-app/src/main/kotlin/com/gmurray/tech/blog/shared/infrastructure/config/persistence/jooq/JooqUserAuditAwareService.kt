package com.gmurray.tech.blog.shared.infrastructure.config.persistence.jooq

import com.gmurray.tech.blog.infrastructure.persistence.jooq.audit.AuditAwareService

class JooqUserAuditAwareService : AuditAwareService {

    override fun getCurrentUserId(): String {
        return "JOOQ_USER"
    }
}