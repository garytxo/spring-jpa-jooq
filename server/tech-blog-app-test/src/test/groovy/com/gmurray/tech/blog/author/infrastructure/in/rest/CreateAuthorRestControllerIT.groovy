package com.gmurray.tech.blog.author.infrastructure.in.rest

import com.gmurray.tech.blog.author.infrastructure.in.rest.dto.CreateAuthorRequest
import com.gmurray.tech.blog.author.infrastructure.in.rest.dto.CreateAuthorResponse
import com.gmurray.tech.blog.RestIntegrationTest
import org.springframework.http.HttpStatus

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE

class CreateAuthorRestControllerIT extends RestIntegrationTest {


    def "POST v1/blog/authors should create new author"(){

        given:
        def firstName = "Joe"
        def lastName = "Blogg"
        def request = new CreateAuthorRequest(firstName,lastName)

        when:
        def response = given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(request))
                .when()
                .post("v1/blog/authors")
                .then()
                .status(HttpStatus.CREATED)
                .extract().asString()

        then:
        def result = objectMapper.readValue(response, CreateAuthorResponse.class)

        result.id > 0L

    }
}
