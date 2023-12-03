package com.gmurray.tech.blog.shared.application.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.reflect.ParameterizedType


@Service
class CommandBus(
    commandHandlers: List<CommandHandler<*, *>>
) {

    private val logger = LoggerFactory.getLogger(CommandBus::class.java)

    private var handlers: Map<Class<*>, CommandHandler<*, *>> = commandHandlers.associateBy {
        (it.javaClass.genericInterfaces[0] as ParameterizedType)
            .actualTypeArguments[1] as Class<*>
    }


    init {
        logger.info("Command Handlers loaded ${handlers.size}")
    }

    @Suppress("UNCHECKED_CAST")
    fun <R, C : Command<R>> dispatch(command: C): R {
        val handler = handlers[command::class.java]
            ?: throw IllegalArgumentException("No handler for command type ${command::class.java.simpleName}")

        return (handler as CommandHandler<R, C>).execute(command)
    }

}