package com.gmurray.tech.blog.infrastructure.persistence.jooq

data class AuthorJooqEntity(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String
)
