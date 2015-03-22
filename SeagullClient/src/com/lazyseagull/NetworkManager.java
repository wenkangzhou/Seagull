package com.lazyseagull;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * 网络管理类
 * @author cxk
 *
 */
public class NetworkManager {
	/**
	 * 判断当前网络是否是WIFI网络
	 * @param context
	 * @return
	 */
	public static boolean isWIFI(Context context){
		String netnameString = NetworkManager.GetCurrentNetworkName(context);
            if ( netnameString != null )     
    		{ 
            	if(netnameString.equalsIgnoreCase("wifi"))
            	{
            		return true;
            	}
    		}
    	return false;
	}
	/**
	 * 获取当前网络连接名称
	 * @param context
	 * @return
	 */
	public static String GetCurrentNetworkName(Context context){
		//判断当前网络类型,用户可以选择下载方式（立即下载，wifi下载）
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
    	if (connectivity != null)  {
            NetworkInfo activeNetInfo = connectivity.getActiveNetworkInfo();
            if ( activeNetInfo != null )     
    		{ 
            	return activeNetInfo.getTypeName();
    		}
    	}
    	return null;
	}
	
	/***
	 * 提示打开网络设置
	 */
	public static void showNetworkSetting(final Context context){
        //提示对话框
        AlertDialog.Builder builder=new Builder(context);
        builder.setTitle("网络设置提示").setMessage("亲！您的网络没有打开哦?").setPositiveButton("设置", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent=null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本 
                if(android.os.Build.VERSION.SDK_INT > 10 ){
                    
                	intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
               } else {
            	   intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
               }
                
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
}
