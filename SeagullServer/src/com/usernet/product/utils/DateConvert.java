package com.usernet.product.utils;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.Converter;

/**
 * 
 * 
 * @author
 * 
 */

public class DateConvert implements Converter {
	/**
	 */
	private static SimpleDateFormat df = new SimpleDateFormat();

	/**
	 */
	private static Set<String> patterns = new HashSet<String>();
	static {
		DateConvert.patterns.add("yyyy-MM-dd");
		DateConvert.patterns.add("yyyy-MM-dd HH:mm");
		DateConvert.patterns.add("yyyy-MM-dd HH:mm:ss");
		DateConvert.patterns.add("yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 
	 * @param type
	 *            Class
	 * @param value
	 *            Object return Date Object.
	 */
	@SuppressWarnings({ "rawtypes" })
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		} else if (value instanceof String) {
			Object dateObj = null;
			Iterator it = patterns.iterator();
			while (it.hasNext()) {
				try {
					String pattern = (String) it.next();
					df.applyPattern(pattern);
					dateObj = df.parse((String) value);
					break;
				} catch (Exception ex) {
					// do iterator continue
				}
			}
			return dateObj;
		} else {
			return null;
		}
	}

}
