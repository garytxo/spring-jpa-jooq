package com.gmurray.tech.blog.post.application.port.`in`

import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.shared.application.query.Query

data class FindBlogPostByIdQuery(val value: Long) : Query<Post>
