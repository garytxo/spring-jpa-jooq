/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos


import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.interfaces.IPostPostCategory


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class PostPostCategory(
    override var postId: Long? = null,
    override var categoryId: Long? = null
): IPostPostCategory {


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (this::class != other::class)
            return false
        val o: PostPostCategory = other as PostPostCategory
        if (this.postId == null) {
            if (o.postId != null)
                return false
        }
        else if (this.postId != o.postId)
            return false
        if (this.categoryId == null) {
            if (o.categoryId != null)
                return false
        }
        else if (this.categoryId != o.categoryId)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if (this.postId == null) 0 else this.postId.hashCode())
        result = prime * result + (if (this.categoryId == null) 0 else this.categoryId.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("PostPostCategory (")

        sb.append(postId)
        sb.append(", ").append(categoryId)

        sb.append(")")
        return sb.toString()
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    override fun from(from: IPostPostCategory) {
        postId = from.postId
        categoryId = from.categoryId
    }

    override fun <E : IPostPostCategory> into(into: E): E {
        into.from(this)
        return into
    }
}
