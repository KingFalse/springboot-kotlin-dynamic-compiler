package me.kagura.dynamiccompiler.utils

import org.springframework.boot.system.ApplicationHome
import java.io.File
import java.nio.file.Files
import java.util.jar.JarFile

var JAR_CLASS_PATH: Array<File> = emptyArray()

/**
 * 解压当前Jar包并拼接ClassPath
 */
fun unJar() {
    val tempDirectory = Files.createTempDirectory("").toFile()
    val source = ApplicationHome().source ?: return
    val jarFile = JarFile(source)
    jarFile.entries().asSequence().forEach {
        val tempFile = File(tempDirectory, it.name)
        if (it.isDirectory) {
            tempFile.mkdirs()
        } else {
            val inputStream = jarFile.getInputStream(it)
            Files.copy(inputStream, tempFile.toPath())
        }
    }
    jarFile.close()
    val lib = File(tempDirectory, "BOOT-INF${File.separator}lib")
    val classes = File(tempDirectory, "BOOT-INF${File.separator}classes")
    JAR_CLASS_PATH = lib.listFiles().plus(classes)
}