package com.gmurray.tech.blog.post.infrastructure.out.persistence.jpa

import com.gmurray.tech.blog.fixtures.slices.JpaDbTest
import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaEntity
import com.gmurray.tech.blog.infrastructure.persistence.jpa.AuthorJpaRepository
import com.gmurray.tech.blog.post.application.exception.PostAuthorDoesNotExistException
import com.gmurray.tech.blog.post.application.port.in.CreateBlogPostUseCase
import com.gmurray.tech.blog.post.domain.Categories
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@JpaDbTest
@ActiveProfiles(["test","jpa"])
class PostJpaStorageAdapterIT extends Specification {

    @Autowired
    AuthorJpaRepository authorJpaRepository

    @Autowired
    PostJpaStorageAdapter postJpaStorageAdapter

    def "create should throw error when author does not exist"(){

        given:
        def authorId = 111L
        def title = "post title"
        def description = "post description"
        def tags = ["Tag","Tag2"].toSet()
        def categories = [Categories.ENTERTAINMENT].toSet()
        def command = new CreateBlogPostUseCase.NewBlogPostCommand(authorId,title,description,tags,categories)

        when:
        def result = postJpaStorageAdapter.create(command)

        then:
        thrown(PostAuthorDoesNotExistException)

    }

    def "create should create new post for author"(){

        given:
        def author = createAuthor("Joe","Test")
        def title = "post title"
        def description = "post description"
        def tags = ["Tag","Tag2"].toSet()
        def categories = [Categories.ENTERTAINMENT].toSet()
        def command = new CreateBlogPostUseCase.NewBlogPostCommand(author.id,title,description,tags,categories)

        when:
        def result = postJpaStorageAdapter.create(command)

        then:
        result > 0

    }

    def createAuthor(String fname="fname", String lname="lname"){
        def author = new AuthorJpaEntity(null,fname,lname)
        return authorJpaRepository.save(author)
    }

}
