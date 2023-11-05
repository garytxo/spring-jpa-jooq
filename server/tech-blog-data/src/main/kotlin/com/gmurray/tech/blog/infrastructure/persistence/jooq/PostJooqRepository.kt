package com.gmurray.tech.blog.infrastructure.persistence.jooq

import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostPostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogPostDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.PostCategoryDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost
import com.gmurray.tech.blog.infrastructure.persistence.exception.PostNotFoundException
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.PostCategory as PostCategoryPojo

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
    fun save(blogPost: PostJooqEntity): Long {
        val newPost = blogPost.newPost()
        newPost.savePostCategories()
        return newPost.id!!
    }

    private fun PostJooqEntity.newPost(): PostJooqEntity {
        val jooqPojo = this.toBlogPostPojo()
        dao.insert(jooqPojo)
        return jooqPojo.toJooqEntity()
    }

    private fun PostJooqEntity.savePostCategories() {
        val categories = categoryDao.fetch(PostCategory.POST_CATEGORY.NAME, this.categories)
        categories.forEach { postCategory ->
            postCategory.mergePostCategories(postId = this.id!!)
        }

    }

    private fun PostCategoryPojo.mergePostCategories(postId: Long) {
        dslContext().insertInto(PostPostCategory.POST_POST_CATEGORY)
            .columns(PostPostCategory.POST_POST_CATEGORY.POST_ID, PostPostCategory.POST_POST_CATEGORY.CATEGORY_ID)
            .values(postId, this.id!!)
            .onDuplicateKeyIgnore()
            .execute()
    }


    private fun PostJooqEntity.toBlogPostPojo() =
        BlogPost(
            id = this.id,
            title = this.title,
            description = this.description,
            authorId = this.authorId,
            tags = this.tags.joinToString("  ") { it.lowercase().trim() },
            status = this.status
        )

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
            categories = this.postCategories()
        )

    private fun BlogPost.postCategories() =
        dslContext().select(PostCategory.POST_CATEGORY.NAME)
            .from(PostCategory.POST_CATEGORY)
            .join(PostPostCategory.POST_POST_CATEGORY)
            .on(PostPostCategory.POST_POST_CATEGORY.CATEGORY_ID.eq(PostCategory.POST_CATEGORY.ID))
            .where(PostPostCategory.POST_POST_CATEGORY.POST_ID.eq(this.id!!))
            .fetch(PostCategory.POST_CATEGORY.NAME)
            .mapNotNull { it }.toSet()
    
}