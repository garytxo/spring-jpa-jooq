package com.gmurray.tech.blog.post.application.service

import com.gmurray.tech.blog.post.application.port.`in`.FindBlogPostByIdUseCase
import com.gmurray.tech.blog.post.application.port.out.FindBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId
import org.springframework.stereotype.Service

@Service
@Deprecated("")
class FindBlogPostByIdQueryHandler(private val findBlogPostByIdPort: FindBlogPostByIdPort) : FindBlogPostByIdUseCase {

    override fun getBlogPostById(postId: PostId): Post {
        return findBlogPostByIdPort.getBlogPostById(postId)
    }
}