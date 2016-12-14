package com.ww.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
@PropertySource(value = "file:${APP_HOME}/conf/${env}/kafka.properties")
public class KafkaConsumerConfig {
	@Value("${kafka.broker.address}")
	private String brokerAddress;

	@Value("${kafka.messageKey}")
	private String messageKey;

	@Value("${kafka.zookeeper.connect}")
	private String zookeeperConnect;

	@Value("${kafka.group}")
	private String group;
	@Value("${kafka.topic}")
	private String topics;

	@Bean(name = "kafkaListenerContainerFactory")
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> KafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		// will create 3 KafkaMessageListenerContainer
		// 6 TopicPartition s are provided and the concurrency is 3; each
		// container will get 2 partitions.
		factory.setConcurrency(3);
		factory.getContainerProperties().setPollTimeout(10000);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());

	}

	// // 消费者容器配置信息
	// @Bean(name = "containerProperties")
	// public ContainerProperties getContainerProperties() {
	// ContainerProperties cp = new ContainerProperties(topics);
	// cp.setMessageListener(listener());
	// return cp;
	// }

	// // 创建kafkatemplate bean，使用的时候，只需要注入这个bean，即可使用template的send消息方法
	// @Bean(name = "kafkaMessageListenerContainer")
	// public KafkaMessageListenerContainer kafkaMessageListenerContainer() {
	// KafkaMessageListenerContainer kc = new
	// KafkaMessageListenerContainer(consumerFactory(),
	// getContainerProperties());
	// return kc;
	//
	// }

	@Bean
	public Listener listener() {
		return new Listener();
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> propsMap = new HashMap<>();
		propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, 0);
		propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
		propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
		propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, group);
		propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return propsMap;
	}

}
