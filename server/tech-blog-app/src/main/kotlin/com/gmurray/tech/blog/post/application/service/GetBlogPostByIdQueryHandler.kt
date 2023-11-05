package com.gmurray.tech.blog.post.application.service

import com.gmurray.tech.blog.post.application.port.`in`.GetBlogPostByIdUseCase
import com.gmurray.tech.blog.post.application.port.out.GetBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId
import org.springframework.stereotype.Service

@Service
class GetBlogPostByIdQueryHandler(private val getBlogPostByIdPort: GetBlogPostByIdPort) : GetBlogPostByIdUseCase {

    override fun getBlogPostById(postId: PostId): Post {
        return getBlogPostByIdPort.getBlogPostById(postId)
    }
}