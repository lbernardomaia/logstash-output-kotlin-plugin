package com.logstash

import co.elastic.logstash.api.Configuration
import co.elastic.logstash.api.Event
import com.logstash.KotlinOutputPlugin.Companion.PREFIX_CONFIG
import org.junit.jupiter.api.Test
import org.logstash.plugins.ConfigurationImpl
import org.logstash.plugins.ContextImpl
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue


class KotlinInputPluginTest {

    @Test
    fun `testJavaOutputExample`() {
        val prefix = "Prefix-"
        val configValues = mapOf(PREFIX_CONFIG.name() to prefix)
        val config: Configuration = ConfigurationImpl(configValues)

        val byteArrayOutputStream = ByteArrayOutputStream()
        val kotlinOutputPlugin = KotlinOutputPlugin(
                "test-id",
                config,
                ContextImpl(null, null),
                PrintStream(byteArrayOutputStream)
        )

        val event: Event = org.logstash.Event()
        val expectedEventMeMessage = "Input Message"
        event.setField("message", expectedEventMeMessage)

        kotlinOutputPlugin.output(listOf(event))

        assertTrue {
            byteArrayOutputStream.toString().contains(prefix)
                    && byteArrayOutputStream.toString().contains(expectedEventMeMessage)
        }
    }
}