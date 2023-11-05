package com.gmurray.tech.blog.shared.infrastructure.config.persistence.jooq

import com.gmurray.tech.blog.author.infrastructure.out.persistence.jooq.AuthorJooqStorageAdapter
import com.gmurray.tech.blog.infrastructure.persistence.jooq.AuthorJooqRepository
import com.gmurray.tech.blog.infrastructure.persistence.jooq.PostJooqRepository
import com.gmurray.tech.blog.post.infrastructure.out.persistence.jooq.PostJooqStorageAdapter
import com.gmurray.tech.blog.shared.infrastructure.config.persistence.TechBlogCommonPersistenceConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.transaction.annotation.EnableTransactionManagement


@Profile("jooq")
@Configuration
@EnableTransactionManagement
class JooqPersistenceConfig : TechBlogCommonPersistenceConfig() {


    @Bean
    fun authorJooqStorageAdapter(authorJooqRepository: AuthorJooqRepository)
            = AuthorJooqStorageAdapter(authorJooqRepository)

    @Bean
    fun postJooqStorageAdapter(authorJooqRepository: AuthorJooqRepository,
                               postJooqRepository: PostJooqRepository) =
        PostJooqStorageAdapter(authorJooqRepository,postJooqRepository)




}