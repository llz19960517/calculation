package com.anft.calculation.util;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理辅助类
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author gaohj
 * @version 1.0
 */
public class DateTimeUtil {
//	private static Log log = Log.getLog(DateTimeUtil.class);

  public static final String LONGTIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  public static final Integer Sixty =60;
  public static final Integer THOUSAND =1000;
  public static final String LONGTIME_PATTERN_MIN = "yyyy-MM-dd HH:mm";

  public static final String LONGTIME_PATTERN2 = "yyyyMMddHHmmss";

  public static final String MEDIATIME_PATTERN = "yyyy-MM-dd";

  public static final String MEDIATIME_PATTERN2 = "yyyyMMdd";

  public static final String MEDIATIME_YEARANDMONEY = "yyyyMM";
  public static final String MEDIATIME_YEARANDMONEY1 = "yyyy-MM";

  public static final String SHORTTIME_PATTERN = "HH:mm:SS";

  private static SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

  private static final SimpleDateFormat mysdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static String getSignTime() {
    Calendar ca = Calendar.getInstance();
    SimpleDateFormat sp1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
    return sp1.format(ca.getTime());
  }

  public static String getNowString() {
    Calendar ca = Calendar.getInstance();
    SimpleDateFormat sp1 = new SimpleDateFormat(MEDIATIME_PATTERN);
    return sp1.format(ca.getTime());
  }
  public static int getFullMins(Date startTime, Date endTime) {
    int result = 0;
    final double ceil = Math.floor((endTime.getTime() - startTime.getTime()) / 1000 / 60.00);
    result = (int)ceil;
    return result;
  }

  /**
   * 算的两个时间的相差秒
   * @param startTime
   * @param endTime
   * @return
   */
  public static Long getFullSs(Date startTime, Date endTime) {

    long l = (endTime.getTime() - startTime.getTime()) / 1000;

    return l;

  }



  public static String getNowFullString() {
    Calendar ca = Calendar.getInstance();
    SimpleDateFormat sp1 = new SimpleDateFormat(LONGTIME_PATTERN);
    return sp1.format(ca.getTime());
  }



  /**
   * 在某个时间增加一定的分钟数
   */
  public static Date plusMinuteByDate(Date date, int minutes) {
    DateTime dateTime = new DateTime(date);
    dateTime = dateTime.plusMinutes(minutes);
    return dateTime.toDate();
  }


  /**
   * 在某个时间增加一定的秒数
   */
  public static Date plusSsByDate(Date date, long ss) {
    DateTime dateTime = new DateTime(date);
    dateTime = dateTime.plusSeconds((int)ss);
    return dateTime.toDate();
  }

  public static void main(String[] args) {

//    double d  =2.20;
//    int i=2;
//    double v = d * i;
//    System.out.println(v);

    Date date = dateAddDay(new Date(), 1);
    System.out.println(date);
  }

  /**
   * @param @param
   *     startTime  10:18
   * @param @param
   *     endTime   23:13
   * @param @return
   *
   * @return int
   *
   * @Title: getMins
   * @Description:获取两个时间差的分钟
   */
  public static int getMins(String startTime, String endTime) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    try {
      Date d1 = df.parse("2004-03-26 " + startTime + ":00");
      Date d2 = df.parse("2004-03-26 " + endTime + ":00");
      result = (int) ((d2.getTime() - d1.getTime()) / 1000 / 60);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  public static int getTwoSecondTimesMins(String startTime, String endTime) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    try {
      Date d1 = df.parse("2004-03-26 " + startTime);
      Date d2 = df.parse("2004-03-26 " + endTime);
      result = (int) ((d2.getTime() - d1.getTime()) / 1000 / 60);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  public static int isLtTime(String startTime, String endTime) {
    int i = Integer.parseInt(startTime.replaceAll(":", "").replaceAll("", ""));
    int y = Integer.parseInt(endTime.replaceAll(":", "").replaceAll("", ""));
    if (i > y) {
      return 1;
    } else if (i == y) {
      return 0;
    } else {
      return -1;
    }
  }

  public static boolean isDateTime(String dt) {
    SimpleDateFormat sdf = new SimpleDateFormat();
    try {
      sdf.parse(dt);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 得到指定日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
   *
   * @param dt
   *     指定的日期
   *
   * @return
   */
  public static String getFullTime(Calendar dt) {
    return formatCalendar(dt, LONGTIME_PATTERN);
  }

  /**
   * 得到当前日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
   *
   * @return
   */
  public static String getFullTime() {
    return getFullTime(Calendar.getInstance());
  }

  /**
   * 得到当前日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
   *
   * @return
   */
  public static String getDateTime(String dateTimePattern) {
    return formatCalendar(Calendar.getInstance(), dateTimePattern);
  }

  /**
   * 得到短日期格式：HH:mm:SS.ms
   *
   * @param datetime
   *     日期
   *
   * @return
   */
  public static String getShortTime(Calendar datetime) {
    return formatCalendar(datetime, SHORTTIME_PATTERN);
  }


  /**
   * 比较两个日期的大小
   *
   * @param nowDate
   * @param lastDate
   *
   * @return 1 大于 0 相等 -1 小于
   */
  public static int comparativeDate(Date nowDate, Date lastDate)

  {
    SimpleDateFormat sdf = new SimpleDateFormat(MEDIATIME_PATTERN);
    String nowTime = sdf.format(nowDate);
    String lastTime = sdf.format(lastDate);
    try {
      nowDate = sdf.parse(nowTime);
      lastDate = sdf.parse(lastTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    if (nowDate.equals(lastDate)) {
      return 0;
    }
    if (nowDate.after(lastDate)) {
      return 1;
    }
    if (nowDate.before(lastDate)) {
      return -1;
    }
    return 0;
  }

  /**
   * 得到当前日期短日期格式：HH:mm:SS.ms
   *
   * @return
   */
  public static String getShortTime() {
    return getShortTime(Calendar.getInstance());
  }

  /**
   * 得到日期格式：YYYY-MM-DD
   *
   * @param datetime
   *     日期
   *
   * @return
   */
  public static String getMediumTime(Calendar datetime) {
    return formatCalendar(datetime, MEDIATIME_PATTERN);
  }

  /**
   * 得到当前日期的年月日格式：YYYY-MM-DD
   *
   * @return
   */
  public static String getMediumTime() {
    return getMediumTime(Calendar.getInstance());
  }

  /**
   * 比较两个日期的早晚，日期格式为：yyyy-MM-dd
   *
   * @param date1
   *     日期1
   * @param date2
   *     日期2
   *
   * @return 如果日期1晚于日期2，则返回大于0；如果日期1等日期2，则返回0；如果日期1早于日期2，则返回小于0
   */
  public static int compareDate(String date1, String date2) {
    Date dt1 = toDate(date1);
    Date dt2 = toDate(date2);
    return dt1.compareTo(dt2);
  }

  /**
   * 得到当前年份
   *
   * @return
   */
  public static int getCurrentYear() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.YEAR);
  }

  /**
   * 得到当前月份
   *
   * @return
   */
  public static int getCurrentMonth() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.MONTH) + 1;
  }

  /**
   * 得到当前日
   *
   * @return
   */
  public static int getCurrentDay() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 格式化给定时间
   *
   * @param cal
   *     给定时间
   * @param pattern
   *     要格式化的模式
   *
   * @return 格式化后的字符串
   */
  public static String formatCalendar(Calendar cal, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(cal.getTime());
  }

  /**
   * 将字符串(yyy-MM-dd)转化为日期
   *
   * @param strDate
   *     待转化的日期字符串
   *
   * @return 日期对象, 如果字符串格式非法，则返回null
   *
   * @throws
   */
  public static Date toDate(String strDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(MEDIATIME_PATTERN);
    try {
      return sdf.parse(strDate);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 判断time是否在from，to之内
   *
   * @param time
   *     指定日期
   * @param from
   *     开始日期
   * @param to
   *     结束日期
   *
   * @return
   */
  public static boolean belongCalendar(Date time, Date from, Date to) {
    Calendar date = Calendar.getInstance();
    date.setTime(time);

    Calendar after = Calendar.getInstance();
    after.setTime(from);

    Calendar before = Calendar.getInstance();
    before.setTime(to);

    if (date.after(after) && date.before(before)) {
      return true;
    } else {
      return false;
    }
  }

  public static Date getMonFirstDay(Integer add) {
    Calendar cal = Calendar.getInstance();
    Date now = new Date();
    cal.setTime(now);
    cal.add(Calendar.MONTH, add);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    String resultTime = year + "-" + month + "-01";
    Date date = null;
    try {
      date = sp.parse(resultTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  // 本月最后一天日期
  public static Date getThisMonLastDay() {
    Calendar cal = Calendar.getInstance();
    Date now = new Date();
    cal.setTime(now);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    String resultTime = year + "-" + month + "-" + day;
    Date date = null;
    try {
      date = sp.parse(resultTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }


  /**
   * 获得两个日期的 天数集合
   */
  public static List<String> getDay(Date start, Date end) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    List<String> list = new ArrayList<String>();
    try {
      Date date = start;
      Calendar cd = Calendar.getInstance();
      while (date.getTime() <= end.getTime()) {
        list.add(sdf.format(date));
        cd.setTime(date);
        cd.add(Calendar.DATE, 1);// 增加一天
        date = cd.getTime();
      }
    } catch (Exception e) {
    }
    return list;
  }

  // *年*月最后一天日期
  public static Integer getMonLastDay(Integer year, Integer month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  // 某月第一天日期
  public static Date getMonFirstDay(Integer year, Integer month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month - 1);
    String resultTime = year + "-" + month + "-01";
    Date date = null;
    try {
      date = sp.parse(resultTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * 将字符串(yyyy-MM-dd HH:mm:ss)转化为日期
   *
   * @param strDate
   *     待转化的日期字符串
   *
   * @return 日期对象, 如果字符串格式非法，则返回null
   *
   * @throws
   */
  public static Date toDateTime(String strDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(LONGTIME_PATTERN);
    try {
      return sdf.parse(strDate);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 将字符串(yyy-MM-dd)转化为日期
   *
   * @param strDate
   *     待转化的日期字符串
   *
   * @return 日期对象, 如果字符串格式非法，则返回null
   *
   * @throws
   */
  public static Calendar toCalendar(String strDate) {
    Date dt = toDate(strDate);
    Calendar cal = Calendar.getInstance();
    cal.setTime(dt);
    return cal;
  }


  /**
   * 根据 日期（时间）字符串 解析日期（时间）
   *
   * @param strTime
   *     字符串形式的日期（时间），格式为：
   *     <p/>
   *     （1）yyyyMMdd （2） yyyy-MM-dd （3）yyyyMMddHHmmss （4） yyyy-MM-dd
   *     HH:mm:ss
   *
   * @return Date 如果解析成功，则返回Date类型的对象，否则为 null
   */
  private static Date parseDate(final String strTime) {
    if (strTime == null || strTime.trim().length() < 6) // 日期时间的最小长度必须大于等于6
    {
//			log.error("DateTimeUtil.parseDate()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");

      return null;
    }

    try {
      if (strTime.indexOf('-') >= 0) // yyyy-MM-dd HH:mm:ss
      {
        if (strTime.length() > 10) {
          SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
          return timeFormat2.parse(strTime);
        } else {
          SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
          return dateFormat2.parse(strTime);
        }
      } else
      // yyyyMMddHHmmss
      {
        if (strTime.length() > 8) {
          SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
          return timeFormat1.parse(strTime);
        } else {
          SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
          return dateFormat1.parse(strTime);
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 将日期转化为指定格式的字符串
   *
   * @param date
   *     日期
   * @param format
   *     格式字符串
   *
   * @return 指定格式的日期字符串
   */
  public static String formatDate(Date date, String format) {
    if (null == date) {
      return null;
    }
    SimpleDateFormat sdFromat = new SimpleDateFormat(format);
    return sdFromat.format(date);
  }

  /**
   * 日期转换为字符串 短日期
   *
   * @param date
   *
   * @return
   */
  public static String dateToString(Date date) {
    if (date != null) {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(date);
    } else {
      return null;
    }
  }

  /**
   * 日期转换为字符串 长日期
   *
   * @param date
   *
   * @return
   */
  public static String dateTimeToString(Date date) {
    if (date != null) {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(date);
    } else {
      return null;
    }
  }

  /**
   * 获取指定日期以后（以前）一定年数的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的年数，是负数表示向前的年数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddYear(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddYear()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.YEAR, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }

  /**
   * 获取指定日期以后（以前）一定月份数的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的月份数，是负数表示向前的月份数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddMonth(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddMonth()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.MONTH, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }

  /**
   * 获取指定日期以后（以前）n个星期的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的星期数，是负数表示向前的星期数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddWeek(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddWeek()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.WEEK_OF_MONTH, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }

  /**
   * 获取指定日期以后（以前）一定天数的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的天数，是负数表示向前的天数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddDay(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddDay()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.DAY_OF_MONTH, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }


  /**
   * 获取指定日期以后（以前）一定天数的日期
   *
   * @param strTime
   * @param count
   *     要增加（减少）的天数，是负数表示向前的天数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static Date dateAddDay(Date strTime, int count) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(strTime);
    calendar.add(Calendar.DAY_OF_MONTH, count);
    Date dtDst = calendar.getTime();
    return dtDst;
  }

  /**
   * 获取指定时间以后（以前）一定小时数的时间
   *
   * @param strTime
   *     字符串形式的时间，格式可以为：（1）yyyyMMddhhmmss （2） yyyy-MM-dd hh:mm:ss
   * @param count
   *     要增加（减少）的小时数，是负数表示向前的小时数
   *
   * @return String 格式与输入的时间格式相同
   */
  public static String timeAddHour(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.timeAddHour()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.HOUR_OF_DAY, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd hh:mm:ss
    {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(dtDst);
    } else
    // //yyyyMMddhhmmss
    {
      SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
      return timeFormat1.format(dtDst);
    }
  }


  /**
   * 获取指定时间以后（以前）一定分钟数的时间
   *
   * @param strTime
   *     字符串形式的时间，格式可以为：（1）yyyyMMddhhmmss （2） yyyy-MM-dd hh:mm:ss
   * @param count
   *     要增加（减少）的分钟数，是负数表示向前的分钟数
   *
   * @return String 格式与输入的时间格式相同
   */
  public static String timeAddMinute(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.timeAddMinute()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.MINUTE, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd hh:mm:ss
    {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(dtDst);
    } else
    // //yyyyMMddhhmmss
    {
      SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
      return timeFormat1.format(dtDst);
    }
  }


  /**
   * 获取指定时间以后（以前）一定秒数的时间
   *
   * @param strTime
   *     字符串形式的时间，格式可以为：（1）yyyyMMddhhmmss （2） yyyy-MM-dd hh:mm:ss
   * @param count
   *     要增加（减少）的秒数，是负数表示向前的秒数
   *
   * @return String 格式与输入的时间格式相同
   */
  public static String timeAddSecond(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.timeAddSecond()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.SECOND, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd hh:mm:ss
    {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(dtDst);
    } else
    // //yyyyMMddhhmmss
    {
      SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
      return timeFormat1.format(dtDst);
    }
  }

  /**
   * 将字符串形式的日期时间格式成显示形式
   *
   * @param strTime
   *     字符串形式的日期（时间），格式为：
   *     <p/>
   *     （1）yyyyMMdd （2）yyyyMMddHHmmss
   *
   * @return String
   */
  public static String displayDateTimeCN(String strTime) {
    String retString = strTime;
    try {
      if (strTime == null) {
        retString = "";
      } else if (strTime.length() == 14) {
        retString =
            strTime.substring(0, 4) + "年" + strTime.substring(4, 6) + "月" + strTime.substring(6, 8)
                + "日 " + strTime.substring(8, 10) + "时" + strTime.substring(10, 12) + "分" + strTime
                .substring(12, 14) + "秒";
      } else if (strTime.length() == 8) {
        retString =
            strTime.substring(0, 4) + "年" + strTime.substring(4, 6) + "月" + strTime.substring(6, 8)
                + "日";
      } else if (strTime.length() == 6) {
        retString = strTime.substring(0, 4) + "年" + strTime.substring(4, 6) + "月";
      } else if (strTime.length() <= 4) {
        retString = strTime.substring(0, 4) + "年";
      }
    } catch (Exception e) {
//			log.error("DateTimeUtil.displayDateTimeCN()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
    }
    return retString;
  }

  /**
   * @param @param
   *     startTime  10:18
   * @param @param
   *     endTime   23:13
   * @param @return
   *
   * @return int
   *
   * @Title: getMins
   * @Description:获取两个时间差的分钟
   */
  public static int getSecond(String startTime, String endTime) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    try {
      Date d1 = df.parse("2004-03-26 " + startTime + ":00");
      Date d2 = df.parse("2004-03-26 " + endTime + ":00");
      result = (int) ((d2.getTime() - d1.getTime()) / 1000);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  /**
   * @param @param
   *     startTime
   * @param @param
   *     x
   * @param @return
   *
   * @return String
   *
   * @Title: addDateMinut
   * @Description:增加多少分钟
   */
  public static String addDateMinut(String startTime, int x) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
    SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");// 24小时制
    Date date = null;
    try {
      date = format.parse("2014-03-26 " + startTime + ":00");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (date == null) {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.MINUTE, x);// 24小时制
    date = cal.getTime();
    cal = null;
    return format1.format(date);
  }

  public static String getStringNowShortTime() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
    String time = format.format(new Date());
    return time + " 00:00:00";
  }

  public static int CompareTwoString(String a, String b) {
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    try {
      if (a.equals(b)) {
        return 0;
      }
      Date aa = df.parse(a);
      Date bb = df.parse(b);
      if (aa.getTime() < bb.getTime()) {
        return 1;
      } else {
        return -1;
      }
    } catch (ParseException e) {
      e.printStackTrace();
      return -2;
    }
  }


  public static String differMins(Date inTime) {
    int minutes = (int) ((new Date().getTime() - inTime.getTime()) / (1000 * 60));
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }


  public static String differMins(Date inTime, Date outTime) {
    if (inTime == null || outTime == null) {
      return " ";
    }
    int minutes = (int) ((outTime.getTime() - inTime.getTime()) / (1000 * 60));
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }




  public static String differMins(int minutes) {
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }

  public static String[] getYesterDayWeek() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String[] a = new String[7];
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    for (int i = 0; i < 7; i++) {
      a[i] = sdf.format(calendar.getTime());
      calendar.add(Calendar.DATE, -1);
    }
    return a;
  }


  /**
   * 分钟转天时分
   *
   * @param min
   *
   * @return day hour min
   */
  public static String minConvertDayHourMin(Double min) {
    String html = "0分";
    if (min != null) {
      Double m = (Double) min;
      String format;
      Object[] array;
      Integer days = (int) (m / (60 * 24));
      Integer hours = (int) (m / (60) - days * 24);
      Integer minutes = (int) (m - hours * 60 - days * 24 * 60);
      if (days > 0) {
        format = "%1$,d天%2$,d时%3$,d分";
        array = new Object[]{days, hours, minutes};
      } else if (hours > 0) {
        format = "%1$,d时%2$,d分";
        array = new Object[]{hours, minutes};
      } else {
        format = "%1$,d分";
        array = new Object[]{minutes};
      }
      html = String.format(format, array);
    }

    return html;
  }

  /**
   * _12302_2019-07-22<p></p>
   * like this<p>
   *
   * 15:32  ->(Date) 2019-07-22 15:32:00<p>
   *  9:42  ->(Date) 2019-07-22 09:42:00
   * @param dataStr
   * @return java.util.Date
   */
  public static Date mergeData(String dataStr){
    Date result = null;
    String tempStr = mysdf.format(new Date()).substring(0,11);
    try {
      result = mysdf.parse(tempStr+dataStr+":00");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * _12302_2019-07-22<p></p>
   *
   */
  public static Date dataAddMins(Date date,int mins){
    date.setTime(date.getTime() + mins * 60 * 1000);
    return date;
  }

  /**
   * 天时分转分钟
   *
   * @param day
   * @param hour
   * @param min
   *
   * @return min
   */
  public static int dayHourMinConvertMin(int day, int hour, int min) {
    int days = day * 24 * 60;
    int hours = hour * 60;
    return days + hours + min;
  }

  public static String[] getDayWeek() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String[] a = new String[7];
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -6);
    for (int i = 0; i < 7; i++) {
      a[i] = sdf.format(calendar.getTime());
      calendar.add(Calendar.DATE, 1);
    }
    return a;
  }

//	public static void main(String[] args) {
//		Date lastMonth=DateTimeUtil.getMonFirstDay(-1);
//		lastMonth=DateTimeUtil.dateAddMonth(lastMonth,-3);
//		Date yesterdayMonth=DateTimeUtil.toDate(DateTimeUtil.dateAddDay(DateTimeUtil.getNowString(),-1));
//		yesterdayMonth=DateTimeUtil.dateAddMonth(yesterdayMonth, -3);
//		System.out.println(lastMonth.toLocaleString());
//		System.out.println(yesterdayMonth.toLocaleString());
////		Date now=DateTimeUtil.toDate(DateTimeUtil.getNowString());
////		Date yesterday=DateTimeUtil.toDate(DateTimeUtil.dateAddDay(DateTimeUtil.getNowString(),-1));
////		System.out.println(now.toLocaleString());
////		System.out.println(yesterday.toLocaleString());
////		String[] s=getDayWeek();
////		for(int i=0;i<s.length;i++){
////		}
////
////		Date date = getMonFirstDay(-1);
////        System.out.println(date.toLocaleString());
//	}

  public static Date dateAddMonth(Date lastMonth, int i) {
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(lastMonth);

    calendar.add(Calendar.MONTH, i);
    return calendar.getTime();
  }

  public static int compare_date(String DATE1, String DATE2) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date dt1 = df.parse(DATE1);
      Date dt2 = df.parse(DATE2);
      if (dt1.getTime() > dt2.getTime()) {
        System.out.println("dt1 在dt2前");
        return 1;
      } else if (dt1.getTime() < dt2.getTime()) {
        System.out.println("dt1在dt2后");
        return -1;
      } else {
        return 0;
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return 0;
  }
 
	/*public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
//System.out.println(Calendar.DATE);//5
		cal.add(Calendar.DATE,-1);
		Date time=cal.getTime();
		System.out.println(new SimpleDateFormat("yyyyMM").format(time));
	}*/
 

  public static boolean isSameDate(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);

    boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
        .get(Calendar.YEAR);
    boolean isSameMonth = isSameYear
        && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    boolean isSameDate = isSameMonth
        && cal1.get(Calendar.DAY_OF_MONTH) == cal2
        .get(Calendar.DAY_OF_MONTH);

    return isSameDate;
  }

//  public static void main(String[] args) throws ParseException, InterruptedException {
////    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
////    String a = "2017-02-28";
////    Date expiryEndDate = sim.parse(a);
////
////    Calendar cd = Calendar.getInstance();
////    System.out.println(sim.format(cd.getTime()));
////    System.out.println(expiryEndDate.getYear());
////    System.out.println(expiryEndDate.getYear()+1900);
////    cd.set(expiryEndDate.getYear() + 1900, expiryEndDate.getMonth(), expiryEndDate.getDate());
////    System.out.println(sim.format(cd.getTime()));
////    cd.add(Calendar.DATE, 1);
////    System.out.println(sim.format(cd.getTime()));
////    cd.add(Calendar.MONTH, 1);
////    cd.add(Calendar.DATE, -1);
////    Date newExpiryEndDate = cd.getTime();
////
////    System.out.println(sim.format(newExpiryEndDate));
//    String strTime="2019-12-05 12:00:00";
//    String strTime2="2019-12-05 12:14:01";
//    Date startTime =strToDateLong(strTime);
//    Date endTime =strToDateLong(strTime2);;
//    final String stringToday = DateTimeUtil.getStringToday(endTime);
//    final int fullMins = getFullMins(startTime, endTime);
//    System.out.println("strTime-->"+strTime);
//    System.out.println("endTime-->"+stringToday);
//    System.out.println("分钟数："+fullMins);
//
//
//
//
//  }

  public static Date strToDateLong(String strDate) {
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
     }
  public static String getStringToday(Date currentTime) {

       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
       return dateString;
  }


  /**
   * 格式化某一时间
   */
  public static String getStringShort(Date date) {
    SimpleDateFormat sp = new SimpleDateFormat();
    sp.applyPattern(MEDIATIME_PATTERN);
    if (date != null && MEDIATIME_PATTERN != null) {
      return sp.format(date);
    }
    return "时间格式化异常！";
  }



  /**
   * 格式化某一时间
   */
  public static String getHhmmss(Date date) {
    SimpleDateFormat sp = new SimpleDateFormat();
    sp.applyPattern(SHORTTIME_PATTERN);
    if (date != null && SHORTTIME_PATTERN != null) {
      return sp.format(date);
    }
    return "时间格式化异常！";
  }



  public static Date StringToDate(String date, String pattern) {
    Date myDate = null;
    if (date != null) {
      try {
        myDate = getDateFormat(pattern).parse(date);
      } catch (Exception e) {
      }
    }
    return myDate;
  }

  private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

  private static final Object object = new Object();

  private static SimpleDateFormat getDateFormat(String pattern)
          throws RuntimeException {
    SimpleDateFormat dateFormat = threadLocal.get();
    if (dateFormat == null) {
      synchronized (object) {
        if (dateFormat == null) {
          dateFormat = new SimpleDateFormat(pattern);
          dateFormat.setLenient(false);
          threadLocal.set(dateFormat);
        }
      }
    }
    dateFormat.applyPattern(pattern);
    return dateFormat;
  }


  public static String getDate(Date date) {
    return DateToString(date, "yyyy-MM-dd");
  }



  public static String DateToString(Date date, String pattern) {
    String dateString = null;
    if (date != null) {
      try {
        dateString = getDateFormat(pattern).format(date);
      } catch (Exception e) {
      }
    }
    return dateString;
  }
  /**
   * 获得两个日期的 天数集合
   *
   * @param suffix
   *     格式化字符串
   */
  public static List<String> getDay(Date start, Date end, String suffix) {
    SimpleDateFormat sdf = new SimpleDateFormat(suffix);
    List<String> list = new ArrayList<>();
    try {
      //  Date dateTmp = StringToDate(getDate(earlyDate), DatePattern.YYYY_MM_DD);
      Date date = StringToDate(getDate(start), "yyyy-MM-dd");
      Calendar cd = Calendar.getInstance();
      long endTimeVal = StringToDate(getDate(end), "yyyy-MM-dd").getTime();
      while (date.getTime() <= endTimeVal) {
        list.add(sdf.format(date));
        cd.setTime(date);
        cd.add(Calendar.DATE, 1);// 增加一天
        date = cd.getTime();
      }
    } catch (Exception e) {
    }
    return list;
  }


  /**
   * 获取停车时间段 跨天的  list集合
   *
   * eg[{"end":1578153599000,"begin":1578108420000},{"end":1578239999000,"begin":1578153600000}]
   * @param startDate
   * @param nowDate
   * @return
   */

  public static List<HashMap<String, Date>> getParkIngDateList(Date startDate, Date nowDate) {

   Date endTime=nowDate;
    List<HashMap<String, Date>> result = new ArrayList<>();
    HashMap<String, Date> map;
    if (null == nowDate) {
      nowDate = getNowFullDate();
    }
    List<String> days = DateTimeUtil.getDay(startDate, nowDate,
            "yyyy-MM-dd");
    if (null != days && days.size() > 0) {
      if (days.size() == 1) {
        map = new HashMap<>();
        map.put("begin", startDate);
        map.put("end", nowDate);
        result.add(map);
      } else {
        int dayIndex = days.size();
        for (int i = 0; i < dayIndex; i++) {
          map = new HashMap<>();
          String tempDays = days.get(i);
          Date tempDate =
                  getDateByStr(tempDays + " 00:00:00");
          Date endDate =
                  getDateByStr(tempDays + " 23:59:59");
          if (i == 0) {
            map.put("begin", startDate);
            map.put("end", endDate);
            result.add(map);
            continue;
          }
          map.put("begin", tempDate);
          map.put("end", endDate);
          if (i == dayIndex - 1) {
            int timeIndexA = comparativeDate(nowDate, endDate);// 比较日期，并根据返回值进行判断
            if (timeIndexA >= 0) {
              nowDate = endTime;
            }
            map.put("end", nowDate);
          }
          result.add(map);
        }
      }
    }
    return result;
  }


  public static Date getNowFullDate() {
    SimpleDateFormat sp = new SimpleDateFormat();
    sp.applyPattern("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    try {
      String s = sp.format(date);
      return sp.parse(s);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }


  public static Date getDateByStr(String dateStr) {
    SimpleDateFormat formatter = null;
    if (dateStr == null) {
      return null;
    } else if (dateStr.length() == 10) {
      formatter = new SimpleDateFormat("yyyy-MM-dd");
    } else if (dateStr.length() == 16) {
      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    } else if (dateStr.length() == 19) {
      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    } else if (dateStr.length() > 19) {
      dateStr = dateStr.substring(0, 19);
      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    } else {
      return null;
    }
    try {
      return formatter.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }



  /**
   * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
   *
   * @param nowTime 当前时间
   * @param startTime 开始时间
   * @param endTime 结束时间
   * @return
   * @author jqlin
   */
  public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
    if (nowTime.getTime() == startTime.getTime()) {
      return true;
    }

    Calendar date = Calendar.getInstance();
    date.setTime(nowTime);

    Calendar begin = Calendar.getInstance();
    begin.setTime(startTime);

    Calendar end = Calendar.getInstance();
    end.setTime(endTime);

    if (date.after(begin) && date.before(end)) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * 时间重贴
   * @param range1Start
   * @param range1End
   * @param range2Start
   * @param range2End
   * @return
   */
  public static int calcOverlapDays(Date range1Start, Date range1End,
                                    Date range2Start, Date range2End) {

    long range1StartTime = range1Start.getTime();
    long range1EndTime = range1End.getTime();

    long range2StartTime = range2Start.getTime();
    long range2EndTime = range2End.getTime();

    assert range1StartTime > range1EndTime;
    assert range2StartTime > range2EndTime;

    long overlapTime = Math.min(range1EndTime, range2EndTime)
            - Math.max(range1StartTime, range2StartTime);

    return (overlapTime < 0) ? 0
            : (int) (overlapTime   / 1000 + 1);
  }




}
