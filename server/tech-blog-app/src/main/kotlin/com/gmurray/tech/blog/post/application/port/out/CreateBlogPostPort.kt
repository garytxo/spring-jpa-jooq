package com.gmurray.tech.blog.post.application.port.out

import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostUseCase

interface CreateBlogPostPort {

    fun create(newBlogPostCommand: CreateBlogPostUseCase.NewBlogPostCommand):Long
}