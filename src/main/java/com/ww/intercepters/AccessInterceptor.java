package com.ww.intercepters;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ww.service.AccessTraceService;
import com.ww.vo.AccessLogVO;

/**
 * 
 * Collection all information for request accessing.
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccessInterceptor.class);
	@Autowired
	private AccessTraceService tracer;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		LOGGER.info("----------------------preHandle----------------------");
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();

		Map map = request.getParameterMap();

		AccessLogVO vo = new AccessLogVO();
		vo.setPath(url);
		vo.setAction(method);
		vo.setParam(queryString);
		tracer.start(vo);
		tracer.param(queryString);
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex)
			throws Exception {
		// tracer.exception(ex);
		LOGGER.info("----------------------afterCompletion----------------------");
		tracer.end();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("----------------------postHandle----------------------");
	}

}
