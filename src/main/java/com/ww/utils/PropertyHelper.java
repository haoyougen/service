package com.ww.utils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PropertyHelper {
	public static <T> T mapToBean(Map<String, Object> map, Class<T> obj) {
		if (map == null) {
			return null;
		}
		T t = null;
		try {
			Set<Entry<String, Object>> sets = map.entrySet();
			t = obj.newInstance();
			Method[] methods = obj.getDeclaredMethods();
			for (Entry<String, Object> entry : sets) {
				String str = entry.getKey();
				String setMethod = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
				for (Method method : methods) {
					if (method.getName().equals(setMethod)) {
						method.invoke(t, entry.getValue());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
