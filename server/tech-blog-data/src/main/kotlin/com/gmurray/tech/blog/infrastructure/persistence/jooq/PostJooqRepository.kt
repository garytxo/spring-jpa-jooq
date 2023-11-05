package com.gmurray.tech.blog.infrastructure.persistence.jooq

import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostPostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogPostDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.PostCategoryDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost
import com.gmurray.tech.blog.infrastructure.persistence.exception.PostNotFoundException
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class PostJooqRepository(
    private val categoryDao: PostCategoryDao,
    dslContext: DSLContext,
    blogPostDao: BlogPostDao
) :
    AbstractBaseJooqRepository<BlogPostDao>(dslContext, blogPostDao) {


    /**
     * Save using  the JOOQ generate POJOs and DAOs
     */
    fun save(newBlogPost: NewBlogPost): Long {
        dao.insert(newBlogPost.blogPost)
        savePostCategories(newBlogPost)
        return newBlogPost.blogPost.id!!

    }

    fun findPostBy(postId: Long): PostJooqEntity {
        val post = dao.fetchOneById(postId) ?: throw PostNotFoundException("No post found for id:$postId")
        return post.toJooqEntity()
    }

    private fun BlogPost.toJooqEntity() =
        PostJooqEntity(
            id = this.id!!,
            title = this.title!!,
            description = this.description!!,
            tags = emptySet(),
            status = this.status!!,
            authorId = this.authorId!!,
            categories = findCategoryNamesFor(this.id!!)
        )

    private fun findCategoryNamesFor(postId: Long) =
        dslContext().select(PostCategory.POST_CATEGORY.NAME)
            .from(PostCategory.POST_CATEGORY)
            .join(PostPostCategory.POST_POST_CATEGORY)
            .on(PostPostCategory.POST_POST_CATEGORY.CATEGORY_ID.eq(PostCategory.POST_CATEGORY.ID))
            .where(PostPostCategory.POST_POST_CATEGORY.POST_ID.eq(postId))
            .fetch(PostCategory.POST_CATEGORY.NAME)
            .mapNotNull { it }.toSet()


    private fun savePostCategories(newBlogPost: NewBlogPost) {
        val categories = categoryDao.fetch(PostCategory.POST_CATEGORY.NAME, newBlogPost.categoriesNames)
        categories.forEach { postCategory ->
            mergePostCategories(postId = newBlogPost.blogPost.id!!, postCategoryId = postCategory.id!!)
        }

    }

    private fun mergePostCategories(postId: Long, postCategoryId: Long) {
        dslContext().insertInto(PostPostCategory.POST_POST_CATEGORY)
            .columns(PostPostCategory.POST_POST_CATEGORY.POST_ID, PostPostCategory.POST_POST_CATEGORY.CATEGORY_ID)
            .values(postId, postCategoryId)
            .onDuplicateKeyIgnore()
            .execute()
    }

    data class NewBlogPost(
        val blogPost: BlogPost,
        val categoriesNames: Set<String>
    )


}