package com.ww.service;

import com.ww.vo.AccessLogVO;

public interface AccessTraceService {
	void start(AccessLogVO logvo);

	void end();

	void param(String param);

	void exception(Throwable ex);

}
