package com.github.nwillc.kafkaflow

import kotlinx.coroutines.runBlocking
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.Duration

class KafkaTest {
    @Disabled
    @Test
    internal fun `should send a string`() = runBlocking {
        val producer = createProducer("localhost:9092")
        val future = producer.send(ProducerRecord(stringTopic, "hello world"))
        val result = future.get()
    }

    @Test
    internal fun `should receive a message`() {
        val consumer = createConsumer("localhost:9092")
        consumer.subscribe(listOf(stringTopic))
        var read = false
        while (!read) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.iterator().forEach {
                println(">>> ${it.value()}")
                read = true
            }
        }
    }
}
