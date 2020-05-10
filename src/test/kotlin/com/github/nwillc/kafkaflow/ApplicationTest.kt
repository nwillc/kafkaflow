package com.github.nwillc.kafkaflow

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.KafkaContainer

class ApplicationTest {
    lateinit var kafkaContainer: KafkaContainer

    @BeforeEach
    fun setUp() {
        kafkaContainer = KafkaContainer("5.3.1")
        kafkaContainer.start()
    }

    @Test
    fun `should have Application`() {
        assertThat(Application::javaClass).isNotNull()
    }
}
