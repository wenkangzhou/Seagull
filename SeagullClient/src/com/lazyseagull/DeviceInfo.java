package com.lazyseagull;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class DeviceInfo {

	private static Context context;

	private static Config config;

	private static DeviceInfo deviceInfo;

	private static PackageInfo packageInfo = null;

	private static String macAddress = null;

	public DeviceInfo(Context context) {

		this.context = context;
		this.config = new Config(context);
	}


	public int getClientVersionCode() {

		return packageInfo.versionCode;
	}


	public String getClientVersionName() {

		return packageInfo.versionName;
	}


	public static DeviceInfo getInstance(Context context) {

		if (deviceInfo == null) {

			deviceInfo = new DeviceInfo(context);
			
			if (packageInfo == null) {

				try {
					packageInfo = context.getPackageManager().getPackageInfo(
							context.getPackageName(), 0);
				} catch (NameNotFoundException e) {
				}
			}

			return deviceInfo;
		} else {

			if (packageInfo == null) {

				try {
					packageInfo = context.getPackageManager().getPackageInfo(
							context.getPackageName(), 0);
				} catch (NameNotFoundException e) {
				}
			}
			return deviceInfo;
		}
	}
	/**
	 * 获取mac地址
	 */
	public static String getLocalMacAddress() {

		if (config.getMACAddress() == null) {

			WifiManager wifiMgr = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifiMgr.getConnectionInfo();
			if (null != info) {

				Log.i("tang", "info : " + info);
				macAddress = info.getMacAddress();

			}
			if (macAddress == null && !wifiMgr.isWifiEnabled()) {

				try {
					Log.i("tang", "open wifi");
					wifiMgr.setWifiEnabled(true);
					for (int i = 0; i < 10; i++) {

						WifiInfo _info = wifiMgr.getConnectionInfo();
						macAddress = _info.getMacAddress();

						Log.i("tang", "获取MAC地址 " + i + " :" + macAddress);

						if (macAddress != null)
							break;
						try {

							Thread.sleep(500);
						} catch (InterruptedException e) {

							Log.e("tang", "获取MAC地址睡眠出错");
						}
					}
					Log.i("tang", "close wifi");
					wifiMgr.setWifiEnabled(false);

					config.putMACAddress(macAddress);

				} catch (Exception e) {

					Log.e("tang", "open wifi get mac error");
				}
			}
		} else {

			Log.i("tang", "get mac by config");
			macAddress = config.getMACAddress();
		}

		Log.i("tang", "mac :" + macAddress);
		return macAddress;
	}

}
