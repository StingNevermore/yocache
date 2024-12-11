package com.nevermore.yocache.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author nevermore
 * @since 0.0.1
 */
@SpringBootApplication
class YocacheServerApplication

fun main(args: Array<String>) {
    runApplication<YocacheServerApplication>(*args)
}
