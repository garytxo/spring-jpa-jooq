package com.gmurray.tech.blog.author.infrastructure.out.persistence.jooq

import com.gmurray.tech.blog.author.application.port.in.CreateAuthorCommand
import com.gmurray.tech.blog.author.application.port.in.FindAuthorsQuery
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestData
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestDataCreator
import com.gmurray.tech.blog.fixtures.slices.JooqDbTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@JooqDbTest
@ActiveProfiles(["test","jooq"])
class AuthorJooqStorageAdapterIT extends Specification {

    @Autowired
    AuthorJooqStorageAdapter authorJooqStorageAdapter

    @Autowired
    BlogPostTestDataCreator blogPostTestDataCreator

    def cleanup() {
        blogPostTestDataCreator.cleanUp()
    }

    def "create should create new author with command"() {
        given:
        def firstName = "Fname"
        def lastName = "Lname"
        def email = "email@email.com"
        def command = new CreateAuthorCommand(firstName, lastName, email)

        when:
        def result = authorJooqStorageAdapter.create(command)

        then:
        result > 0
    }

    def "findBy should return one author"() {
        given:
        def testData = new BlogPostTestData()
        blogPostTestDataCreator.createBlogPostWith(testData)

        and:
        def query = new FindAuthorsQuery(testData.blogAuthor.firstName)

        when:
        def result = authorJooqStorageAdapter.findBy(query)

        then:
        result != null
        result.size() > 0
    }
}
