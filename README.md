# springboot-kotlin-dynamic-compiler
SpringBoot-Kotlin-Dynamic-Compiler

# Getting started
1. git clone https://github.com/KingFalse/springboot-kotlin-dynamic-compiler.git
2. mvn package
3. java -jar SpringBoot-Kotlin-Dynamic-Compiler.jar
4. Enjoy! 
```shell script
curl --location --request POST 'http://127.0.0.1:8080/exec' \
--header 'Content-Type: text/plain' \
--data-raw 'package me.kagura.dynamiccompiler.script
import me.kagura.dynamiccompiler.script.Worker

class KaguraWorker : Worker {
    override fun doWork(): String {
        return "https://www.kagura.me/"
    }
}'
```
