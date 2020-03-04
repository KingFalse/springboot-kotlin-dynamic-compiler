package me.kagura.dynamiccompiler.listener

import me.kagura.dynamiccompiler.utils.unJar
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class InitClassPath : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(p0: ApplicationReadyEvent) {
        unJar()
    }
}