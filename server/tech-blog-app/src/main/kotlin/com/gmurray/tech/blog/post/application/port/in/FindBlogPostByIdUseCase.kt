package com.gmurray.tech.blog.post.application.port.`in`

import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId

interface FindBlogPostByIdUseCase {


    fun getBlogPostById(postId: PostId): Post
}