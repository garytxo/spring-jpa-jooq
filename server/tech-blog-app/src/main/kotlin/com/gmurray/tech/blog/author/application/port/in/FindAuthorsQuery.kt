package com.gmurray.tech.blog.author.application.port.`in`

import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.shared.application.query.Query

class FindAuthorsQuery(
    val firstName: String
) : Query<Set<BlogAuthor>>