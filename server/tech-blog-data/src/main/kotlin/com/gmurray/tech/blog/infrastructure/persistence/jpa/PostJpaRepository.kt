package com.gmurray.tech.blog.infrastructure.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostJpaEntity,Long>