package com.gmurray.tech.blog.post.application.service

import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostUseCase
import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.domain.PostId
import org.springframework.stereotype.Service

@Service
class CreateBlogPostCommandHandler(private val createBlogPostPort: CreateBlogPostPort) : CreateBlogPostUseCase {


    override fun execute(newBlogPostCommand: CreateBlogPostUseCase.NewBlogPostCommand): PostId {
        return PostId(createBlogPostPort.create(newBlogPostCommand))
    }
}