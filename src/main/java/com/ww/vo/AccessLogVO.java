package com.ww.vo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_TASK_INFO)  
public class AccessLogVO {
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)    
	private String from;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)    
	private String path;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)    
	private String param;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)    
	private String accessTime;

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

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

}
