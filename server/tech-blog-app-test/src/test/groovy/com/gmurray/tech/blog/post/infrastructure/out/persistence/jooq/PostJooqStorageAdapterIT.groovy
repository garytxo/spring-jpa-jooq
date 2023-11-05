package com.gmurray.tech.blog.post.infrastructure.out.persistence.jooq

import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestData
import com.gmurray.tech.blog.fixtures.persistence.BlogPostTestDataCreator
import com.gmurray.tech.blog.fixtures.persistence.TestPostCategory
import com.gmurray.tech.blog.fixtures.slices.JooqDbTest
import com.gmurray.tech.blog.infrastructure.jooq.persistence.tables.daos.BlogPostDao
import com.gmurray.tech.blog.post.application.exception.PostAuthorDoesNotExistException
import com.gmurray.tech.blog.post.application.port.in.CreateBlogPostUseCase
import com.gmurray.tech.blog.post.domain.Categories
import com.gmurray.tech.blog.post.domain.PostId
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@JooqDbTest
@ActiveProfiles(["test","jooq"])
class PostJooqStorageAdapterIT extends Specification {


    @Autowired
    PostJooqStorageAdapter postJooqStorageAdapter

    @Autowired
    BlogPostTestDataCreator blogPostTestDataCreator

    @Autowired
    BlogPostDao blogPostDao

    def cleanup() {
        blogPostTestDataCreator.cleanUp()
    }

    def "create should throw error when author does not exist"() {

        given:
        def authorId = 111L
        def title = "post title"
        def description = "post description"
        def tags = ["Tag", "Tag2"].toSet()
        def categories = [Categories.ENTERTAINMENT].toSet()
        def command = new CreateBlogPostUseCase.NewBlogPostCommand(authorId, title, description, tags, categories)

        when:
        postJooqStorageAdapter.create(command)

        then:
        thrown(PostAuthorDoesNotExistException)

    }

    def "create should create new post for author"(){

        given:
        def author = createAuthor("Joe", "Test").blogAuthor
        def title = "post title"
        def description = "post description"
        def tags = ["tag","tag2"].toSet()
        def categories = [Categories.ENTERTAINMENT].toSet()
        def command = new CreateBlogPostUseCase.NewBlogPostCommand(author.id,title,description,tags,categories)

        when:
        def newPostId = postJooqStorageAdapter.create(command)

        then:
        newPostId > 0
        def result = blogPostDao.fetchOneById(newPostId.toLong())
        result.title == title
        result.authorId == author.id
        result.description == description
        result.tags == StringUtils.join(tags, "  ")


    }

    def "getBlogPostById should return post details for valid unique identifier "() {
        given:
        def testPost = createPost()
        def postId = new PostId(testPost.blogPost.id)

        when:
        def result = postJooqStorageAdapter.getBlogPostById(postId)

        then:
        result != null
        result.title.value == testPost.blogPost.title
        result.author.value() == testPost.blogAuthor.id
        result.description.value == testPost.blogPost.description
        result.categories.size() == testPost.blogPost.categories.size()
        result.categories.forEach { pc ->
            assert testPost.blogPost.categories.find { it.name() == pc.value }
        }

    }

    def createAuthor(String fname = "fname", String lname = "lname") {
        def testData = new BlogPostTestData()
        testData.blogAuthor.firstName = fname
        testData.blogAuthor.lastName = lname

        blogPostTestDataCreator.createBlogAuthorWith(testData)

        return testData
    }

    def createPost(String title = "post title",
                   String description = "post description",
                   Set<TestPostCategory> categories = [TestPostCategory.SPORT, TestPostCategory.ENTERTAINMENT].toSet()

    ) {
        def testData = new BlogPostTestData()
        testData.blogPost.title = title
        testData.blogPost.description = description
        testData.blogPost.categories = categories

        blogPostTestDataCreator.createBlogPostWith(testData)

        return testData
    }
}
