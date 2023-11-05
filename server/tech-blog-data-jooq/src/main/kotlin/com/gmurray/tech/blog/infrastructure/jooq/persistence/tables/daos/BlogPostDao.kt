/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos


import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.BlogPost
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.records.BlogPostRecord
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus

import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Configuration
import org.jooq.impl.DAOImpl
import org.springframework.stereotype.Repository


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
@Repository
open class BlogPostDao(configuration: Configuration?) : DAOImpl<BlogPostRecord, com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost, Long>(BlogPost.BLOG_POST, com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost::class.java, configuration) {

    /**
     * Create a new BlogPostDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost): Long? = o.id

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfId(lowerInclusive: Long?, upperInclusive: Long?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    fun fetchById(vararg values: Long): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.ID, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    fun fetchOneById(value: Long): com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost? = fetchOne(BlogPost.BLOG_POST.ID, value)

    /**
     * Fetch records that have <code>author_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfAuthorId(lowerInclusive: Long?, upperInclusive: Long?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.AUTHOR_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>author_id IN (values)</code>
     */
    fun fetchByAuthorId(vararg values: Long): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.AUTHOR_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>title BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfTitle(lowerInclusive: String?, upperInclusive: String?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.TITLE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>title IN (values)</code>
     */
    fun fetchByTitle(vararg values: String): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.TITLE, *values)

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfDescription(lowerInclusive: String?, upperInclusive: String?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.DESCRIPTION, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    fun fetchByDescription(vararg values: String): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.DESCRIPTION, *values)

    /**
     * Fetch records that have <code>tags BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfTags(lowerInclusive: String?, upperInclusive: String?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.TAGS, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>tags IN (values)</code>
     */
    fun fetchByTags(vararg values: String): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.TAGS, *values)

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfStatus(lowerInclusive: BlogPostStatus?, upperInclusive: BlogPostStatus?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.STATUS, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    fun fetchByStatus(vararg values: BlogPostStatus): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.STATUS, *values)

    /**
     * Fetch records that have <code>row_version BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRowVersion(lowerInclusive: Int?, upperInclusive: Int?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.ROW_VERSION, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>row_version IN (values)</code>
     */
    fun fetchByRowVersion(vararg values: Int): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.ROW_VERSION, *values.toTypedArray())

    /**
     * Fetch records that have <code>row_created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRowCreatedBy(lowerInclusive: String?, upperInclusive: String?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.ROW_CREATED_BY, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>row_created_by IN (values)</code>
     */
    fun fetchByRowCreatedBy(vararg values: String): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.ROW_CREATED_BY, *values)

    /**
     * Fetch records that have <code>row_created_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRowCreatedOn(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.ROW_CREATED_ON, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>row_created_on IN (values)</code>
     */
    fun fetchByRowCreatedOn(vararg values: LocalDateTime): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.ROW_CREATED_ON, *values)

    /**
     * Fetch records that have <code>row_updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRowUpdatedBy(lowerInclusive: String?, upperInclusive: String?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.ROW_UPDATED_BY, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>row_updated_by IN (values)</code>
     */
    fun fetchByRowUpdatedBy(vararg values: String): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.ROW_UPDATED_BY, *values)

    /**
     * Fetch records that have <code>row_updated_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRowUpdatedOn(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetchRange(BlogPost.BLOG_POST.ROW_UPDATED_ON, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>row_updated_on IN (values)</code>
     */
    fun fetchByRowUpdatedOn(vararg values: LocalDateTime): List<com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost> = fetch(BlogPost.BLOG_POST.ROW_UPDATED_ON, *values)
}