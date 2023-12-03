package com.gmurray.tech.blog.shared.application.service

interface CommandHandler<R, C : Command<R>> {

    fun execute(command: C): R
}