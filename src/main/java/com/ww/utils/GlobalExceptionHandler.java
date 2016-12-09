package com.ww.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ww.service.AccessTraceService;
import com.ww.vo.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";
	@Autowired
	private AccessTraceService logtrace;

//	@ExceptionHandler(value = Exception.class)
//	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", e);
//		mav.addObject("url", req.getRequestURL());
//		mav.setViewName(DEFAULT_ERROR_VIEW);
//		return mav;
//	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorInfo jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ErrorInfo r = new ErrorInfo();
		r.setMessage(e.getMessage());
		r.setCode("0");
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());

		return r;
	}
}
