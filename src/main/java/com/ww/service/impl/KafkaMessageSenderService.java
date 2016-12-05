package com.ww.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ww.service.MessageSenderService;
import com.ww.vo.BasicMessage;

@Service
public class KafkaMessageSenderService implements MessageSenderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageSenderService.class);
	@Autowired
    private KafkaTemplate kafkaTemplate;
	@Override
	public void send(BasicMessage message) {
		kafkaTemplate.send(message);
		LOGGER.info("KafkaMessageSenderService ==>send");

	}
}
