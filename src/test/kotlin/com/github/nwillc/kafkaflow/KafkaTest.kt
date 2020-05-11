package com.github.nwillc.kafkaflow

import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Duration

@Testcontainers
class KafkaTest {
    @Container
    val kafkaContainer = KafkaContainer()

    @Test
    internal fun `should receive a message`() {
        val message = "Hello World"
        val producer = createProducer(kafkaContainer.bootstrapServers)
        val future = producer.send(ProducerRecord(stringTopic, message))
        future.get()
        val consumer = createConsumer(kafkaContainer.bootstrapServers)
        consumer.subscribe(listOf(stringTopic))
        var read = false
        while (!read) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.iterator().forEach {
                if (it.value() == message) read = true
            }
        }
    }

}

