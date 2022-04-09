package io.github.riojano0.flypromoflyWU;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest(properties = {"kafka.bootstrapAddress:localhost:8888"})
@EmbeddedKafka(brokerProperties = {"listeners:PLAINTEXT://localhost:8888", "port=8888"})
class FlyWuPublisherApplicationTests {

	@Test
	void contextLoads() {
	}

}
