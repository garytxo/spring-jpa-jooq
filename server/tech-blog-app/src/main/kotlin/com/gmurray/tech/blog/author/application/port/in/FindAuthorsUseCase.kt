package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.domain.BlogAuthor

@Deprecated("")
interface FindAuthorsUseCase {

    fun findBy(searchAuthorQuery: FindAuthorsQuery): Set<BlogAuthor>

    @Deprecated("")
    data class SearchAuthorQuery(
        val firstName: String
    )
}