package io.github.riojano0.flypromofly2EU

import io.github.riojano0.flypromokafka.core.annotation.EnableKafkaProducer
import io.github.riojano0.flypromokafka.core.annotation.EnableKafkaTopics
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableKafkaTopics
@EnableKafkaProducer
@SpringBootApplication
class Fly2EUPublisherApplication

fun main(args: Array<String>) {
    runApplication<Fly2EUPublisherApplication>(*args)
}
