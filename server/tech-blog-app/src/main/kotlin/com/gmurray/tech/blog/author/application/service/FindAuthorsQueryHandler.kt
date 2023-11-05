package com.gmurray.tech.blog.author.application.service

import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.application.port.out.FindAuthorsPort
import com.gmurray.tech.blog.author.domain.BlogAuthor
import org.springframework.stereotype.Service

@Service
class FindAuthorsQueryHandler(private val findAuthorsPort: FindAuthorsPort) : FindAuthorsUseCase {


    override fun findBy(searchAuthorQuery: FindAuthorsUseCase.SearchAuthorQuery): Set<BlogAuthor> {
       return findAuthorsPort.findBy(searchAuthorQuery)
    }
}