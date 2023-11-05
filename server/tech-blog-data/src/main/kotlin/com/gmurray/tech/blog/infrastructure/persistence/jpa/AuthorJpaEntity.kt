package com.gmurray.tech.blog.infrastructure.persistence.jpa

import com.gmurray.tech.blog.infrastructure.persistence.shared.AbstractJpaPersistableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name = "blog_author", schema = "tech_blog")
class AuthorJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_gen")
    @SequenceGenerator(name="author_gen", sequenceName="blog_author_seq", schema = "tech_blog", allocationSize = 1)
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "email")
    val email:String,

) : AbstractJpaPersistableEntity<String>()

{

}