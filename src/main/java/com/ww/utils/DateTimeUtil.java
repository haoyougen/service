package com.ww.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateTimeUtil {
	/**
	 * 判断时间戳是否在有效范围内
	 *
	 * @param inputTimes
	 *            输入的时间戳
	 * @param regionValue
	 *            时间戳范围，毫秒级
	 * @return 是否有效，有效=true
	 */
	public static boolean checkTimeStamp(long inputTimes, long regionValue) {
		return Math.abs(inputTimes - System.currentTimeMillis()) < regionValue;
	}

	/**
	 * 时间对象转为字符串
	 *
	 * @param date
	 *            Date对象
	 * @param type
	 *            格式0=yyyy-MM-dd,1=yyyy/MM/dd,2=yyyyMMdd,3=MM/dd/yy,4=yyyy-MM-dd
	 *            HH:mm:ss
	 * @return 时间字符串
	 */
	public static String DateToString(java.util.Date date, int type) {
		String format = "yyyy-MM-dd";
		if (type == 0) {
			format = "yyyy-MM-dd";
		} else if (type == 1) {
			format = "yyyy/MM/dd";
		} else if (type == 2) {
			format = "yyyyMMdd";
		} else if (type == 3) {
			format = "MM/dd/yy";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 5) {
			format = "yyyy/MM/dd HH:mm:ss";
		} else if (type == 6) {
			format = "yyyyMMddHHmmss";
		} else if (type == 7) {
			format = "yyyyMMddHHmmssSSS";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (date != null) {
			return formatter.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 字符串对象转为Date对象,格式不符返回null
	 *
	 * @param strDate
	 *            输入字符串
	 * @param type
	 *            格式0=yyyy-MM-dd,1=yyyy/MM/dd,2=yyyyMMdd,3=MM/dd/yy,4=yyyy-MM-dd
	 *            HH:mm:ss
	 * @return Date对象
	 */
	public static java.util.Date StringToDate(String strDate, int type) {
		Date result = null;
		String format = "yyyy-MM-dd";
		if (type == 0) {
			format = "yyyy-MM-dd";
		} else if (type == 1) {
			format = "yyyy/MM/dd";
		} else if (type == 2) {
			format = "yyyyMMdd";
		} else if (type == 3) {
			format = "MM/dd/yy";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 5) {
			format = "yyyy/MM/dd HH:mm:ss";
		} else if (type == 6) {
			format = "yyyyMMddHHmmss";
		} else if (type == 7) {
			format = "yyyyMMddHHmmssSSSZ";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (strDate != null && !strDate.equals("")) {
			try {
				result = formatter.parse(strDate);
			} catch (ParseException ex) {
				result = null;
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 判断是否闰年
	 *
	 * @param year
	 *            年份
	 * @return true=是闰年
	 */
	public static boolean isLeapYear(int year) {
		return (((year % 4) == 0 && year % 100 != 0) || year % 400 == 0);
	}

	/**
	 * 返回四位年份
	 *
	 * @param date
	 *            输入时间
	 * @return 年份
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		return date.getYear() + 1900;
	}

	/**
	 * 返回月份
	 *
	 * @param date
	 *            输入时间
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}
		return date.getMonth() + 1;
	}

	/**
	 * 返回几号
	 *
	 * @param date
	 *            输入时间
	 * @return 几号
	 */
	public static int getMonthDay(Date date) {
		if (date == null) {
			return -1;
		}
		return date.getDate();
	}

	/**
	 * 返回周几,0=Sunday,1=Monday,2=Tuesday,3Wednesday,4=Thursday,5=Friday,6=Saturday
	 *
	 * @param date
	 *            输入时间
	 * @return 周几
	 */
	public static int getWeekDay(Date date) {
		if (date == null) {
			return -1;
		}
		return date.getDay();
	}

	/**
	 * 返回周几(中国),7=Sunday,1=Monday,2=Tuesday,3Wednesday,4=Thursday,5=Friday,6=Saturday
	 *
	 * @param date
	 *            输入时间
	 * @return 周几
	 */
	public static int getChinaWeekDay(Date date) {
		if (date == null) {
			return -1;
		}
		int d = date.getDay();
		if (d == 0) {
			d = 7;
		}

		return d;
	}

	/**
	 * 返回小时数（0-23）
	 *
	 * @param date
	 *            输入时间
	 * @return 小时数
	 */
	public static int getHours(Date date) {
		if (date == null) {
			return -1;
		}
		return date.getHours();
	}

	/**
	 * 返回分钟数
	 *
	 * @param date
	 *            输入时间
	 * @return 分钟数
	 */
	public static int getMinutes(Date date) {
		if (date == null) {
			return -1;
		}
		return date.getMinutes();
	}

	/**
	 * 是否是今天
	 *
	 * @param date
	 *            输入时间
	 * @return true=是
	 */
	public static boolean isToday(Date date) {
		if (date == null) {
			return false;
		}
		Date today = new Date();
		return (date.getYear() == today.getYear() && date.getMonth() == today.getMonth()
				&& date.getDate() == today.getDate()) ? true : false;
	}

	/**
	 * 是否在某个小时的区间0-23内。
	 *
	 * @param date
	 *            输入时间
	 * @param hour
	 *            某小时
	 * @return true=true
	 */
	public static boolean isInHour(Date date, int hour) {
		if (date == null || hour < 0) {
			return false;
		}
		return (getHours(date) == hour) ? true : false;
	}

	/**
	 * GMT字符串转成时间字符串
	 *
	 * @param gmtStr
	 * @param type
	 * @return
	 */
	public static String dateGMTStrToString(String gmtStr, int type) {
		String format = "yyyy-MM-dd";
		if (type == 0) {
			format = "yyyy-MM-dd";
		} else if (type == 1) {
			format = "yyyy/MM/dd";
		} else if (type == 2) {
			format = "yyyyMMdd";
		} else if (type == 3) {
			format = "MM/dd/yy";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 5) {
			format = "yyyy/MM/dd HH:mm:ss";
		} else if (type == 6) {
			format = "yyyyMMddHHmmss";
		} else if (type == 7) {
			format = "yyyyMMddHHmmssSSS";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (gmtStr != null) {
			return formatter.format(Date.parse(gmtStr));
		} else {
			return "";
		}
	}

	/**
	 * 获取当前时间的下个月1号的时间 如何time参数为空，则时分秒为当前的时分秒，否则为指定的时分秒 time的格式只能为HH:mm:ss
	 */
	public static Date getfirstDayOfNextMonthByNow(String time, int type) throws Exception {
		Calendar cal = Calendar.getInstance();
		// 当前日期到下个月1号的还差多少天
		int day = (getCurrentMonthLastDay() - cal.get(Calendar.DAY_OF_MONTH)) + 1;
		if (type == 1) {
			cal.add(Calendar.DAY_OF_MONTH, day);
		} else if (type == 2) {
			cal.add(Calendar.DAY_OF_MONTH, day);
			cal.add(Calendar.MONTH, 2);
		} else if (type == 3) {
			cal.add(Calendar.DAY_OF_MONTH, day);
			cal.add(Calendar.MONTH, 5);
		}
		return format(cal.getTime(), time);
	}

	/**
	 * 获取当前时间的特定天数的后的时间 如何time参数为空，则时分秒为当前的时分秒，否则为指定的时分秒 time的格式只能为HH:mm:ss
	 */
	public static Date getDayOfNextMonthByNow(int type) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (type == 1) {
			cal.add(Calendar.DAY_OF_MONTH, 30);
		} else if (type == 2) {
			cal.add(Calendar.DAY_OF_MONTH, 91);
		} else if (type == 3) {
			cal.add(Calendar.DAY_OF_MONTH, 180);
		} else if (type == 4) {
			cal.add(Calendar.DAY_OF_MONTH, 365);
		}
		return cal.getTime();
	}

	/**
	 * 获取当前时间的下一年的当前月1号时间 如何time参数为空，则时分秒为当前的时分秒，否则为指定的时分秒 time的格式只能为HH:mm:ss
	 */
	public static Date getfirstDayOfNextYearByNow(String time) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_MONTH) + 1);
		cal.add(Calendar.YEAR, 1);
		return format(cal.getTime(), time);
	}

	private static Date format(Date date, String time) throws Exception {
		String dateString = DateToString(date, 0);
		if (time == null || "".equals(time)) {
			time = DateToString(date, 10);
		}
		String regex = "[0-2][0-9]:[0-5][0-9]:[0-5][0-9]";
		if (!Pattern.matches(regex, time)) {
			throw new Exception("time的格式只能为HH:mm:ss");
		}
		dateString = dateString + " " + time;
		return StringToDate(dateString, 4);
	}

	/**
	 * 取得当月天数
	 */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	public static String rightNow(){
		return DateToString(new Date(), 4);
	}

	 //多种日期格式  
    private static DateFormat[] dateFormat = {  
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),  
            new SimpleDateFormat("yyyy-MM-dd HH:mm"),  
            new SimpleDateFormat("yyyy-MM-dd HH"),  
            new SimpleDateFormat("yyyy-MM-dd"),  
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),  
            new SimpleDateFormat("yyyy/MM/dd HH:mm"),  
            new SimpleDateFormat("yyyy/MM/dd HH"),  
            new SimpleDateFormat("yyyy/MM/dd"),  
    };  
	public static void main(String[] args) {
		try {
			System.out.println(DateTimeUtil.DateToString(DateTimeUtil.getDayOfNextMonthByNow(1), 4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
