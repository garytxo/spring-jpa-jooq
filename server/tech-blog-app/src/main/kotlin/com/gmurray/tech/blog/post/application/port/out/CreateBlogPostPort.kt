package com.gmurray.tech.blog.post.application.port.out

import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostCommand

interface CreateBlogPostPort {

    fun create(command: CreateBlogPostCommand): Long
}