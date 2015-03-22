package com.usernet.product.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class StringUtils {
	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		
		readUrl("http://fanli.beekee.cn/api/index.php/App?page=1");
	}

	public static void readUrl(String str) {
		URL url;
		try {
			url = new URL(str);

			InputStream inputStream = url.openStream();
			readStream(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readStream(InputStream inputStream) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(
				inputStream, "gbk"));
		String line = "";
		String temp;

		while ((temp = read.readLine()) != null) {
			
			line += temp;
		}
		System.out.println(line);
		inputStream.close();
	}

	public static String arrayToString(String[] array,String sp){
		String result = "";
		for(String s:array){
			if(result.equals("")){
				result = result+s;
			}else{
				result = result+sp+s;
			}
		}
		return result;
	}
	public static String arrayToString(Integer[] array,String sp){
		String result = "";
		for(Integer s:array){
			if(result.equals("")){
				result = result+s;
			}else{
				result = result+sp+s;
			}
		}
		return result;
	}
}
