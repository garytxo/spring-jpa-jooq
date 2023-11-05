package com.gmurray.tech.blog.author.domain

data class BlogAuthor private constructor(
    private val id: AuthorId,
    val firstName: String,
    val lastName: String,
) {

    fun fullName() = "$lastName , $firstName"

    fun id() = this.id

    companion object {

        fun toAuthor(id:Long,firstName: String,lastName: String) =
            BlogAuthor(AuthorId(id),firstName,lastName)

    }
}