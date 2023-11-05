package com.gmurray.tech.blog.fixtures.slices

import com.gmurray.tech.blog.shared.infrastructure.config.persistence.jpa.JpaPersistenceConfig
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = [
        "com.gmurray.tech.blog.infrastructure.persistence.jooq",
    "com.gmurray.tech.blog.infrastructure.persistence.jpa",
    "com.gmurray.tech.blog.post.infrastructure.out.persistence",
    "com.gmurray.tech.blog.author.infrastructure.out.persistence",
        "com.gmurray.tech.blog.infrastructure.jooq.persistence",
    "com.gmurray.tech.blog.fixtures.persistence"
])
@ImportAutoConfiguration(classes = [ FlywayAutoConfiguration.class])
@Import([JpaPersistenceConfig.class])
@interface JpaDbTest{

}