package com.lazyseagull;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CacheManager;

public class MarketUtils {

	private static DisplayMetrics displayMetrics = new DisplayMetrics();

	private static Config config = null;

	public static int getScreenWidth(Activity activity) {
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	public static int getScreenHeight(Activity activity) {
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

	public static float getScreenDensity(Activity activity) {
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return displayMetrics.density;
	}

	public static boolean checkSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}

	}

	public static String getPhoneNumber(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}


	public static String getUMENG_CHANNEL(Context context) {
		ApplicationInfo ai = null;
		try {
			ai = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bundle bundle = ai.metaData;
		return bundle.getString("UMENG_CHANNEL");
	}

	public static String getUSER_PHONE(Context context) {
		ApplicationInfo ai = null;
		try {
			ai = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bundle bundle = ai.metaData;
		return bundle.get("USER_PHONE").toString();
	}

	public static String newRandomUUID() {
		String uuidRaw = UUID.randomUUID().toString();
		return uuidRaw.replaceAll("-", "");
	}


	public static String getImeiName(Context context) {
		
		if(!"".equals(Config.getInstance(context).getImei())){
			
			return Config.getInstance(context).getImei();
		}
		
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		if (imei == null || imei.trim().length() == 0 || imei.matches("0+")) {// ƽ��

			imei = DeviceInfo.getInstance(context).getLocalMacAddress();
		}

		return imei;
	}


	public static String getNum(String number) {
		if (number.length() > 11)
			number = number.substring(number.length() - 11, number.length());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < number.length(); i++) {
			char c = (char) (((int) number.charAt(i)) + 'A');
			sb.append(c);
		}
		return sb.toString();
	}

	public static long getFileSizes(File f) throws Exception {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
		}
		return s;
	}


	public static long getFileSize(File f) throws Exception
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}


	public static Address getAddress(Context context) {

		Address address = null;

		LocationManager lm = (LocationManager) context
				.getSystemService(context.LOCATION_SERVICE);
		List<String> lp = lm.getAllProviders();

		Criteria criteria = new Criteria();
		criteria.setCostAllowed(false);
		criteria.setAccuracy(Criteria.ACCURACY_COARSE); 
		String providerName = lm.getBestProvider(criteria, true);

		if (providerName != null) {
			Location location = lm.getLastKnownLocation(providerName);
			Log.i("zhou", "-------" + location);

			List<Address> addressList = getAddressbyGeoPoint(context, location);
			if (addressList != null && addressList.size() > 0) {

				address = addressList.get(0);
			}
		}

		return address;
	}

	private static List<Address> getAddressbyGeoPoint(Context context,
			Location location) {
		List<Address> result = null;
		try {
			if (location != null) {
				Geocoder gc = new Geocoder(context, Locale.getDefault());
				result = gc.getFromLocation(location.getLatitude(),
						location.getLongitude(), 1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}


	public static boolean checkNetwork(Context context) {
		try {
			ConnectivityManager cwjManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo network = cwjManager.getActiveNetworkInfo();
			if (network == null)
				return false;
			return network.isAvailable();
		} catch (Exception ex) {
			return false;
		}
	}


	public static String getConnectState2(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		String typeName = null;
		if (info != null) {
			typeName = info.getTypeName().toLowerCase(); // WIFI/MOBILE
			return typeName;
		} else {
			typeName = "none";
			return typeName;
		}
	}



	public static boolean isServiceRunning(Context mContext, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}

	public static void cleanWebview(Context context){
	
		File file = CacheManager.getCacheFileBaseDir();
		if(file != null && file.exists() && file.isDirectory()){
			
			for(File item : file.listFiles()){
				
				item.delete();
			}
			file.delete();
		}
		context.deleteDatabase("webview.db");
		context.deleteDatabase("webviewCache.db");
	}
}
