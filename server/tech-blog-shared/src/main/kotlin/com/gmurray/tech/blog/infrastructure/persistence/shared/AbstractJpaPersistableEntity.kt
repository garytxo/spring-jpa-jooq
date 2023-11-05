package com.gmurray.tech.blog.infrastructure.persistence.shared

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant

/**
 * Put in place so we can use the [org.springframework.data.domain.AuditorAware]
 * which populates the @CreatedBy and @LastModifiedBy by using the name of the principal
 * which is pulled from the security context authentication instance.
 * Please check the campaign-impl for the spring AuditorAware implementation.
 *
 * @param <U>  audit user
 * @param <ID> entity unique identifier
</ID></U> */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractJpaPersistableEntity<U : Serializable> {

    @Version
    @Column(name = "row_version")
    open var version: Long? = 0

    @CreatedBy
    @Column(name = "row_created_by", updatable = false, nullable = false)
    open lateinit var createdBy: U

    @CreatedDate
    @Column(name = "row_created_on", updatable = false, nullable = false)
    open var createdDate: Instant? = null


    @LastModifiedDate
    @Column(name = "row_updated_on", updatable = true, nullable = true)
    open lateinit var lastModifiedDate: Instant

    @LastModifiedBy
    @Column(name = "row_updated_by", updatable = true, nullable = true)
    open lateinit var lastModifiedBy: U
}