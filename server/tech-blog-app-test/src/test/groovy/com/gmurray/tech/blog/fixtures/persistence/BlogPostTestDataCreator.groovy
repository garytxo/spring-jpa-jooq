package com.gmurray.tech.blog.fixtures.persistence


import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.BlogAuthor
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.BlogPost
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostCategory
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.PostPostCategory as PostPostCategoryTable
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogAuthorDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogPostDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.PostCategoryDao
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.PostPostCategoryDao
import com.gmurray.tech.blog.shared.infrastructure.config.persistence.jooq.JooqUserAuditAwareService
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.time.LocalDateTime

@Service
class BlogPostTestDataCreator {

    @Autowired
    DSLContext dslContext

    @Autowired
    BlogAuthorDao blogAuthorDao

    @Autowired
    BlogPostDao blogPostDao

    @Autowired
    PostCategoryDao postCategoryDao

    @Autowired
    PostPostCategoryDao postPostCategoryDao

    @Autowired
    JooqUserAuditAwareService jooqUserAuditAwareService


    def cleanUp() {

        def postDeleted = dslContext.deleteFrom(BlogPost.@Companion.BLOG_POST).execute()
        println("BlogPostTestDataCreator:: removed:${postDeleted} blog posts")

        def authorDeleted = dslContext.deleteFrom(BlogAuthor.@Companion.BLOG_AUTHOR).execute()
        println("BlogPostTestDataCreator:: removed:${authorDeleted} blog authors")
    }

    @Transactional
    def createBlogPostWith(BlogPostTestData blogPostTestData) {

        persist(blogPostTestData.blogAuthor)
        persist(blogPostTestData.blogPost)
    }

    def createBlogAuthorWith(BlogPostTestData blogPostTestData) {
        persist(blogPostTestData.blogAuthor)

    }

    def persist(PostTestData postTestData) {


        def post = dslContext.fetchOne(BlogPost.@Companion.BLOG_POST, BlogPost.@Companion.BLOG_POST.ID.eq(postTestData.id))
        if (post == null) {
            post = dslContext.newRecord(BlogPost.@Companion.BLOG_POST)
            post.id = postTestData.id
        }
        post.authorId = postTestData.authorId
        post.title = postTestData.title
        post.description = postTestData.description
        post.status = postTestData.status


        post.store()

        println("BlogPostTestDataCreator:: Test data blog post created with id:${post.id}")

        def categories = postTestData.categories.stream().map {it.name()}.toList()
        persistPostCategories(post.id,categories)


    }

    def persistPostCategories(Long postId, List<String> categoriesNames){

        def categories = postCategoryDao.fetch(PostCategory.@Companion.POST_CATEGORY.NAME,categoriesNames)

        categories.forEach {postcategory->
            def postpostcategory  = dslContext.fetchOne(
                    PostPostCategoryTable.@Companion.POST_POST_CATEGORY,
                    PostPostCategoryTable.@Companion.POST_POST_CATEGORY.POST_ID.eq(postId).and(PostPostCategoryTable.@Companion.POST_POST_CATEGORY.CATEGORY_ID.eq(postcategory.id))
            )
            if(postpostcategory==null){
                postpostcategory = dslContext.newRecord(PostPostCategoryTable.@Companion.POST_POST_CATEGORY)
                postpostcategory.postId = postId
                postpostcategory.categoryId = postcategory.id
                postpostcategory.store()
            }else{
                println("BlogPostTestDataCreator::PostPostCategory exist for post:${postId} cat:${postcategory.id}")
            }

        }
        println("BlogPostTestDataCreator:: Create PostPostCategories ${categories.size()}")
    }

    def persist(AuthorTestData authorTestData){

        dslContext.insertInto(BlogAuthor.@Companion.BLOG_AUTHOR)
                .columns(BlogAuthor.@Companion.BLOG_AUTHOR.ID,
                        BlogAuthor.@Companion.BLOG_AUTHOR.FIRST_NAME,
                        BlogAuthor.@Companion.BLOG_AUTHOR.LAST_NAME,
                        BlogAuthor.@Companion.BLOG_AUTHOR.EMAIL,

                        //Need to add because the audit listener is not picked up using the dsl
                        BlogAuthor.@Companion.BLOG_AUTHOR.ROW_CREATED_BY,
                        BlogAuthor.@Companion.BLOG_AUTHOR.ROW_CREATED_ON
                    )
                .values(
                        authorTestData.id, authorTestData.firstName,authorTestData.lastName,authorTestData.email,
                        jooqUserAuditAwareService.currentUserId,
                        LocalDateTime.now()
                )
                .onDuplicateKeyIgnore()
                .execute()

        println("BlogPostTestDataCreator:: Create of replace user:${authorTestData}")
    }

}
