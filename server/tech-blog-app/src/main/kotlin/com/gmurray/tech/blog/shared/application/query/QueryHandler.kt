package com.gmurray.tech.blog.shared.application.query

interface QueryHandler<R, Q : Query<R>> {

    fun handle(query: Q): R
}