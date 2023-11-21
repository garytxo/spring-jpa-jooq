package com.gmurray.tech.blog.post.infrastructure.out.persistence.jpa

import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.infrastructure.persistence.exception.PostNotFoundException
import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.jpa.CategoryJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.jpa.PostJpaEntity
import com.gmurray.tech.blog.infrastructure.persistence.jpa.PostJpaRepository
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus
import com.gmurray.tech.blog.post.application.exception.PostAuthorDoesNotExistException
import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostUseCase
import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.application.port.out.FindBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostCategory
import com.gmurray.tech.blog.post.domain.PostDescription
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.post.domain.PostTitle

class PostJpaStorageAdapter(
    private val authorJpaRepository: AuthorJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val categoryJpaRepository: CategoryJpaRepository
) : CreateBlogPostPort, FindBlogPostByIdPort {


    override fun create(newBlogPostCommand: CreateBlogPostUseCase.NewBlogPostCommand): Long {
        val post = postJpaRepository.save(newBlogPostCommand.toNewPostJpaEntity())
        return post.id!!
    }

    override fun getBlogPostById(postId: PostId): Post {
        return postJpaRepository.findById(postId.value())
            .orElseThrow { throw PostNotFoundException("No post found for id:$postId") }
            .toPost()
    }

    private fun PostJpaEntity.toPost() =
        Post(
            id = PostId(this.id!!),
            title = PostTitle(this.title),
            description = PostDescription(this.description),
            categories = this.categories?.map { PostCategory(it.name) }?.toSet() ?: emptySet(),
            author = AuthorId(this.author.id!!)

        )

    private fun CreateBlogPostUseCase.NewBlogPostCommand.toNewPostJpaEntity() =
        PostJpaEntity(
            author = this.getAuthor(),
            title = this.title,
            description = this.description,
            status = BlogPostStatus.DRAFT,
            categories = this.toJpaCategories()
        )

    private fun CreateBlogPostUseCase.NewBlogPostCommand.toJpaCategories() =
        this.categories.map {
            categoryJpaRepository.findByName(it.name)
        }.toSet()


    private fun CreateBlogPostUseCase.NewBlogPostCommand.getAuthor() =
            authorJpaRepository.findById(this.authorId)
                .orElseThrow { PostAuthorDoesNotExistException("No author exist with id:${this.authorId}") }
}