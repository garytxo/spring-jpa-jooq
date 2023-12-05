package com.gmurray.tech.blog.post.application.port.in

import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.post.application.port.out.FindBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostCategory
import com.gmurray.tech.blog.post.domain.PostDescription
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.post.domain.PostTitle
import spock.lang.Specification

class FindBlogPostByIdQueryHandlerTest extends Specification {

    FindBlogPostByIdPort getBlogPostByIdPort = Mock()
    def getBlogPostByIdQueryHandler = new FindBlogPostByIdQueryHandler(getBlogPostByIdPort)

    def "handle should should return Post when given a valid id "() {
        given:
        def id = 11L
        def postId = new PostId(id)
        def query = new FindBlogPostByIdQuery(id)
        def expectedPost = new Post(
                postId,
                new PostTitle("Title"),
                new PostDescription("Descriptions "),
                [new PostCategory("TEST")].toSet(),
                new AuthorId(11L)
        )

        when:
        def result = getBlogPostByIdQueryHandler.handle(query)

        then:
        result != null
        result == expectedPost

        and:
        1 * getBlogPostByIdPort.getBlogPostById(postId) >> expectedPost
    }
}
