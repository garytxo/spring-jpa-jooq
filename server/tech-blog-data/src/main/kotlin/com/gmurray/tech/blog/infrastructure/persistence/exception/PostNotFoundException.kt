package com.gmurray.tech.blog.infrastructure.persistence.exception

class PostNotFoundException(override val message: String, override val cause: Throwable? = null) : RuntimeException() {
}