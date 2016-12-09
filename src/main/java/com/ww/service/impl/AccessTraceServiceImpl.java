package com.ww.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ww.service.AccessTraceService;
import com.ww.utils.DateTimeUtil;
import com.ww.vo.AccessLogVO;

@Service
public class AccessTraceServiceImpl implements AccessTraceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccessTraceServiceImpl.class);

	/**
	 * the context
	 */
	private final ThreadLocal<AccessLogVO> context = new ThreadLocal<AccessLogVO>();
    @Override
	public void start(AccessLogVO logvo) {
		try {
			logvo.setStartTime(DateTimeUtil.rightNow());
			// put the log to context
			this.context.set(logvo);

		} catch (Exception e) {
			LOGGER.error("insert log error.", e);
		}
	}
    @Override
	public void end() {
		try {
			AccessLogVO logvo = context.get();
			logvo.setEndTime(DateTimeUtil.rightNow());
			logvo.setDuration(
					String.valueOf((Long.parseLong(logvo.getEndTime()) - Long.parseLong(logvo.getStartTime())) / 1000));
			this.context.remove();
			
		} catch (Exception e) {
			LOGGER.error("insert log error.", e);
		}
	}
    @Override
	public void exception(Throwable ex) {
		try {
			AccessLogVO logvo = context.get();
			logvo.setEndTime(DateTimeUtil.rightNow());
			logvo.setDuration(
					String.valueOf((Long.parseLong(logvo.getEndTime()) - Long.parseLong(logvo.getStartTime())) / 1000));

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream pout = new PrintStream(out);
			ex.printStackTrace(pout);
			String ret = new String(out.toByteArray());
			pout.close();
			logvo.setException(ret);
			try {
				out.close();
			} catch (Exception e) {

			}

			this.context.remove();
		} catch (Exception e) {
			LOGGER.error("insert log error.", e);
		}
	}

	@Override
	public void param(String param) {
		AccessLogVO logvo = context.get();
		logvo.setParam(param);

	}

}
