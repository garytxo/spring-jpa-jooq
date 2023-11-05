package com.gmurray.tech.blog.post.application.service

import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.post.application.port.out.GetBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostCategory
import com.gmurray.tech.blog.post.domain.PostDescription
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.post.domain.PostTag
import com.gmurray.tech.blog.post.domain.PostTitle
import spock.lang.Specification

class GetBlogPostByIdQueryHandlerTest extends Specification {

    GetBlogPostByIdPort getBlogPostByIdPort = Mock()
    def getBlogPostByIdQueryHandler = new GetBlogPostByIdQueryHandler(getBlogPostByIdPort)

    def "getBlogPostById should should return Post when given a valid id "() {
        given:
        def postId = new PostId(11L)
        def expectedPost = new Post(
                postId,
                new PostTitle("Title"),
                new PostDescription("Descriptions "),
                [new PostTag("Tag1")].toSet(),
                [new PostCategory("TEST")].toSet(),
                new AuthorId(11L)
        )

        when:
        def result = getBlogPostByIdQueryHandler.getBlogPostById(postId)

        then:
        result != null
        result == expectedPost

        and:
        1 * getBlogPostByIdPort.getBlogPostById(postId) >> expectedPost
    }
}
