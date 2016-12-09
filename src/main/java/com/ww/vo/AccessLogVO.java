package com.ww.vo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "myindex", type = "AccessLogVO")
public class AccessLogVO {
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String from;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String action;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String path;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String param;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String startTime;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String endTime;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String duration;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String sessionID;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String exception;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
