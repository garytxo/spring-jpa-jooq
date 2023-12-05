package com.gmurray.tech.blog.author.application.port.out

import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsQuery
import com.gmurray.tech.blog.author.domain.BlogAuthor

interface FindAuthorsPort {


    fun findBy(findAuthorsQuery: FindAuthorsQuery): Set<BlogAuthor>


}