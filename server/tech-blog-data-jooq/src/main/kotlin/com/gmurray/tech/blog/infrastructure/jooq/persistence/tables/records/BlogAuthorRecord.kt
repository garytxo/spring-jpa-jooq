/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.records


import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.BlogAuthor
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.interfaces.IBlogAuthor
import com.gmurray.tech.blog.infrastructure.persistence.jooq.codegen.TechBlogAuditableAwareRecord

import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record9
import org.jooq.Row9
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class BlogAuthorRecord() : UpdatableRecordImpl<BlogAuthorRecord>(BlogAuthor.BLOG_AUTHOR), TechBlogAuditableAwareRecord, Record9<Long?, String?, String?, Int?, String?, LocalDateTime?, String?, LocalDateTime?, String?>, IBlogAuthor {

    open override var id: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open override var firstName: String?
        set(value): Unit = set(1, value)
        get(): String? = get(1) as String?

    open override var lastName: String?
        set(value): Unit = set(2, value)
        get(): String? = get(2) as String?

    open override var rowVersion: Int?
        set(value): Unit = set(3, value)
        get(): Int? = get(3) as Int?

    open override var rowCreatedBy: String?
        set(value): Unit = set(4, value)
        get(): String? = get(4) as String?

    open override var rowCreatedOn: LocalDateTime?
        set(value): Unit = set(5, value)
        get(): LocalDateTime? = get(5) as LocalDateTime?

    open override var rowUpdatedBy: String?
        set(value): Unit = set(6, value)
        get(): String? = get(6) as String?

    open override var rowUpdatedOn: LocalDateTime?
        set(value): Unit = set(7, value)
        get(): LocalDateTime? = get(7) as LocalDateTime?

    open override var email: String?
        set(value): Unit = set(8, value)
        get(): String? = get(8) as String?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row9<Long?, String?, String?, Int?, String?, LocalDateTime?, String?, LocalDateTime?, String?> = super.fieldsRow() as Row9<Long?, String?, String?, Int?, String?, LocalDateTime?, String?, LocalDateTime?, String?>
    override fun valuesRow(): Row9<Long?, String?, String?, Int?, String?, LocalDateTime?, String?, LocalDateTime?, String?> = super.valuesRow() as Row9<Long?, String?, String?, Int?, String?, LocalDateTime?, String?, LocalDateTime?, String?>
    override fun field1(): Field<Long?> = BlogAuthor.BLOG_AUTHOR.ID
    override fun field2(): Field<String?> = BlogAuthor.BLOG_AUTHOR.FIRST_NAME
    override fun field3(): Field<String?> = BlogAuthor.BLOG_AUTHOR.LAST_NAME
    override fun field4(): Field<Int?> = BlogAuthor.BLOG_AUTHOR.ROW_VERSION
    override fun field5(): Field<String?> = BlogAuthor.BLOG_AUTHOR.ROW_CREATED_BY
    override fun field6(): Field<LocalDateTime?> = BlogAuthor.BLOG_AUTHOR.ROW_CREATED_ON
    override fun field7(): Field<String?> = BlogAuthor.BLOG_AUTHOR.ROW_UPDATED_BY
    override fun field8(): Field<LocalDateTime?> = BlogAuthor.BLOG_AUTHOR.ROW_UPDATED_ON
    override fun field9(): Field<String?> = BlogAuthor.BLOG_AUTHOR.EMAIL
    override fun component1(): Long? = id
    override fun component2(): String? = firstName
    override fun component3(): String? = lastName
    override fun component4(): Int? = rowVersion
    override fun component5(): String? = rowCreatedBy
    override fun component6(): LocalDateTime? = rowCreatedOn
    override fun component7(): String? = rowUpdatedBy
    override fun component8(): LocalDateTime? = rowUpdatedOn
    override fun component9(): String? = email
    override fun value1(): Long? = id
    override fun value2(): String? = firstName
    override fun value3(): String? = lastName
    override fun value4(): Int? = rowVersion
    override fun value5(): String? = rowCreatedBy
    override fun value6(): LocalDateTime? = rowCreatedOn
    override fun value7(): String? = rowUpdatedBy
    override fun value8(): LocalDateTime? = rowUpdatedOn
    override fun value9(): String? = email

    override fun value1(value: Long?): BlogAuthorRecord {
        set(0, value)
        return this
    }

    override fun value2(value: String?): BlogAuthorRecord {
        set(1, value)
        return this
    }

    override fun value3(value: String?): BlogAuthorRecord {
        set(2, value)
        return this
    }

    override fun value4(value: Int?): BlogAuthorRecord {
        set(3, value)
        return this
    }

    override fun value5(value: String?): BlogAuthorRecord {
        set(4, value)
        return this
    }

    override fun value6(value: LocalDateTime?): BlogAuthorRecord {
        set(5, value)
        return this
    }

    override fun value7(value: String?): BlogAuthorRecord {
        set(6, value)
        return this
    }

    override fun value8(value: LocalDateTime?): BlogAuthorRecord {
        set(7, value)
        return this
    }

    override fun value9(value: String?): BlogAuthorRecord {
        set(8, value)
        return this
    }

    override fun values(value1: Long?, value2: String?, value3: String?, value4: Int?, value5: String?, value6: LocalDateTime?, value7: String?, value8: LocalDateTime?, value9: String?): BlogAuthorRecord {
        this.value1(value1)
        this.value2(value2)
        this.value3(value3)
        this.value4(value4)
        this.value5(value5)
        this.value6(value6)
        this.value7(value7)
        this.value8(value8)
        this.value9(value9)
        return this
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    override fun from(from: IBlogAuthor) {
        id = from.id
        firstName = from.firstName
        lastName = from.lastName
        rowVersion = from.rowVersion
        rowCreatedBy = from.rowCreatedBy
        rowCreatedOn = from.rowCreatedOn
        rowUpdatedBy = from.rowUpdatedBy
        rowUpdatedOn = from.rowUpdatedOn
        email = from.email
        resetChangedOnNotNull()
    }

    override fun <E : IBlogAuthor> into(into: E): E {
        into.from(this)
        return into
    }

    /**
     * Create a detached, initialised BlogAuthorRecord
     */
    constructor(id: Long? = null, firstName: String? = null, lastName: String? = null, rowVersion: Int? = null, rowCreatedBy: String? = null, rowCreatedOn: LocalDateTime? = null, rowUpdatedBy: String? = null, rowUpdatedOn: LocalDateTime? = null, email: String? = null): this() {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.rowVersion = rowVersion
        this.rowCreatedBy = rowCreatedBy
        this.rowCreatedOn = rowCreatedOn
        this.rowUpdatedBy = rowUpdatedBy
        this.rowUpdatedOn = rowUpdatedOn
        this.email = email
        resetChangedOnNotNull()
    }

    /**
     * Create a detached, initialised BlogAuthorRecord
     */
    constructor(value: com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogAuthor?): this() {
        if (value != null) {
            this.id = value.id
            this.firstName = value.firstName
            this.lastName = value.lastName
            this.rowVersion = value.rowVersion
            this.rowCreatedBy = value.rowCreatedBy
            this.rowCreatedOn = value.rowCreatedOn
            this.rowUpdatedBy = value.rowUpdatedBy
            this.rowUpdatedOn = value.rowUpdatedOn
            this.email = value.email
            resetChangedOnNotNull()
        }
    }
}
