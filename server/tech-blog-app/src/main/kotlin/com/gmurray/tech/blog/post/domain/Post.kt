package com.gmurray.tech.blog.post.domain

import com.gmurray.tech.blog.author.domain.AuthorId

data class Post(
    val id:PostId,
    val title : PostTitle,
    val description: PostDescription,
    val categories:Set<PostCategory>,
    val author:AuthorId
)
