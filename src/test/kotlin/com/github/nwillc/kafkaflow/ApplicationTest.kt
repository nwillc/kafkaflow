package com.github.nwillc.kafkaflow

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.KafkaContainer

class ApplicationTest {
    @Test
    fun `should have Application`() {
        assertThat(Application::javaClass).isNotNull()
    }
}
