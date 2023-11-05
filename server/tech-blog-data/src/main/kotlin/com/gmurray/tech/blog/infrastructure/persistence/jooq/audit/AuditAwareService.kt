package com.gmurray.tech.blog.infrastructure.persistence.jooq.audit

interface AuditAwareService {


    fun getCurrentUserId():String{
        return "TESTUSER"
    }


}