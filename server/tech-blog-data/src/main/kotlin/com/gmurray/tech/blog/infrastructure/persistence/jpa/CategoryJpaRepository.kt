package com.gmurray.tech.blog.infrastructure.persistence.jpa

import org.springframework.data.repository.CrudRepository

interface CategoryJpaRepository :CrudRepository<CategoryJpaEntity,Long> {

    fun findByName(name:String):CategoryJpaEntity
}