package com.gmurray.tech.blog.post.infrastructure.in.rest

import com.gmurray.tech.blog.RestIntegrationTest
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestData
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestDataCreator
import com.gmurray.tech.blog.fixtures.persistence.TestPostCategory
import com.gmurray.tech.blog.post.infrastructure.in.rest.dto.CreateBlogPostRequest
import com.gmurray.tech.blog.post.infrastructure.in.rest.dto.CreateBlogPostResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE

class CreateBlogPostRestControllerIT extends RestIntegrationTest {


    @Autowired
    BlogPostTestDataCreator blogPostTestDataCreator

    def cleanup() {
        blogPostTestDataCreator.cleanUp()
    }

    def "POST v1/blog/posts should create a new post for exist author"(){

        given:
        def authorId = createAuthor("Joe","Test")
        def title = "post title"
        def description = "post description"
        def tags = ["Tag","Tag2"].toSet()
        def categories = [TestPostCategory.ENTERTAINMENT.name()].toSet()

        and:
        def request = new CreateBlogPostRequest(authorId,title,description,tags,categories)

        when:
        def response = given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(request))
                .when()
                .post("v1/blog/posts")
                .then()
                .status(HttpStatus.CREATED)
                .extract().asString()

        then:
        def result = objectMapper.readValue(response, CreateBlogPostResponse.class)

        result.id > 0L

    }



    def createAuthor(String fname = "fname", String lname = "lname") {
        def testData = new BlogPostTestData()
        testData.blogAuthor.firstName = fname
        testData.blogAuthor.lastName = lname

        blogPostTestDataCreator.createBlogAuthorWith(testData)

        return testData.blogAuthor.id
    }
}
