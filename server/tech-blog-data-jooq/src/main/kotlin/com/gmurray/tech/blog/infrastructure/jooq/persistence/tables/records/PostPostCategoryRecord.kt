/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.records


import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostPostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.interfaces.IPostPostCategory

import org.jooq.Field
import org.jooq.Record2
import org.jooq.Row2
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class PostPostCategoryRecord() : UpdatableRecordImpl<PostPostCategoryRecord>(PostPostCategory.POST_POST_CATEGORY), Record2<Long?, Long?>, IPostPostCategory {

    open override var postId: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open override var categoryId: Long?
        set(value): Unit = set(1, value)
        get(): Long? = get(1) as Long?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record2<Long?, Long?> = super.key() as Record2<Long?, Long?>

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row2<Long?, Long?> = super.fieldsRow() as Row2<Long?, Long?>
    override fun valuesRow(): Row2<Long?, Long?> = super.valuesRow() as Row2<Long?, Long?>
    override fun field1(): Field<Long?> = PostPostCategory.POST_POST_CATEGORY.POST_ID
    override fun field2(): Field<Long?> = PostPostCategory.POST_POST_CATEGORY.CATEGORY_ID
    override fun component1(): Long? = postId
    override fun component2(): Long? = categoryId
    override fun value1(): Long? = postId
    override fun value2(): Long? = categoryId

    override fun value1(value: Long?): PostPostCategoryRecord {
        set(0, value)
        return this
    }

    override fun value2(value: Long?): PostPostCategoryRecord {
        set(1, value)
        return this
    }

    override fun values(value1: Long?, value2: Long?): PostPostCategoryRecord {
        this.value1(value1)
        this.value2(value2)
        return this
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    override fun from(from: IPostPostCategory) {
        postId = from.postId
        categoryId = from.categoryId
        resetChangedOnNotNull()
    }

    override fun <E : IPostPostCategory> into(into: E): E {
        into.from(this)
        return into
    }

    /**
     * Create a detached, initialised PostPostCategoryRecord
     */
    constructor(postId: Long? = null, categoryId: Long? = null): this() {
        this.postId = postId
        this.categoryId = categoryId
        resetChangedOnNotNull()
    }

    /**
     * Create a detached, initialised PostPostCategoryRecord
     */
    constructor(value: com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.PostPostCategory?): this() {
        if (value != null) {
            this.postId = value.postId
            this.categoryId = value.categoryId
            resetChangedOnNotNull()
        }
    }
}
