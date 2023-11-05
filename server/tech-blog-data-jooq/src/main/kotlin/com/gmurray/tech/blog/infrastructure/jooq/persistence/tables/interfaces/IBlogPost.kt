/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.interfaces


import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus

import java.io.Serializable
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
interface IBlogPost : Serializable {
    var id: Long?
    var authorId: Long?
    var title: String?
    var description: String?
    var tags: String?
    var status: BlogPostStatus?
    var rowVersion: Int?
    var rowCreatedBy: String?
    var rowCreatedOn: LocalDateTime?
    var rowUpdatedBy: String?
    var rowUpdatedOn: LocalDateTime?

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IBlogPost
     */
    fun from(from: IBlogPost)

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IBlogPost
     */
    fun <E : IBlogPost> into(into: E): E
}