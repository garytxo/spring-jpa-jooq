package com.gmurray.tech.blog

import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@SpringBootTest(
        classes = [TechBlogSpringApplication.class,TestOverrideServicesConfig.class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(["test","jpa"])
abstract class RestIntegrationTest extends Specification{

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private WebApplicationContext applicationContext

    def setup() {
        RestAssuredMockMvc.webAppContextSetup(applicationContext)
    }


    static class TestOverrideServicesConfig{

    }
}
