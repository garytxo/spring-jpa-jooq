package com.gmurray.tech.blog.author.application.port.in
import com.gmurray.tech.blog.author.application.port.out.CreateAuthorPort
import com.gmurray.tech.blog.author.domain.AuthorId
import spock.lang.Specification

class CreateAuthorCommandHandlerTest extends Specification {

    CreateAuthorPort createAuthorPort = Mock()
    def createAuthorCommandHandler = new CreateAuthorCommandHandler(createAuthorPort)

    def "execute should create new author and return its unique identifier"() {

        given:
        def id = 11L
        def expectedAuthorId = new AuthorId(id)
        def firstName = "Gary"
        def lastName = "Murray"
        def email = "gary@mail.com"
        def command = new CreateAuthorCommand(firstName, lastName, email)

        when:
        def result = createAuthorCommandHandler.execute(command)


        then:
        result == expectedAuthorId

        and:
        1 * createAuthorPort.create(command) >> id
        0 * _
    }
}
