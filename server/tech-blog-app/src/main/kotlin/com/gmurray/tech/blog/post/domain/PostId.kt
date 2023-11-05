package com.gmurray.tech.blog.post.domain

data class PostId (private val value:Long){

    fun value() = value

    override fun toString() = "$value"

}
