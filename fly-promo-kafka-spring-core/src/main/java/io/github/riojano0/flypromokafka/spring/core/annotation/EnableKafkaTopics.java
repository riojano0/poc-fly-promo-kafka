package io.github.riojano0.flypromokafka.spring.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.riojano0.flypromokafka.spring.core.config.KafkaTopicConfig;

import org.springframework.context.annotation.Import;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ KafkaTopicConfig.class})
public @interface EnableKafkaTopics {

}
