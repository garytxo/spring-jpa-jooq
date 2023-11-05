package com.gmurray.tech.blog.post.application.port.out

import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId

interface FindBlogPostByIdPort {

    fun getBlogPostById(postId: PostId): Post
}