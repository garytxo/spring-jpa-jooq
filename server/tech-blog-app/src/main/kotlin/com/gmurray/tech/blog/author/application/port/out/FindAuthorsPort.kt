package com.gmurray.tech.blog.author.application.port.out

import com.gmurray.tech.blog.author.domain.BlogAuthor
import com.gmurray.tech.blog.author.domain.FindAuthorSearchParams

interface FindAuthorsPort {


    fun findBy(findAuthorSearchParams: FindAuthorSearchParams): Set<BlogAuthor>


}