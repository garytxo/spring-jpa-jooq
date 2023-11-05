package com.gmurray.tech.blog.infrastructure.persistence.jpa

import com.gmurray.tech.blog.infrastructure.persistence.shared.AbstractJpaPersistableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "post_category", schema = "tech_blog")
class CategoryJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name="v", sequenceName="blog_category_seq", schema = "tech_blog", allocationSize = 1)
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "name")
    val name:String,

    @ManyToMany(mappedBy = "categories")
    val posts : Set<PostJpaEntity> ? = emptySet()


) : AbstractJpaPersistableEntity<String>()