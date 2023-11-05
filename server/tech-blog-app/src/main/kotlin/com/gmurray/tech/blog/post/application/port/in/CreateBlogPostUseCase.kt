package com.gmurray.tech.blog.post.application.port.`in`

import com.gmurray.tech.blog.post.domain.Categories
import com.gmurray.tech.blog.post.domain.PostId

interface CreateBlogPostUseCase {


    fun execute(newBlogPostCommand: NewBlogPostCommand): PostId

    data class NewBlogPostCommand(
        val authorId:Long,
        val title:String,
        val description:String,
        val tags:Set<String>,
        val categories: Set<Categories>,
    )
}