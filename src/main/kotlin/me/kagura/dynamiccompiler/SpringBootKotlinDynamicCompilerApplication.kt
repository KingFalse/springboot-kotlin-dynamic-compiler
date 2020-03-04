package me.kagura.dynamiccompiler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinDynamicCompilerApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinDynamicCompilerApplication>(*args)
}
