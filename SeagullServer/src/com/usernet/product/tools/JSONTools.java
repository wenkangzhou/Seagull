package com.usernet.product.tools;

import org.apache.commons.lang.StringUtils;

public class JSONTools {

	public static StringBuffer string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				// sb.append("\\r");
				sb.append("");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb;
	}
	
	/***
	 * 替换字符串中所有空格和制表符
	 * @param msg
	 * @return
	 */
	public static String replace(String msg){
		
		if(StringUtils.isNotEmpty(msg)){
			
			String temp = msg.replaceAll("\\s*", "");
			return temp.substring(0,temp.length() > 200 ? 200 : temp.length());
		}else{
			
			return "";
		}
	}
}
