package com.gmurray.tech.blog.post.application.port.in


import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.domain.Categories
import com.gmurray.tech.blog.post.domain.PostId
import spock.lang.Specification

class CreateBlogPostCommandHandlerTest extends Specification {


    CreateBlogPostPort createBlogPostPort = Mock()

    def commandHandler = new CreateBlogPostCommandHandler(createBlogPostPort)


    def "execute should create new post when author is valid"() {

        given:
        def expectedId = 1212L
        def expectedPostId = new PostId(expectedId)

        and:
        def authorId = 112L
        def title = "Title"
        def description = "Description"
        def category = Categories.ENTERTAINMENT
        def command = new CreateBlogPostCommand(
                authorId, title, description, [category].toSet()
        )

        when:
        def result = commandHandler.execute(command)

        then:
        result == expectedPostId
        and:
        1 * createBlogPostPort.create(command) >> expectedId
    }
}
