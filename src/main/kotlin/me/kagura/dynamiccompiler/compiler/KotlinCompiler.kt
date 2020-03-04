package me.kagura.dynamiccompiler.compiler

import org.slf4j.LoggerFactory
import java.io.File
import java.net.URLClassLoader
import java.nio.file.Files

class KotlinCompiler {

    fun kotlinc(source: String): Class<*>? {
        val logger = LoggerFactory.getLogger("KotlinCompiler")
        val packageName = Regex("""package[ ]+([\w._\p{S}]+)""").find(source)?.groupValues?.get(1) ?: ""
        val className = Regex("""class[ ]+(\S*)""").find(source)?.groupValues?.get(1) ?: ""
        var canonicalName = ""
        if (packageName.isNotEmpty()) {
            canonicalName += "$packageName."
        }
        canonicalName += className

        val kotlinDynamicCompiler = KotlinDynamicCompiler()

        try {
            val outputDir = Files.createTempDirectory(System.currentTimeMillis().toString()).toFile()
            val key = "${System.currentTimeMillis()}"
            outputDir.absolutePath
            val ktFile = File(outputDir, "$key.kt")
            ktFile.writeText(source)
            kotlinDynamicCompiler.compileModule("", listOf(ktFile.absolutePath), outputDir, Thread.currentThread().contextClassLoader)
            val uri = arrayOf(outputDir.toURI().toURL())
            val classLoader = URLClassLoader.newInstance(uri)
            return classLoader.loadClass(canonicalName)
        } catch (e: Exception) {
            logger.error("Kotlin编译失败,source={}", source, e)
            e.printStackTrace()
        }
        return null
    }

}