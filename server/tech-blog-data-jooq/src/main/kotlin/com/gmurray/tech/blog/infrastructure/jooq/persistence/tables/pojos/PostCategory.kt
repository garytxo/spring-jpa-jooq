/*
 * This file is generated by jOOQ.
 */
package com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos


import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.interfaces.IPostCategory

import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class PostCategory(
    override var id: Long? = null,
    override var name: String? = null,
    override var rowVersion: Int? = null,
    override var rowCreatedBy: String? = null,
    override var rowCreatedOn: LocalDateTime? = null,
    override var rowUpdatedBy: String? = null,
    override var rowUpdatedOn: LocalDateTime? = null
): IPostCategory {


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (this::class != other::class)
            return false
        val o: PostCategory = other as PostCategory
        if (this.id == null) {
            if (o.id != null)
                return false
        }
        else if (this.id != o.id)
            return false
        if (this.name == null) {
            if (o.name != null)
                return false
        }
        else if (this.name != o.name)
            return false
        if (this.rowVersion == null) {
            if (o.rowVersion != null)
                return false
        }
        else if (this.rowVersion != o.rowVersion)
            return false
        if (this.rowCreatedBy == null) {
            if (o.rowCreatedBy != null)
                return false
        }
        else if (this.rowCreatedBy != o.rowCreatedBy)
            return false
        if (this.rowCreatedOn == null) {
            if (o.rowCreatedOn != null)
                return false
        }
        else if (this.rowCreatedOn != o.rowCreatedOn)
            return false
        if (this.rowUpdatedBy == null) {
            if (o.rowUpdatedBy != null)
                return false
        }
        else if (this.rowUpdatedBy != o.rowUpdatedBy)
            return false
        if (this.rowUpdatedOn == null) {
            if (o.rowUpdatedOn != null)
                return false
        }
        else if (this.rowUpdatedOn != o.rowUpdatedOn)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if (this.id == null) 0 else this.id.hashCode())
        result = prime * result + (if (this.name == null) 0 else this.name.hashCode())
        result = prime * result + (if (this.rowVersion == null) 0 else this.rowVersion.hashCode())
        result = prime * result + (if (this.rowCreatedBy == null) 0 else this.rowCreatedBy.hashCode())
        result = prime * result + (if (this.rowCreatedOn == null) 0 else this.rowCreatedOn.hashCode())
        result = prime * result + (if (this.rowUpdatedBy == null) 0 else this.rowUpdatedBy.hashCode())
        result = prime * result + (if (this.rowUpdatedOn == null) 0 else this.rowUpdatedOn.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("PostCategory (")

        sb.append(id)
        sb.append(", ").append(name)
        sb.append(", ").append(rowVersion)
        sb.append(", ").append(rowCreatedBy)
        sb.append(", ").append(rowCreatedOn)
        sb.append(", ").append(rowUpdatedBy)
        sb.append(", ").append(rowUpdatedOn)

        sb.append(")")
        return sb.toString()
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    override fun from(from: IPostCategory) {
        id = from.id
        name = from.name
        rowVersion = from.rowVersion
        rowCreatedBy = from.rowCreatedBy
        rowCreatedOn = from.rowCreatedOn
        rowUpdatedBy = from.rowUpdatedBy
        rowUpdatedOn = from.rowUpdatedOn
    }

    override fun <E : IPostCategory> into(into: E): E {
        into.from(this)
        return into
    }
}
