package com.lazyseagull;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class Config {
	
	public static final String NAME = "com.lazyseagull_imei";
	public static final String BOTTOM_MENU = "com.lazyseagull_mac";
	
	private Context mContext;

	private static Config config = null;

	public Config(Context context) {
		mContext = context;
	}

	public static Config getInstance(Context context) {

		if (config == null) {

			config = new Config(context);
		}
		return config;
	}
	/**
	 * 手机唯一识别码
	 */
	public void setImei(String imei) {
		SharedPreferences settings = mContext.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);
		settings.edit().putString("imei_1", imei).commit();
	}

	public String getImei() {
		SharedPreferences settings = mContext.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);
		return settings.getString("imei_1", "");
	}
	// 获取MAC地址
	public String getMACAddress() {

		SharedPreferences settings = mContext.getSharedPreferences(BOTTOM_MENU,
				Context.MODE_PRIVATE);
		return settings.getString("MACaddress", null);
	}

	// 保存MAC地址
	public void putMACAddress(String text) {

		SharedPreferences settings = mContext.getSharedPreferences(BOTTOM_MENU,
				Context.MODE_PRIVATE);
		Editor edit = settings.edit();
		edit.putString("MACaddress", text);
		edit.commit();
	}
	/**
	 * 获取退出时所显示的文字
	 */
	public String getExitText() {

		Log.i("zhou", "获取退出所显示的文字 ");
		SharedPreferences settings = mContext.getSharedPreferences(BOTTOM_MENU,
				Context.MODE_PRIVATE);
		return settings.getString("exitText", "确认要退出吗?");
	}
}
