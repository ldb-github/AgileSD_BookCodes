package Payroll.AgileSD;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 判断日前是否是周五
	 * @param date
	 * @return
	 */
	public static boolean isFriday(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}
	/**
	 * 判断日期是否为周末
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
			   c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}
	/**
	 * 判断日期是否为当月最后一天
	 * @param date
	 * @return
	 */
	public static boolean isLastDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int m1 = c.get(Calendar.MONTH);
		
		c.add(Calendar.DAY_OF_MONTH, 1);
		int m2 = c.get(Calendar.MONTH);
		
		return (m1 != m2);
	}
	/**
	 * 获取日期前delta天的日期
	 * @param date
	 * @param delta
	 * @return
	 */
	public static Date before(Date date, int delta){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -delta);
		
		return c.getTime();
	}
	/**
	 * 判断日期first是否在日期second之前
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isBefore(Date first, Date second){
		return first.getTime() <= second.getTime();
	}
	/**
	 * 判断日期first是否在日期second之后
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isAfter(Date first, Date second){
		return first.getTime() >= second.getTime();
	}
}
