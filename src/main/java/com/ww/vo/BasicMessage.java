package com.ww.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class BasicMessage implements Message<String> {
	private String body;

	public BasicMessage(String body) {
		this.body = body;
	}

	@Override
	public MessageHeaders getHeaders() {
		Map<String, Object> headmap = new HashMap<String, Object>();
		headmap.put("h1", "v1");
		MessageHeaders mh = new MessageHeaders(headmap);

		return mh;
	}

	@Override
	public String getPayload() {
		return  body;

	}

}
