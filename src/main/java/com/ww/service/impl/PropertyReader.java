/**
 * 
 */
package com.ww.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * @author wangshuguang
 *
 */

@ConfigurationProperties(prefix = "service")
@PropertySources({ @PropertySource(value = "file:${APP_HOME}/conf/${env}/test.properties") })
@Component
public class PropertyReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);
	private Map<String, String> count = new HashMap<>();

	public String brows() {
		Iterator<Entry<String, String>> it = count.entrySet().iterator();
		StringBuilder sb = new StringBuilder();
		sb.append("start");
		sb.append('\n');
		sb.append('\t');
		for (; it.hasNext();) {
			Entry<String,String> entry = it.next();
			sb.append(entry.getKey() + "--" + entry.getValue());
			LOGGER.info("{} -- {}", entry.getKey(), entry.getValue());

		}
		sb.append('\n');
		sb.append("over");
		return sb.toString();
	}

	/**
	 * @return the count
	 */
	public Map<String, String> getCount() {
		return count;
	}
	
	
}
