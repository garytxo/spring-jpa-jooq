package com.gmurray.tech.blog.shared.infrastructure.config.persistence.jpa

import com.gmurray.tech.blog.author.infrastructure.out.persistence.jpa.AuthorJpaStorageAdapter
import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.jpa.CategoryJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.jpa.PostJpaRepository
import com.gmurray.tech.blog.post.infrastructure.out.persistence.jpa.PostJpaStorageAdapter
import com.gmurray.tech.blog.shared.infrastructure.config.persistence.TechBlogCommonPersistenceConfig
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement


@Profile("jpa")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = ["com.gmurray.tech.blog.infrastructure.persistence.jpa"],
        entityManagerFactoryRef = "techBlogEntityManagerFactory",
        transactionManagerRef = "techBlogTransactionManager"
        )
@EnableJpaAuditing(auditorAwareRef = "jpaUserAuditAwareService")
class JpaPersistenceConfig : TechBlogCommonPersistenceConfig() {

    @Primary
    @Bean(name = ["techblogEntityManagerFactoryBuilder"])
    fun techBlogEntityManagerFactoryBuilder() =
        EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), HashMap<String, Any?>(), null)

    @Primary
    @Bean(name = ["techBlogEntityManagerFactory"])
    fun techBlogEntityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        return techBlogEntityManagerFactoryBuilder()
            .dataSource(techBlogDataSource())
            .packages("com.gmurray.tech.blog.infrastructure.persistence.jpa")
            .persistenceUnit("techblog-jpa")
            .build()
    }

    @Bean
    fun techBlogTransactionManager(
    ): PlatformTransactionManager {
        return JpaTransactionManager(techBlogEntityManagerFactory().`object`!!)
    }

    @Bean
    fun authorJpaStorageAdapter(authorJpaRepository: AuthorJpaRepository)
            = AuthorJpaStorageAdapter(authorJpaRepository)

    @Bean
    fun postJpaStorageAdapter(authorJpaRepository: AuthorJpaRepository,
                              postJpaRepository: PostJpaRepository,
                              categoryJpaRepository: CategoryJpaRepository
    )
     = PostJpaStorageAdapter(authorJpaRepository,postJpaRepository,categoryJpaRepository)

    @Bean
    fun jpaUserAuditAwareService() =
        JpaUserAuditAwareService()
}