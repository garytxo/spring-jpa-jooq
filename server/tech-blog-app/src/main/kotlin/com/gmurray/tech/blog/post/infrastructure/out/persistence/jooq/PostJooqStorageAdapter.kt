package com.gmurray.tech.blog.post.infrastructure.out.persistence.jooq

import com.gmurray.tech.blog.author.domain.AuthorId
import com.gmurray.tech.blog.infrastructure.persistence.jooq.AuthorJooqRepository
import com.gmurray.tech.blog.infrastructure.persistence.jooq.PostCategoryEntity
import com.gmurray.tech.blog.infrastructure.persistence.jooq.PostJooqEntity
import com.gmurray.tech.blog.infrastructure.persistence.jooq.PostJooqRepository
import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus
import com.gmurray.tech.blog.post.application.exception.PostAuthorDoesNotExistException
import com.gmurray.tech.blog.post.application.port.`in`.CreateBlogPostCommand
import com.gmurray.tech.blog.post.application.port.out.CreateBlogPostPort
import com.gmurray.tech.blog.post.application.port.out.FindBlogPostByIdPort
import com.gmurray.tech.blog.post.domain.Post
import com.gmurray.tech.blog.post.domain.PostCategory
import com.gmurray.tech.blog.post.domain.PostDescription
import com.gmurray.tech.blog.post.domain.PostId
import com.gmurray.tech.blog.post.domain.PostTitle

class PostJooqStorageAdapter(
    private val authorJooqRepository: AuthorJooqRepository,
    private val postJooqRepository: PostJooqRepository
) : CreateBlogPostPort, FindBlogPostByIdPort {


    override fun getBlogPostById(postId: PostId) =
        postJooqRepository.findPostBy(postId = postId.value())
            .toDomain()

    private fun PostJooqEntity.toDomain() =
        Post(
            id = PostId(this.id!!),
            title = PostTitle(this.title),
            description = PostDescription(this.description),
            categories = this.categories.map { PostCategory(it.name) }.toSet(),
            author = AuthorId(this.authorId)
        )

    override fun create(command: CreateBlogPostCommand) =
        postJooqRepository.save(command.toPostJooqEntity())

    private fun CreateBlogPostCommand.toPostJooqEntity() =
        PostJooqEntity(
            id = null,
            title = this.title,
            description = this.description,
            status = BlogPostStatus.DRAFT,
            categories = this.toPostCategories(),
            authorId = this.getAuthor().id!!,
        )

    private fun CreateBlogPostCommand.toPostCategories() =
        this.categories.map { PostCategoryEntity(null, it.name) }.toList()


    private fun CreateBlogPostCommand.getAuthor() =
        authorJooqRepository.findById(this.authorId)
            ?: throw PostAuthorDoesNotExistException("No author exist with id:${this.authorId}")


}