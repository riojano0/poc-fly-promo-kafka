package io.github.riojano0.flypromokafka.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.riojano0.flypromokafka.core.consumer.KafkaConsumerConfig;

import org.springframework.context.annotation.Import;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ KafkaConsumerConfig.class })
public @interface EnableKafkaConsumer {

}
