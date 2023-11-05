package com.gmurray.tech.blog.infrastructure.persistence.jpa

import com.gmurray.tech.blog.infrastructure.persistence.shared.AbstractJpaPersistableEntity
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "blog_post", schema = "tech_blog")
class PostJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name="post_gen", sequenceName="blog_post_seq", schema = "tech_blog", allocationSize = 1)
    @Column(name = "id")
    var id: Long? = 0,

    @ManyToOne(targetEntity = AuthorJpaEntity::class)
    @JoinColumn(name = "author_id")
    val author: AuthorJpaEntity,

    @Column(name = "title")
    val title:String,

    @Column(name = "description")
    val description:String,

    @Column(name = "tags")
    val tags:String,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: BlogPostStatus,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "post_post_category", schema="tech_blog",
        joinColumns = [ JoinColumn(name = "post_id", referencedColumnName = "id") ],
        inverseJoinColumns = [ JoinColumn(name = "category_id", referencedColumnName = "id") ])
    val categories:Set<CategoryJpaEntity>?= emptySet()

) : AbstractJpaPersistableEntity<String>()