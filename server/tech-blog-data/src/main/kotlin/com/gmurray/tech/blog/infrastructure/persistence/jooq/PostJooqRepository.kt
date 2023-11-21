package com.gmurray.tech.blog.infrastructure.persistence.jooq

import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.BlogPost.Companion.BLOG_POST
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostCategory.Companion.POST_CATEGORY
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostPostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostPostCategory.Companion.POST_POST_CATEGORY
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogPostDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.PostCategoryDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.pojos.BlogPost
import com.gmurray.tech.blog.infrastructure.persistence.exception.PostNotFoundException
import org.jooq.DSLContext
import org.jooq.impl.DSL.multiset
import org.jooq.kotlin.mapping
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
        return findPostBy(jooqPojo.id!!)
    }

    private fun PostJooqEntity.savePostCategories() {
        val categories = categoryDao.fetch(POST_CATEGORY.NAME, this.categories.map { it.name })
        categories.forEach { postCategory ->
            postCategory.mergePostCategories(postId = this.id!!)
        }

    }

    private fun PostCategoryPojo.mergePostCategories(postId: Long) {
        dslContext().insertInto(POST_POST_CATEGORY)
            .columns(POST_POST_CATEGORY.POST_ID, PostPostCategory.POST_POST_CATEGORY.CATEGORY_ID)
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
            status = this.status
        )

    fun findPostBy(postId: Long) =
        dslContext().select(
            BLOG_POST.ID,
            BLOG_POST.TITLE,
            BLOG_POST.DESCRIPTION,
            BLOG_POST.STATUS,
            BLOG_POST.AUTHOR_ID.`as`("authorId"),
            multiset(
                dslContext().select(
                    POST_CATEGORY.ID,
                    POST_CATEGORY.NAME
                )
                    .from(POST_CATEGORY)
                    .join(POST_POST_CATEGORY).on(POST_POST_CATEGORY.CATEGORY_ID.eq(POST_CATEGORY.ID))
                    .where(POST_POST_CATEGORY.POST_ID.eq(postId))
            ).mapping { id, name -> PostCategoryEntity(id, name!!) }.`as`("categories")
        ).from(BLOG_POST)
            .where(BLOG_POST.ID.eq(postId))
            .fetchOneInto(PostJooqEntity::class.java) ?: throw PostNotFoundException("No post found for id:$postId")
    

}