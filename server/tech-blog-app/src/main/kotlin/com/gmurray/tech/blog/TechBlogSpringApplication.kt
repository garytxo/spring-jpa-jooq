package com.gmurray.tech.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TechBlogSpringApplication

fun main(args: Array<String>) {
    runApplication<TechBlogSpringApplication>(*args)
}