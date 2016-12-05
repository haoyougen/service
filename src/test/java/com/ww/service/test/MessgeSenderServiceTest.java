package com.ww.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ww.service.MessageSenderService;
import com.ww.vo.BasicMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application.properties", "file:${APP_HOME}/conf/${env}/kafka.properties" })
@ContextConfiguration("classpath:services.xml")

public class MessgeSenderServiceTest {
	@Autowired
	private KafkaTemplate<String, String> template;

	@Test
	public void test() {
		// BasicMessage message = new BasicMessage("hahahahah");
		template.send("mytopic", "dddddddd");
		System.out.println("done");
	}
}
