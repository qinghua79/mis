package com.framework.core.util;

import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

public class BeanUtils {
	public static Map toMap(Object src) {
		return BeanMap.create(src);
	}
}
