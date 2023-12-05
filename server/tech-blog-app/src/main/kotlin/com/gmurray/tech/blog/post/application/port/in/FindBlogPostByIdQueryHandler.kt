package com.gmurray.tech.blog.post.application.port.`in`

import com.gmurray.tech.blog.post.application.port.out.FindBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.shared.application.annotation.TechBlogQueryHandler
import com.gmurray.tech.blog.shared.application.query.QueryHandler

@TechBlogQueryHandler
class FindBlogPostByIdQueryHandler(private val findBlogPostByIdPort: FindBlogPostByIdPort) :
    QueryHandler<Post, FindBlogPostByIdQuery> {

    override fun handle(query: FindBlogPostByIdQuery): Post {
        return findBlogPostByIdPort.getBlogPostById(PostId(query.value))
    }
}