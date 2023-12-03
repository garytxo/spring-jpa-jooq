package com.gmurray.tech.blog.post.application.port.`in`

import com.gmurray.tech.blog.post.domain.Categories
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.shared.application.service.Command

class CreateBlogPostCommand(
    val authorId: Long,
    val title: String,
    val description: String,
    val categories: Set<Categories>,
) : Command<PostId>