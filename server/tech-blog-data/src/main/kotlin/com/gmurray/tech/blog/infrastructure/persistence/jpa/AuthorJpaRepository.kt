package com.gmurray.tech.blog.infrastructure.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorJpaRepository : JpaRepository<AuthorJpaEntity,Long> {

    fun findAllByFirstNameContainingIgnoreCase(firstName:String):Set<AuthorJpaEntity>
}