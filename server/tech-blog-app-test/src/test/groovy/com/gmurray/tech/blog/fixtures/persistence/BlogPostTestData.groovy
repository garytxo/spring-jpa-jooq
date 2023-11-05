package com.gmurray.tech.blog.fixtures.persistence


import com.gmurray.tech.blog.infrastructure.persistence.shared.BlogPostStatus

class BlogPostTestData {

    def blogAuthor = new AuthorTestData()
    def blogPost = new PostTestData(authorId: blogAuthor.id)


}

enum TestPostCategory {
    TECHNOLOGY,
    FINANCE,
    FOOD,
    ENTERTAINMENT,
    SCIENCE,
    MUSIC,
    SPORT,
    OTHER
}

class PostTestData {
    Long id
    String title
    String description
    Long authorId
    BlogPostStatus status
    Set<TestPostCategory> categories

    PostTestData(Long id = 11L, String title = "Post title", String description = "Post description", Long authorId = 1L,
                 BlogPostStatus status = BlogPostStatus.DRAFT,
                 Set<TestPostCategory> categories = [TestPostCategory.TECHNOLOGY].toSet()) {
        this.id = id
        this.title = title
        this.description = description
        this.authorId = authorId
        this.status = status
        this.categories = categories
    }


    @Override
    public String toString() {
        return "PostTestData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", status=" + status +
                ", categories=" + categories +
                '}';
    }
}

class AuthorTestData{
    Long id
    String firstName
    String lastName
    String email
    AuthorTestData(Long id = 1L, String firstName= "Joe", String lastName= "Bloggs",String email="joe.blogg1@test.com") {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }


    @Override
    public String toString() {
        return "AuthorTestData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
