package me.kagura.dynamiccompiler.controller

import me.kagura.dynamiccompiler.compiler.KotlinCompiler
import me.kagura.dynamiccompiler.script.Worker
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExecController {

    @PostMapping("/exec")
    fun exec(@RequestBody source: String): String {
        val newInstance = KotlinCompiler().kotlinc(source)?.newInstance() ?: return "Compilation error."
        val worker = newInstance as Worker
        return worker.doWork()
    }

}