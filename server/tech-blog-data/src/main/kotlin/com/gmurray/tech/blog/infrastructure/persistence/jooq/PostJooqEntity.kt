package com.gmurray.tech.blog.infrastructure.persistence.jooq

import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus

class PostJooqEntity(
    val id: Long?,
    val title: String,
    val description: String,
    val status: BlogPostStatus,
    val authorId: Long,
    val categories: List<PostCategoryEntity>
)