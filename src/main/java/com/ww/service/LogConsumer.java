package com.ww.service;

import com.ww.vo.AccessLogVO;

public interface LogConsumer {
	public void consume(AccessLogVO vo);
}
