package com.usernet.product.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opensymphony.oscache.util.StringUtil;

/**
 * 日期工具类
 */
public class DateUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat yearMonthSdf = new SimpleDateFormat("yyyy-MM");
	
	private static SimpleDateFormat nyrsfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//获得上个月的月份日期  
	@SuppressWarnings("deprecation")
	public static String getPreMonth(){
		
		Date today = new Date();
		if (!(today.getDate() >= 15)) {

			today.setMonth(today.getMonth() - 1);
		}
		
		return yearMonthSdf.format(today);
	}
	
	/***
	 * 得到从1月到上月的集合
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String> getOneToPreMonth(){
		
		Date date = new Date();
		List<String> monthList = new ArrayList<String>();
		int currentMonth = date.getMonth();
		
		for(int i = currentMonth;i >= 0 ;i--){
			
			date.setMonth(i);
			monthList.add(yearMonthSdf.format(date));
		}
		
		return monthList;
	}
	
	/***
	 * 得到从1月到当月的集合
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String> getOneToCurrentMonth(){
		
		Date date = new Date();
		List<String> monthList = new ArrayList<String>();
		int currentMonth = date.getMonth() + 1;
		
		for(int i = currentMonth - 1;i >= 0 ;i--){
			
			date.setMonth(i);
			monthList.add(yearMonthSdf.format(date));
		}
		
		return monthList;
	}
	
	public static Timestamp stringToTimestampBegin(String time) {
		if (time == null || time.trim().equals(""))
			return null;
		return Timestamp.valueOf(time + " 00:00:00");
	}

	public static Timestamp stringToTimestampEnd(String time) {
		if (time == null || time.trim().equals(""))
			return null;
		return Timestamp.valueOf(time + " 59:59:59");
	}
	public static Timestamp stringToTimestamp(String time){
		if (time == null || time.trim().equals(""))
			return null;
		return Timestamp.valueOf(time + " 23:59:59");
	}
	
	public static Date stringToDate(String date,String format){
		if(!StringUtil.isEmpty(format)){
			sdf = new SimpleDateFormat(format);
		}
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取当月的1号  日期
	public static String getCurrentMonthOne(){
		
		Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
		return sdf.format(cal.getTime());
	}
	
	public static String getDate() {
		
		return sdf.format(new Date());
	}
	
	/** 获取昨天的日期  没有时分秒 **/
	public static String getYestaday(){
		
		Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
		return sdf.format(cal.getTime());
	}
	
	/** 获取几天前的日期  没有时分秒 **/
	public static String getBeDay(Integer day){
		Calendar cal=Calendar.getInstance();
		
        cal.add(Calendar.DATE,-1*day);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 今日星期几
	 * @return
	 */
	public static String getWeekDayString()
    {
        String weekString = "";
        final String dayNames[] = {"7","1","2","3","4","5","6"};
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekString = dayNames[dayOfWeek - 1];
        return weekString;
    }
	
	
	/***
	 * 获得已今日为结束日期的 30天日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String> getCurrentMonthAndPreMonth(){
		
		Date tody = new Date();
		List<String> dayList = new ArrayList<String>();
		dayList.add(sdf.format(tody));
		for(int i = 0;i<30;i++){
			
			if(tody.getDate() - 1 == 0){
				
				tody.setMonth(tody.getMonth());
			}
			
			tody.setDate(tody.getDate() - 1);
			dayList.add(sdf.format(tody));
		}
		
		return dayList;
	}
	
	/****
	 * 获取今日时间
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getToday(Date date){
		Date today = new Date();
		today.setHours(23);
		today.setMinutes(59);
		return nyrsfm.format(today);
	}
	/**
	  * 获得当前日期时间
	  * @param type:DT=>'yyyy-MM-dd HH:mm:ss' ; D=>'yyyy-MM-dd'
	  * @return
	  */
	  public static String getDateFomat(String type){
	         Calendar cal=Calendar.getInstance();//-0代表0天前这个时候的时间
	         cal.add(Calendar.DAY_OF_YEAR,-0);
	         String nowtime="";
	         if (type.equals("DT")){
	          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          nowtime=sdf.format(cal.getTime());
	         }else if (type.equals("D")){
	          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          nowtime=sdf.format(cal.getTime());
	         }
	         return nowtime;
	     } 
	  
	/***
	 * 获取传进来的时间 的前一天时间 
	 * @param date
	 * @param flag 如果为true 则返回 Data类型
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYesterDay(Date date,int day){
		
		Date temp = date;
		temp.setDate(date.getDate() - day);
		temp.setHours(23);
		temp.setMinutes(59);
		
		return nyrsfm.format(temp);
	}
	
	/***
	 * 处理日期去除年份
	 * @param date
	 * @return
	 */
	public static String subDate(String date){
		
		return date.substring(date.indexOf("-") + 1,date.length());
	}

	public static String format(Date date) {
		return sdf.format(date);
	}

	public static String format(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static List<String[]> getTimePeriod(String start, String end) {
		List<String[]> pers = new ArrayList<String[]>();
		Timestamp etime = stringToTimestampBegin(end);
		//if (etime.after(new Timestamp(System.currentTimeMillis())))
		//	return null;
		Timestamp stime = stringToTimestampBegin(start);
		Calendar s = Calendar.getInstance();
		s.setTime(stime);
		boolean isExit = false;
		while (!isExit) {
			String[] temp = new String[2];
			s.set(Calendar.DAY_OF_MONTH, 1);
			if (s.getTime().before(stime))
				s.setTime(stime);
			temp[0] = sdf.format(s.getTime());
			s.add(Calendar.MONTH, 1);
			s.set(Calendar.DAY_OF_MONTH, 1);
			s.add(Calendar.DAY_OF_MONTH, -1);
			if (s.getTime().after(etime)) {
				s.setTime(etime);
				isExit = true;
			}
			temp[1] = sdf.format(s.getTime());
			s.add(Calendar.DAY_OF_MONTH, 1);
			pers.add(temp);
		}
		return pers;
	}
}
