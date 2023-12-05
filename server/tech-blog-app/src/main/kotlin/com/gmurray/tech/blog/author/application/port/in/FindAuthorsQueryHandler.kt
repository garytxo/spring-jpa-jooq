package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.application.port.out.FindAuthorsPort
import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.shared.application.annotation.TechBlogQueryHandler
import com.gmurray.tech.blog.shared.application.query.QueryHandler

@TechBlogQueryHandler
class FindAuthorsQueryHandler(private val findAuthorsPort: FindAuthorsPort) :
    QueryHandler<Set<BlogAuthor>, FindAuthorsQuery> {

    override fun handle(query: FindAuthorsQuery): Set<BlogAuthor> {
        return findAuthorsPort.findBy(query)
    }
}