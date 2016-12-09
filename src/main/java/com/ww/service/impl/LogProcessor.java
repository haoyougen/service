package com.ww.service.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ww.service.LogConsumer;
import com.ww.vo.AccessLogVO;

@Component
public class LogProcessor {
	@Autowired
	private LogConsumer logConsumer;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogProcessor.class);
	private final BlockingQueue<AccessLogVO> queue = new LinkedBlockingQueue<AccessLogVO>();
	private volatile AtomicBoolean runFlag = new AtomicBoolean(false);
	private int noOfThread = 1;

	public void saveLog(AccessLogVO logvo) {
		if (logvo == null) {
			return;
		}
		queue.add(logvo);
	}

	public void start() {
		runFlag.set(true);
		while (runFlag.get()) {
			for (int i = 0; i < noOfThread; i++) {
				final Thread t = new Thread("LogProcessor " + i) {
					public void run() {
						try {
							AccessLogVO log = LogProcessor.this.queue.poll(1, TimeUnit.SECONDS);
							logConsumer.consume(log);
						} catch (Exception e) {
							LOGGER.info("error during processing access log");
						}
					}
				};
				t.setDaemon(true);
				t.start();
			}
		}
	}

}
