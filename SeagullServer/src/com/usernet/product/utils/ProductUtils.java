package com.usernet.product.utils;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class ProductUtils {
	/*
	 * 加密
	 */
	public static String getMD5String(String args) {
		return Base64.encode(Md5Token.getInstance().getLongToken(
				Md5Token.getInstance().getLongToken(args) + ProductConfig.MD5_CODE), ProductConfig.CHARSET);
	}

	/**
	 * 获得汉字的首字母拼音
	 * 
	 * @param chinese 汉字
	 * @return
	 */
	public static String getFirstChars(String chinese) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < chinese.length(); i++) {
				// 对英文字母的处理：小写字母转换为大写，大写的直接返回
				char ch = chinese.charAt(i);
				if (ch >= 'a' && ch <= 'z')
					sb.append(ch);
				if (ch >= 'A' && ch <= 'Z')
					sb.append(ch);
				if (ch <= '9' && ch >= '0')
					sb.append(ch);
				// 对汉语的处理
				String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
				if (array == null || array.length == 0) {
					continue;
				}
				String s = array[0];
				sb.append(s.charAt(0));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得汉字的全拼
	 * 
	 * @param chinese 汉字
	 * @return
	 */
	public static String getFullChars(String chinese) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < chinese.length(); i++) {
				// 对英文字母的处理：小写字母转换为大写，大写的直接返回
				char ch = chinese.charAt(i);
				if (ch >= 'a' && ch <= 'z')
					sb.append(ch);
				if (ch >= 'A' && ch <= 'Z')
					sb.append(ch);
				if (ch <= '9' && ch >= '0')
					sb.append(ch);
				// 对汉语的处理

				String[] array = PinyinHelper.toHanyuPinyinStringArray(ch, format);
				if (array == null || array.length == 0) {
					continue;
				}
				String s = array[0];
				sb.append(s);
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void main(String[] args) {
		System.out.println(getMD5String("admin"));
	}
	/*
	 * 所有接口条用处理成功时返回错误信息，result=0|1 result：0-处理失败；1-处理成功;
	 */
	public static String getResJson(String desc) {
		StringBuffer buf = new StringBuffer();
		buf.append("{\"result\":\"1\",\"data\":");
		buf.append(desc);
		buf.append("}");
		return buf.toString();
	}
}
