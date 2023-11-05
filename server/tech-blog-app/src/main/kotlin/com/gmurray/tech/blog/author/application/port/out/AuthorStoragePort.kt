package com.gmurray.tech.blog.author.application.port.out

import com.gmurray.tech.blog.author.application.port.`in`.CreateAuthorUseCase
import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.domain.BlogAuthor

interface AuthorStoragePort {


    fun create(newAuthorCommand: CreateAuthorUseCase.NewAuthorCommand):Long


    fun findBy(searchAuthorQuery: FindAuthorsUseCase.SearchAuthorQuery):Set<BlogAuthor>


}