package com.unbank.duplicate.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class publicTools {
	private ArrayList valueList = new ArrayList();

	public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;

	public publicTools() {
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static Date getdate(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}

	public static int getWeekNo(String date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getdate(date));
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}

	public static String getWeekStr(String date) throws Exception {
		String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return dayNames[getWeekNo(date) - 1];
	}


	// 取得2个日期之间有几天
	public static int getdatecount(String sdate1, String sdate2) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = df.parse(sdate1);
			java.util.Calendar c1 = new java.util.GregorianCalendar();
			c1.setTime(date1);

			Date date2 = df.parse(sdate2);
			java.util.Calendar c2 = new java.util.GregorianCalendar();
			c2.setTime(date2);

			return Integer.parseInt(Long.toString((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60 * 60 * 24))) + 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 取得2个日期之间差几天
	public static int getBetweencount(String sdate1, String sdate2) {
		try {
			// System.out.println(sdate1 + "-------" + sdate2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = df.parse(sdate1);
			java.util.Calendar c1 = new java.util.GregorianCalendar();
			c1.setTime(date1);

			Date date2 = df.parse(sdate2);
			java.util.Calendar c2 = new java.util.GregorianCalendar();
			c2.setTime(date2);

			return Integer.parseInt(Long.toString((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60 * 60 * 24)));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 取得传入yyyy-mm-dd日期和向后几天�?�向前几天，返回�?个日期集合，包含当天
	public static String[] getdates(String sdate, int days) {

		String flag = "next";
		if (days < 0)
			flag = "pre";
		days = Math.abs(days);
		String[] str = new String[days];
		String strtemp = "";
		for (int i = 1; i < days; i++) {
			if (i == 1)
				strtemp = sdate;
			str[i] = getdate(strtemp, flag);
			strtemp = str[i];
		}
		str[0] = sdate;
		String[] retStr = new String[days];
		for (int c = 0; c < str.length; c++) {
			retStr[retStr.length - 1 - c] = str[c];
		}
		return retStr;
	}

	// 取得传入yyyy-mm-dd日期和向后几天�?�向前几天，返回�?个日期集�?,不包含当�?
	public static String[] getdatesNoNow(String sdate, int days) {

		String flag = "next";
		if (days < 0)
			flag = "pre";
		days = Math.abs(days);
		String[] str = new String[days];
		String strtemp = "";
		for (int i = 0; i < days; i++) {

			if (i == 0)
				strtemp = sdate;
			str[i] = getdate(strtemp, flag);
			strtemp = str[i];
		}
		return str;
	}

	// sdate:日期yyyy-mm-dd;i:next-向后�? pre-向前�?
	public static String getdate(String sdate, String sflag) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date newdate = df.parse(sdate);
			java.util.Calendar cal = new java.util.GregorianCalendar();
			cal.setTime(newdate);
			if (sflag.equals("next"))
				cal.add(Calendar.DATE, 1);
			else
				cal.add(Calendar.DATE, -1);
			Date dt = cal.getTime();
			return df.format(dt);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// 取得传入yyyy-mm-dd日期和前、后标志向后几天、向前几天，返回该天日期
	public static String getOneDay(String sdate, int dnum) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date newdate = df.parse(sdate);
			java.util.Calendar cal = new java.util.GregorianCalendar();
			cal.setTime(newdate);
			if (dnum > 0)
				cal.add(Calendar.DATE, dnum);
			else
				cal.add(Calendar.DATE, dnum);
			Date dt = cal.getTime();
			return df.format(dt);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// 取得两日期间差几小时
	public static int getHC(String sdate1, String sdate2) {
		try {
			System.out.println(sdate1 + "=====" + sdate2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = df.parse(sdate1);
			java.util.Calendar c1 = new java.util.GregorianCalendar();
			c1.setTime(date1);

			Date date2 = df.parse(sdate2);
			java.util.Calendar c2 = new java.util.GregorianCalendar();
			c2.setTime(date2);

			return Integer.parseInt(Long.toString((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60 * 60)));

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static int getHCM(String sdate1, String sdate2) {
		try {
			System.out.println(sdate1 + "|" + sdate2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = df.parse(sdate1);
			java.util.Calendar c1 = new java.util.GregorianCalendar();
			c1.setTime(date1);

			Date date2 = df.parse(sdate2);
			java.util.Calendar c2 = new java.util.GregorianCalendar();
			c2.setTime(date2);

			return Integer.parseInt(Long.toString((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60)));

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	//
	public static String getAddHC(String sdate1, int h) {
		int rth = 0;
		try {
			System.out.println(sdate1);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = df.parse(sdate1);
			java.util.Calendar c1 = new java.util.GregorianCalendar();
			c1.setTime(date1);

			c1.add(Calendar.HOUR, h);
			Date dt = c1.getTime();
			return df.format(date1);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static String getMIN(String sdate1, int m) {
		try {
			System.out.println(sdate1);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = df.parse(sdate1);
			java.util.Calendar c1 = new java.util.GregorianCalendar();

			c1.setTime(date1);
			c1.setTimeInMillis(c1.getTimeInMillis() + (m * 1000));
			Date dt = c1.getTime();

			return df.format(dt);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static int getInt(String istr) {
		int i = 0;
		try {
			i = Integer.parseInt(istr);
		} catch (NumberFormatException e) {
			i = 0;
			return i;
		}

		return i;
	}

	public static String getFoLDate(String gt) {
		String i = "";
		try {
			Date date = new Date(); // now
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			// cal.add(Calendar.MONTH,-1); //前个
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if (gt.equals("f")) {
				String ym = df.format(date);
				ym = ym.substring(0, 8);
				return ym + Integer.toString(cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			}
			if (gt.equals("l")) {
				String ym = df.format(date);
				ym = ym.substring(0, 8);
				return ym + Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
		} catch (Exception e) {
			return i;
		}

		return i;
	}

	public static String getFoLDate(String datestr, String gt) {
		String i = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			Date date = df.parse(datestr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			// cal.add(Calendar.MONTH,-1); //前个

			if (gt.equals("f")) {
				String ym = df.format(date);
				ym = ym.substring(0, 8);
				return ym + Integer.toString(cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			}
			if (gt.equals("l")) {
				String ym = df.format(date);
				ym = ym.substring(0, 8);
				return ym + Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
		} catch (Exception e) {
			return i;
		}

		return i;
	}

	public static String GetToday() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		return df.format(dt);
	}

	public static String GetTime() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

		return df.format(dt);
	}

	public static String GetNow() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(dt);
	}

	public static String GetNowSS() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");

		return df.format(dt);
	}
	
	public static String GetNow2() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd");

		return df.format(dt);
	}

	public static String SubStringNoPoint(String str, int bytes) {

		int count = 0; // 统计字节
		String reStr = ""; // 返回字符
		if (str == null) {
			return reStr;
		}
		char[] tempChar = str.toCharArray();
		for (int i = 0; i < tempChar.length; i++) {
			String s1 = str.valueOf(tempChar[i]);
			byte[] b = s1.getBytes();
			count += b.length;
			if (count < bytes) {
				reStr += tempChar[i];
			} else if (count == bytes) {
				// reStr = reStr.substring(0,i-1) + "...";
				break;
			} else {
				reStr = reStr.substring(0, i - 1);
				break;
			}
		}
		return reStr;
	}

	public static Date getMondayOfWeek(Date date) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return monday.getTime();
	}

	/**
	 * 根据日期取得对应周周日日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar sunday = Calendar.getInstance();
		sunday.setTime(date);
		sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sunday.getTime();
	}

	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public static String formatDate(Date date, String pattern) {
		String strDate = null;
		try {
			if (pattern == null) {
				pattern = "yyyy-MM-dd";
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			strDate = format.format(date);
		} catch (Exception e) {

		}
		return strDate;
	}

	public static String subStringNew(String str, int bytes) {
		int count = 0; 
		String reStr = ""; 
		if (str == null) {
			return reStr;
		}
		char[] tempChar = str.toCharArray();

		for (int i = 0; i < tempChar.length; i++) {
			String s1 = str.valueOf(tempChar[i]);
			byte[] b = s1.getBytes();
			count += b.length;
			if (count <= bytes) {
				reStr += tempChar[i];
			} else {
				reStr = reStr.substring(0, i) + "...";
				break;
			}
		}
		return reStr;
	}

	public static String substring(String str, int tocount, String more) {
		int reint = 0;
		String restr = "";
		if (str == null)
			return "";
		char[] tempchar = str.toCharArray();
		for (int kk = 0; (kk < tempchar.length && tocount > reint); kk++) {System.out.println("----"+reint);
			String s1 = str.valueOf(tempchar[kk]);
			byte[] b = s1.getBytes();
			reint += b.length;System.out.println(b.length+"----"+s1);
			restr += tempchar[kk];
		}
		if (tocount == reint || (tocount == reint - 1))
			restr += more;
		return restr;
	}
	public static int compareDate(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				//System.out.println("dt1 在dt2后面");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//System.out.println("dt1在dt2前面");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	public static int compareDateTime(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				//System.out.println(DATE1+" 在 "+DATE2+"后面");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//System.out.println(DATE1+" 在 "+DATE2+" 前面");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	public static String GetNowMinite() {
	    Date dt = new Date();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	    return df.format(dt);
	  }
	

	 
	/***
	     * 取得起止日期间的天数集合
	     * @param startDate
	     * @param endDate
	     * @return
	     */
	public static List<String> twodatebetween(String startDate, String endDate){
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		List<String> al = new ArrayList<String>();
		if(startDate.equals(endDate)){
			//IF起始日期等于截止日期,仅返回起始日期一天
			al.add(startDate);
		}else if(startDate.compareTo(endDate) < 0){
			//IF起始日期早于截止日期,返回起止日期的每一天
	        while(startDate.compareTo(endDate) < 0){ 
	        	al.add(startDate);
	        	try {
	        		Long l = format.parse(startDate).getTime();
	        		startDate = format.format( l + 3600*24*1000);//+1天
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
	       }
	        al.add(endDate);
		}else{
			//IF起始日期晚于截止日期,仅返回起始日期一天
			al.add(startDate);
		}
			return al;
	}
	public static void loggerException(Logger logger,Exception e){
		logger.error("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		logger.error(e.toString());
    	StackTraceElement[] este=e.getStackTrace();
    	for(int i=0;i<este.length;i++){
    		logger.error(este[i]);
    	}
	}
	public static void main(String[] args){
//		List<String> list=twodatebetween("2014-01-01", "2014-02-04");
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i));
//		}
		
//		HashMap<String,Integer> statisticssum=new HashMap<String,Integer>();
		System.out.println(GetNow2());
	}
	
	
}
