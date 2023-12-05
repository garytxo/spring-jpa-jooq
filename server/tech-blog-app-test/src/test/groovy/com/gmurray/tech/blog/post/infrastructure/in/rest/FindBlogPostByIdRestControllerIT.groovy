package com.gmurray.tech.blog.post.infrastructure.in.rest

import com.gmurray.tech.blog.RestIntegrationTest
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestData
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestDataCreator
import com.gmurray.tech.blog.post.infrastructure.in.rest.dto.FindBlogPostResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE

class FindBlogPostByIdRestControllerIT extends RestIntegrationTest {

    @Autowired
    BlogPostTestDataCreator blogPostTestDataCreator

    def cleanup() {
        blogPostTestDataCreator.cleanUp()
    }

    def "GET v1/blog/posts/id should return existing post"() {

        given:
        def blogPost = new BlogPostTestData()
        blogPostTestDataCreator.createBlogPostWith(blogPost)

        when:
        def response = given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("v1/blog/posts/${blogPost.blogPost.id}")
                .then()
                .status(HttpStatus.OK)
                .extract().asString()

        then:
        def result = objectMapper.readValue(response, FindBlogPostResponse.class)

        result.id == blogPost.blogPost.id
        result.title == blogPost.blogPost.title
        result.description == blogPost.blogPost.description
        blogPost.blogPost.categories.forEach {
            assert result.categories.contains(it.name())
        }
    }

}
