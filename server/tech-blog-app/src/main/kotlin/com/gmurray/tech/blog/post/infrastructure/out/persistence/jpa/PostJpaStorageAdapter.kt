package com.gmurray.tech.blog.post.infrastructure.out.persistence.jpa

import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.jpa.CategoryJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.jpa.PostJpaEntity
import com.gmurray.tech.blog.infrastructure.persistence.jpa.PostJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus
import com.gmurray.tech.blog.post.application.exception.PostAuthorDoesNotExistException
import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostUseCase
import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.application.port.out.GetBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostId

class PostJpaStorageAdapter(
    private val authorJpaRepository: AuthorJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val categoryJpaRepository: CategoryJpaRepository
) : CreateBlogPostPort, GetBlogPostByIdPort {


    override fun create(newBlogPostCommand: CreateBlogPostUseCase.NewBlogPostCommand): Long {

        val post = postJpaRepository.save(newBlogPostCommand.toNewBlogPost())

        return post.id!!
    }

    override fun getBlogPostById(postId: PostId): Post {
        TODO("Not yet implemented")
    }

    private fun CreateBlogPostUseCase.NewBlogPostCommand.toNewBlogPost() =
        PostJpaEntity(
            author = this.getAuthor(),
            title = this.title,
            description = this.description,
            tags = this.tags.map { it.lowercase().trim() }.joinToString("  "),
            status = BlogPostStatus.DRAFT,
            categories = this.toJpaCategories()
        )

    private fun CreateBlogPostUseCase.NewBlogPostCommand.toJpaCategories()=
        this.categories.map {
            categoryJpaRepository.findByName(it.name)
        }.toSet()


    private fun CreateBlogPostUseCase.NewBlogPostCommand.getAuthor() =
            authorJpaRepository.findById(this.authorId)
                .orElseThrow { PostAuthorDoesNotExistException("No author exist with id:${this.authorId}") }
}