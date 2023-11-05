package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.domain.BlogAuthor

interface FindAuthorsUseCase {

    fun findBy(searchAuthorQuery: SearchAuthorQuery): Set<BlogAuthor>

    data class SearchAuthorQuery(
        val firstName: String
    )
}