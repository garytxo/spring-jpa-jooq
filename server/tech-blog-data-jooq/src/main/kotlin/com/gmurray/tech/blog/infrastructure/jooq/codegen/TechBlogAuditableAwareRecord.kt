package com.gmurray.tech.blog.infrastructure.jooq.codegen

import java.time.LocalDateTime


interface TechBlogAuditableAwareRecord {
    var rowVersion: Int?
    var rowCreatedBy: String?
    var rowCreatedOn: LocalDateTime?
    var rowUpdatedBy: String?
    var rowUpdatedOn: LocalDateTime?
}