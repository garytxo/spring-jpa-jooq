package com.gmurray.tech.blog.author.application.service

import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsQuery
import com.gmurray.tech.blog.author.application.port.`in`.FindAuthorsUseCase
import com.gmurray.tech.blog.author.application.port.out.FindAuthorsPort
import com.gmurray.tech.blog.author.domain.BlogAuthor
import org.springframework.stereotype.Service

@Service
@Deprecated("")
class FindAuthorsQueryHandler(private val findAuthorsPort: FindAuthorsPort) : FindAuthorsUseCase {


    override fun findBy(findAuthorsQuery: FindAuthorsQuery): Set<BlogAuthor> {
        return findAuthorsPort.findBy(findAuthorsQuery)
    }
}