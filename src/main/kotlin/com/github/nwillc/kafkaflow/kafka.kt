package com.github.nwillc.kafkaflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.time.Duration
import java.util.Properties
import java.util.UUID

const val stringTopic = "strings"
const val stringGroup = "string-processor"

fun createProducer(brokers: String): Producer<String, String> {
    val props = Properties()
    props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = brokers
    props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
    props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
    return KafkaProducer(props)
}

fun createConsumer(brokers: String): Consumer<String, String> {
    val props = Properties()
    props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = brokers
    props[ConsumerConfig.GROUP_ID_CONFIG] = UUID.randomUUID().toString()
    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
    props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
    return KafkaConsumer(props)
}

fun <K, V> Consumer<K, V>.subscribeFlow(topics: List<String>): Flow<ConsumerRecord<K?, V?>> = flow {
    subscribe(topics)
    while (true) {
        val records = poll(Duration.ofSeconds(1))
        records.iterator().forEach {
            emit(it)
            kotlinx.coroutines.delay(1)
        }
    }
}
