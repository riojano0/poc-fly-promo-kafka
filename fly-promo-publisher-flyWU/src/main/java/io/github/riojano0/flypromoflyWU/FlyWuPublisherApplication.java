package io.github.riojano0.flypromoflyWU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.riojano0.flypromokafka.spring.core.annotation.EnableKafkaProducer;
import io.github.riojano0.flypromokafka.spring.core.annotation.EnableKafkaTopics;

@EnableKafkaTopics
@EnableKafkaProducer
@SpringBootApplication
public class FlyWuPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyWuPublisherApplication.class, args);
	}

}
