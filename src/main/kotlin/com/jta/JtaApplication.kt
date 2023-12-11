package com.jta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class JtaApplication

fun main(args: Array<String>) {
    runApplication<JtaApplication>(*args)
}
