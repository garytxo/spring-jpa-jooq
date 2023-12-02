package com.gmurray.tech.blog.shared.application.service

interface CommandHandler<R, C : Command<R>> {

    fun handle(command: C): R
}