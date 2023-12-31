/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables


import com.gmurray.tech.blog.infrastructure.jooq.persistence.TechBlog
import com.gmurray.tech.blog.infrastructure.jooq.persistence.keys.BLOG_POST__FK_BLOG_AUTHOR
import com.gmurray.tech.blog.infrastructure.jooq.persistence.keys.PK_BLOG_POST
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.records.BlogPostRecord
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus

import java.time.LocalDateTime
import java.util.function.Function

import kotlin.collections.List

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Records
import org.jooq.Row10
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.EnumConverter
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class BlogPost(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, BlogPostRecord>?,
    aliased: Table<BlogPostRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<BlogPostRecord>(
    alias,
    TechBlog.TECH_BLOG,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>tech_blog.blog_post</code>
         */
        val BLOG_POST: BlogPost = BlogPost()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<BlogPostRecord> = BlogPostRecord::class.java

    /**
     * The column <code>tech_blog.blog_post.id</code>.
     */
    val ID: TableField<BlogPostRecord, Long?> = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>tech_blog.blog_post.author_id</code>.
     */
    val AUTHOR_ID: TableField<BlogPostRecord, Long?> = createField(DSL.name("author_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>tech_blog.blog_post.title</code>.
     */
    val TITLE: TableField<BlogPostRecord, String?> = createField(DSL.name("title"), SQLDataType.VARCHAR.nullable(false), this, "")

    /**
     * The column <code>tech_blog.blog_post.description</code>.
     */
    val DESCRIPTION: TableField<BlogPostRecord, String?> = createField(DSL.name("description"), SQLDataType.VARCHAR, this, "")

    /**
     * The column <code>tech_blog.blog_post.status</code>.
     */
    val STATUS: TableField<BlogPostRecord, BlogPostStatus?> = createField(DSL.name("status"), SQLDataType.VARCHAR(100).nullable(false), this, "", EnumConverter<String, BlogPostStatus>(String::class.java, BlogPostStatus::class.java))

    /**
     * The column <code>tech_blog.blog_post.row_version</code>.
     */
    val ROW_VERSION: TableField<BlogPostRecord, Int?> = createField(DSL.name("row_version"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>tech_blog.blog_post.row_created_by</code>.
     */
    val ROW_CREATED_BY: TableField<BlogPostRecord, String?> = createField(DSL.name("row_created_by"), SQLDataType.VARCHAR(255).nullable(false), this, "")

    /**
     * The column <code>tech_blog.blog_post.row_created_on</code>.
     */
    val ROW_CREATED_ON: TableField<BlogPostRecord, LocalDateTime?> = createField(DSL.name("row_created_on"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "")

    /**
     * The column <code>tech_blog.blog_post.row_updated_by</code>.
     */
    val ROW_UPDATED_BY: TableField<BlogPostRecord, String?> = createField(DSL.name("row_updated_by"), SQLDataType.VARCHAR(255).defaultValue(DSL.field(DSL.raw("NULL::character varying"), SQLDataType.VARCHAR)), this, "")

    /**
     * The column <code>tech_blog.blog_post.row_updated_on</code>.
     */
    val ROW_UPDATED_ON: TableField<BlogPostRecord, LocalDateTime?> = createField(DSL.name("row_updated_on"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "")

    private constructor(alias: Name, aliased: Table<BlogPostRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<BlogPostRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>tech_blog.blog_post</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>tech_blog.blog_post</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>tech_blog.blog_post</code> table reference
     */
    constructor(): this(DSL.name("blog_post"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, BlogPostRecord>): this(Internal.createPathAlias(child, key), child, key, BLOG_POST, null)
    override fun getSchema(): Schema? = if (aliased()) null else TechBlog.TECH_BLOG
    override fun getIdentity(): Identity<BlogPostRecord, Long?> = super.getIdentity() as Identity<BlogPostRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<BlogPostRecord> = PK_BLOG_POST
    override fun getReferences(): List<ForeignKey<BlogPostRecord, *>> = listOf(BLOG_POST__FK_BLOG_AUTHOR)

    private lateinit var _blogAuthor: BlogAuthor

    /**
     * Get the implicit join path to the <code>tech_blog.blog_author</code>
     * table.
     */
    fun blogAuthor(): BlogAuthor {
        if (!this::_blogAuthor.isInitialized)
            _blogAuthor = BlogAuthor(this, BLOG_POST__FK_BLOG_AUTHOR)

        return _blogAuthor;
    }

    val blogAuthor: BlogAuthor
        get(): BlogAuthor = blogAuthor()
    override fun getRecordVersion(): TableField<BlogPostRecord, Int?> = ROW_VERSION
    override fun getRecordTimestamp(): TableField<BlogPostRecord, LocalDateTime?> = ROW_CREATED_ON
    override fun `as`(alias: String): BlogPost = BlogPost(DSL.name(alias), this)
    override fun `as`(alias: Name): BlogPost = BlogPost(alias, this)
    override fun `as`(alias: Table<*>): BlogPost = BlogPost(alias.getQualifiedName(), this)

    /**
     * Rename this table
     */
    override fun rename(name: String): BlogPost = BlogPost(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): BlogPost = BlogPost(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): BlogPost = BlogPost(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row10<Long?, Long?, String?, String?, BlogPostStatus?, Int?, String?, LocalDateTime?, String?, LocalDateTime?> = super.fieldsRow() as Row10<Long?, Long?, String?, String?, BlogPostStatus?, Int?, String?, LocalDateTime?, String?, LocalDateTime?>

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, Long?, String?, String?, BlogPostStatus?, Int?, String?, LocalDateTime?, String?, LocalDateTime?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, Long?, String?, String?, BlogPostStatus?, Int?, String?, LocalDateTime?, String?, LocalDateTime?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}
