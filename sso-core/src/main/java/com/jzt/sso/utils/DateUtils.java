package com.jzt.sso.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

	/**
	 * 格式到分钟
	 */
	public final static String YY_UNTIL_MI = "yyyy-MM-dd HH:mm";

	/**
	 * 格式到天数
	 */
	public final static String YY_UNTIL_DD = "yyyy-MM-dd";

	private static Log log = LogFactory.getLog(DateUtils.class);

	/**
	 * 获取当天最后时间"2017:12:20 15:52:00" --> "2017:12:20 23:59:59"
	 * author lijuntao
	 * date 2017年12月20日
	 */
	public static Date getLastTime(Date date){
		if(date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 获取当天最早时间"2017:12:20 15:52:00" --> "2017:12:20 00:00:00"
	 * author lijuntao
	 * date 2017年12月20日
	 */
	public static Date getFirstTime(Date date){
		if(date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 解析日期，不抛出遗产
	 * author lijuntao
	 * date 2018年4月12日
	 */
	public static Date parse(String dateStr, String format){
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (Exception e) {
			log.debug(String.format("日期格式转化失败:%s,%s", dateStr, format));
		}
		return null;
	}

	/**
	 * 解析日期，不抛出遗产
	 * author lijuntao
	 * date 2018年4月12日
	 */
	public static Date parse(String dateStr, String... formats){
		if(CommonUtils.empty(formats)){
			log.debug(String.format("日期格式转化失败:%s,%s", dateStr, Arrays.toString(formats)));
			return null;
		}
		Date date = null;
		for(String format : formats){
			try {
				date = new SimpleDateFormat(format).parse(dateStr);
				if(date != null) {
					return date;
				}
			} catch (Exception e) {
			}
		}
		if(date == null){
			log.debug(String.format("日期格式转化失败:%s,%s", dateStr, Arrays.toString(formats)));
		}
		return null;
	}

	/**
	 * 解析日期，不抛出异常
	 * author lijuntao
	 * date 2018年4月12日
	 */
	public static String format(Date date, String format){
		if(date == null)
			return null;
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			log.debug(String.format("日期格式转化失败:%s,%s", date, format));
		}
		return null;
	}

	/**
	 * 校验日期，不抛出异常
	 * author lijuntao
	 * date 2018年4月12日
	 */
	public static boolean isCorrectFormat(String dateStr, String format){
		if(CommonUtils.empty(dateStr) && CommonUtils.empty(format))
			return false;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date date = dateFormat.parse(dateStr);
			return dateStr.equals(dateFormat.format(date));
		} catch (Exception e) {
			log.debug(String.format("校验日期格式异常:%s,%s", dateStr, format));
		}
		return false;
	}


	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate=sdf.parse(sdf.format(smdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			bdate=sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 两个日期相差的小时数
	 * author lijuntao
	 * date 2018年8月7日
	 */
	public static int differHours(Date date, Date date2){
		if(date == null || date2 == null){
			return 0;
		}
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		long timeInMillis = calendar1.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		long timeInMillis2 = calendar2.getTimeInMillis();

		return (int) ((timeInMillis - timeInMillis2)/(1000*60*60));
	}

	/**
	 * 两个日期相差的分钟数
	 * author lijuntao
	 * date 2018年8月7日
	 */
	public static int differMinute(Date date, Date date2){
		if(date == null || date2 == null){
			return 0;
		}
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		long timeInMillis = calendar1.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		long timeInMillis2 = calendar2.getTimeInMillis();

		return (int) ((timeInMillis - timeInMillis2)/(1000*60));
	}

	/**
	 * 判断时间介于某个时间段之间
	 * author lijuntao
	 * date 2018年8月7日
	 */
	public static boolean between(Date date, Date beginDate, Date endDate){
		if(date == null || date == null || date == null){
			return false;
		}
		return beginDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0;
	}

    /**
     * 当前时间的往后几天
     * @param date 当前时间
     * @param days 往后几天
     * @return
     */
	public static Date afterDaysTime(Date date, int days){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        int today = calendar1.get(Calendar.DATE);
        calendar1.set(Calendar.DATE, today+days);
        return calendar1.getTime();
    }

	/**
	 * 当前时间的往后几天
	 * @param date 当前时间
	 * @param days 往后几天
	 * @return
	 */
	public static Date beforeDaysTime(Date date, int days){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		int today = calendar1.get(Calendar.DATE);
		calendar1.set(Calendar.DATE, today-days);
		return calendar1.getTime();
	}


	/**
	 * eg:in->2018/10/9-2018/10/11    out->2018/10/9,2018/10/10,2018/10/11
	 * @param sdate
	 * @param eDate
	 * @param format
	 * @return
	 */
	public static  List<String> betweenDateList(Date sdate ,Date eDate, String format){
		long betweendays=(long) ((eDate.getTime()-sdate.getTime())/(1000 * 60 * 60 *24)+0.5);//天数间隔
		Calendar c = Calendar.getInstance();
		List<String> list=new ArrayList<String>();
		while (sdate.getTime()<=eDate.getTime()) {
			list.add(DateUtils.format(sdate, format));
			c.setTime(sdate);
			c.add(Calendar.DATE, 1); // 日期加1天
			sdate = c.getTime();
		}
		return list;
	}

	/**
	 * 校验时间格式
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str, String format) {
		boolean convertSuccess = true;
		if(null == str){
			return convertSuccess;
		}
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat fm = new SimpleDateFormat(format);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			fm.setLenient(false);
			fm.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}


	/**
	 * cron表达式转为日期
	 * @param cron
	 * @return
	 */
	public static Date getCronToDate(String cron) {
		String dateFormat="ss mm HH dd MM ? yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(cron);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}


}
