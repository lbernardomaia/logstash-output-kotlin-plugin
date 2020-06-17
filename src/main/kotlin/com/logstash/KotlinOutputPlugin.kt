package com.logstash

import co.elastic.logstash.api.*
import co.elastic.logstash.api.PluginConfigSpec
import java.io.PrintStream
import java.util.concurrent.CountDownLatch

@LogstashPlugin(name = "kotlin_output_plugin")
class KotlinOutputPlugin @JvmOverloads constructor(
        private val pluginId: String,
        private val config: Configuration,
        private val context: Context,
        private val printer: PrintStream = PrintStream(System.out),
        private val prefix: String = config[PREFIX_CONFIG]
) : Output {

    @Volatile private var stopped = false
    private val done = CountDownLatch(1)

    companion object {
        val PREFIX_CONFIG: PluginConfigSpec<String> = PluginConfigSpec.stringSetting("prefix", "")
    }

    override fun output(events: Collection<Event>) {
        with(events.iterator()){
            while (this.hasNext() && !stopped) {
                val s = prefix + this.next()
                printer.println(s)
            }
        }
    }

    override fun stop() {
        stopped = true
        done.countDown()
    }

    @Throws(InterruptedException::class)
    override fun awaitStop() = done.await()

    override fun configSchema() = listOf(PREFIX_CONFIG)

    override fun getId() = pluginId
}