package me.kagura.dynamiccompiler.compiler

import me.kagura.dynamiccompiler.script.Worker
import org.junit.jupiter.api.Test

internal class KotlinCompilerTest {

    @Test
    fun kotlinc() {

        val source = """
            package me.kagura.dynamiccompiler.script
            import me.kagura.dynamiccompiler.script.Worker

            class KaguraWorker : Worker {
                override fun doWork(): String {
                    return "https://www.kagura.me/"
                }
            }
        """.trimIndent()

        val clazz = KotlinCompiler().kotlinc(source)
        val worker = clazz?.newInstance() as Worker
        val result = worker.doWork()
        println(result)
    }
}