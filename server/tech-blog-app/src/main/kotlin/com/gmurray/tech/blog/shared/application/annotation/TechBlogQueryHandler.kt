package com.gmurray.tech.blog.shared.application.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Annotation for application query handler classes which alias for the @Service and it will add @Transactional
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Service
@Transactional
annotation class TechBlogQueryHandler(
    @get: AliasFor(annotation = Service::class)
    val value: String = ""
)