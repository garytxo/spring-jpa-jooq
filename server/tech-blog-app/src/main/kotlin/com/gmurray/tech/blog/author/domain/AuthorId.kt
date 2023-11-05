package com.gmurray.tech.blog.author.domain

data class AuthorId(private val value:Long){

    fun value() = value

    override fun toString() = "$value"


}