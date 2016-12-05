package com.ww.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import com.ww.service.MessageReceiverService;

@Service
public class KafkaMessageConsumerService implements MessageListener<String, String>, MessageReceiverService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageConsumerService.class);

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		LOGGER.info("----------------------------------get message-------------------------");
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		LOGGER.info("----------------------------------get message 2-------------------------");
	}

}
