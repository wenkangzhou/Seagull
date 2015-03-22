package com.usernet.product.tools;

import java.io.InputStream;
import java.util.Properties;

public class Property {

	/**
	 * 读取properties配置信息
	 * 
	 * @param fileName
	 *            文件名
	 * @param pro
	 *            KEY值
	 * @return VALUE值
	 */
	public static String getPara(String fileName, String pro) {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = Property.class.getClassLoader().getResourceAsStream(fileName + ".properties");
			prop.load(is);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return prop.getProperty(pro);
	}
	
	public static void main(String[] args) {
		String s = Property.getPara("resources", "advertImpage");
		System.out.println(s);
	}
}
