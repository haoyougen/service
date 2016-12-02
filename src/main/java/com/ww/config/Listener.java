package com.ww.config;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

	@Value("${kafka.topic}")
	private String topic;

	@Value("${kafka.group}")
	private String group;

	@KafkaListener(topics = "linuxsogood-topic", group = "sync-group")
	public void listen(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {
			Object message = kafkaMessage.get();
			LOGGER.info(message.toString());
		}

	}
}
