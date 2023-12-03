package com.gmurray.tech.blog.post.application.port.`in`

import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.shared.application.annotation.SyncCommandHandler
import com.gmurray.tech.blog.shared.application.service.CommandHandler

@SyncCommandHandler
class CreateBlogPostCommandHandler(private val createBlogPostPort: CreateBlogPostPort) :
    CommandHandler<PostId, CreateBlogPostCommand> {


    override fun execute(command: CreateBlogPostCommand): PostId {
        return PostId(createBlogPostPort.create(command))
    }

}