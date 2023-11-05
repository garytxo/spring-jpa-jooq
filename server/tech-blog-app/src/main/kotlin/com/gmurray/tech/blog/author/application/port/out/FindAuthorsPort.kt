package com.gmurray.tech.blog.author.application.port.out

import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.domain.BlogAuthor

interface FindAuthorsPort {


    fun findBy(searchAuthorQuery: FindAuthorsUseCase.SearchAuthorQuery):Set<BlogAuthor>


}