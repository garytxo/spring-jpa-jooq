package com.gmurray.tech.blog.post.infrastructure.out.persistence.jooq

import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.infrastructure.persistence.jooq.AuthorJooqRepository
import com.gmurray.tech.blog.infrastructure.persistence.jooq.PostJooqEntity
import com.gmurray.tech.blog.infrastructure.persistence.jooq.PostJooqRepository
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus
import com.gmurray.tech.blog.post.application.exception.PostAuthorDoesNotExistException
import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostUseCase
import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.application.port.out.GetBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostCategory
import com.gmurray.tech.blog.post.domain.PostDescription
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.post.domain.PostTag
import com.gmurray.tech.blog.post.domain.PostTitle

class PostJooqStorageAdapter(
    private val authorJooqRepository: AuthorJooqRepository,
    private val postJooqRepository: PostJooqRepository
) : CreateBlogPostPort, GetBlogPostByIdPort {


    override fun getBlogPostById(postId: PostId) =
        postJooqRepository.findPostBy(postId = postId.value())
            .toDomain()

    private fun PostJooqEntity.toDomain() =
        Post(
            id = PostId(this.id!!),
            title = PostTitle(this.title),
            description = PostDescription(this.description),
            tags = this.tags.map { PostTag(it) }.toSet(),
            categories = this.categories.map { PostCategory(it) }.toSet(),
            author = AuthorId(this.authorId)
        )

    override fun create(newBlogPostCommand: CreateBlogPostUseCase.NewBlogPostCommand) =
        postJooqRepository.save(newBlogPostCommand.toPostJooqEntity())

    private fun CreateBlogPostUseCase.NewBlogPostCommand.toPostJooqEntity() =
        PostJooqEntity(
            id = null,
            title = this.title,
            description = this.description,
            tags = this.tags,
            status = BlogPostStatus.DRAFT,
            categories = this.toCategories(),
            authorId = this.getAuthor().id!!,
        )

    private fun CreateBlogPostUseCase.NewBlogPostCommand.toCategories() =
        this.categories.map { it.name }.toSet()


    private fun CreateBlogPostUseCase.NewBlogPostCommand.getAuthor() =
        authorJooqRepository.findById(this.authorId)
            ?: throw PostAuthorDoesNotExistException("No author exist with id:${this.authorId}")


}