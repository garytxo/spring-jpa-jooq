package com.gmurray.tech.blog.shared.application.command

interface CommandHandler<R, C : Command<R>> {

    fun execute(command: C): R
}