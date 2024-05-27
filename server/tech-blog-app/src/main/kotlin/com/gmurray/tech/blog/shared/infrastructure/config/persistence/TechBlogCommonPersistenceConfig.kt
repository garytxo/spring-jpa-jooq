package com.gmurray.tech.blog.shared.infrastructure.config.persistence

import com.gmurray.tech.blog.infrastructure.persistence.jooq.listeners.JooqAuditRecordListener
import com.gmurray.tech.blog.infrastructure.persistence.jooq.listeners.SpringExceptionTranslationExecuteListener
import com.gmurray.tech.blog.shared.infrastructure.config.persistence.jooq.JooqUserAuditAwareService
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.ExecuteListenerProvider
import org.jooq.SQLDialect
import org.jooq.conf.Settings
import org.jooq.conf.StatementType
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.jooq.impl.DefaultExecuteListenerProvider
import org.jooq.impl.DefaultRecordListenerProvider
import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager

@Configuration
class TechBlogCommonPersistenceConfig {


    @Primary
    @Bean(name = ["techBlogDataSource"])
    @ConfigurationProperties("spring.datasource-tech-blog")
    fun techBlogDataSource() =
        HikariDataSource(techBlogDataSourceProperties())

    @Bean
    @ConfigurationProperties("spring.datasource-tech-blog.hikari")
    fun techBlogDataSourceProperties() =
        HikariConfig()


    @Bean(name = ["dslContext"])
    fun dslContext(): DefaultDSLContext {
        return DefaultDSLContext(jooqConfig())
    }

    @Bean(name = ["jooqConfig"])
    fun jooqConfig(): org.jooq.Configuration {
        val config = DefaultConfiguration()
        config.setSQLDialect(SQLDialect.POSTGRES)
        config.setDataSource(techBlogDataSource())
        config.setConnectionProvider(techBlogConnectionProvider())
        config.setTransactionProvider(techBlogTransactionProvider())
        config.setExecuteListenerProvider(techBlogExceptionTransformer())
        config.set(DefaultRecordListenerProvider(jooqAuditRecordListener()))

        val settings: Settings = Settings()
            .withRenderFormatted(true)
            .withBatchSize(1000)
            .withExecuteWithOptimisticLocking(true)
            //.withExecuteWithOptimisticLockingExcludeUnversioned(true)
            .withStatementType(StatementType.PREPARED_STATEMENT)
        //.withFetchSize(1000)
        config.setSettings(settings)
        return config
    }

    fun techBlogTransactionProvider(): SpringTransactionProvider {
        return SpringTransactionProvider(DataSourceTransactionManager(techBlogDataSource()))
    }
    fun techBlogConnectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(techBlogDataSource())
    }

    fun techBlogExceptionTransformer(): ExecuteListenerProvider {
        return DefaultExecuteListenerProvider(SpringExceptionTranslationExecuteListener())
    }


    @Bean
    fun jooqAuditRecordListener() =
        JooqAuditRecordListener(jooqUserAuditAwareService())

    @Bean
    fun jooqUserAuditAwareService() =
        JooqUserAuditAwareService()

}