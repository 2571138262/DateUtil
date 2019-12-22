package com.baixiaowen.dateutil.dateinjava;
import java.sql.Timestamp;
import java.util.*;

/**
 * java中获取日期的工具类
 */
public class DateUtils {
    
    private static DateUtils dateUtils = new DateUtils();
    
    private DateUtils() {}
    
    public static DateUtils ins(){
        return dateUtils;
    }

    /**
     * 获取当天的开始时间
     * @return java.util.Date
     */
    public Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天的结束时间
     * @return java.util.Date
     */
    public Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取昨天的开始时间
     * @return java.util.Date
     */
    public Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取昨天的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取明天的开始时间
     * @return java.util.Date
     */
    public Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     * 获取明天的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取本周的开始时间
     * @return java.util.Date
     */
    @SuppressWarnings("unused")
    public Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取上周的开始时间
     * @return java.util.Date
     */
    @SuppressWarnings("unused")
    public Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取上周的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfLastWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取本月的开始时间
     * @return java.util.Date
     */
    public Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取上月的开始时间
     * @return java.util.Date
     */
    public Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上月的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取本年的开始时间
     * @return java.util.Date
     */
    public Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本年的结束时间
     * @return java.util.Date
     */
    public Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    /**
     * 获取某个日期的开始时间
     * @param d 开始时间 
     * @return 返回时间戳  java.sql.Timestamp
     */
    public Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     * @param d
     * @return 返回时间戳  java.sql.Timestamp
     */
    public Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取今年是哪一年
     * @return java.lang.Integer
     */
    public Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     * @return int 
     */
    public int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 两个日期相减得到的天数
     * @param beginDate
     * @param endDate
     * @return int 
     */
    public int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }

        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);

        int days = new Long(diff).intValue();

        return days;
    }

    /**
     * 两个日期相减得到的毫秒数
     * @param beginDate
     * @param endDate
     * @return long
     */
    public long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    /**
     * 获取两个日期中的最大日期
     * @param beginDate
     * @param endDate
     * @return java.util.Date
     */
    public Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    /**
     * 获取两个日期中的最小日期
     * @param beginDate
     * @param endDate
     * @return java.util.Date
     */
    public Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    /**
     * 返回某月该季度的第一个月
     * @param date
     * @return java.util.Date
     */
    public Date getFirstSeasonDate(Date date) {
        final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    /**
     * 返回某个日期下几天的日期
     * @param date
     * @param i
     * @return java.util.Date
     */
    public Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    /**
     * 返回某个日期前几天的日期
     * @param date
     * @param i
     * @return java.util.Date
     */
    public Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    /**
     * 获取某年某月到某年某月按天的切片日期集合（间隔天数的集合）
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getTimeList(int beginYear, int beginMonth, int endYear,
                                   int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }

                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
        }
        return list;
    }

    /**
     * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
     * @param beginYear
     * @param beginMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    public static void main(String[] args) {
        System.out.println("1）获取当天的开始时间 " + DateUtils.ins().getDayBegin());
        System.out.println("2）获取当天的结束时间 " + DateUtils.ins().getDayEnd());
        System.out.println("3）获取昨天的开始时间 " + DateUtils.ins().getBeginDayOfYesterday());
        System.out.println("4）获取昨天的结束时间 " + DateUtils.ins().getEndDayOfYesterDay());
        System.out.println("5）获取明天的开始时间 " + DateUtils.ins().getBeginDayOfTomorrow());
        System.out.println("6）获取明天的结束时间 " + DateUtils.ins().getEndDayOfTomorrow());
        System.out.println("7）获取本周的开始时间 " + DateUtils.ins().getBeginDayOfWeek());
        System.out.println("8）获取本周的结束时间 " + DateUtils.ins().getEndDayOfWeek());
        System.out.println("9）获取上周的开始时间 " + DateUtils.ins().getBeginDayOfLastWeek());
        System.out.println("10）获取上周的结束时间 " + DateUtils.ins().getEndDayOfLastWeek());
        System.out.println("11）获取本月的开始时间 " + DateUtils.ins().getBeginDayOfMonth());
        System.out.println("12）获取本月的结束时间 " + DateUtils.ins().getEndDayOfMonth());
        System.out.println("13）获取上月的开始时间 " + DateUtils.ins().getBeginDayOfLastMonth());
        System.out.println("14）获取上月的结束时间 " + DateUtils.ins().getEndDayOfLastMonth());
        System.out.println("15）获取本年的开始时间 " + DateUtils.ins().getBeginDayOfYear());
        System.out.println("16）获取本年的结束时间 " + DateUtils.ins().getEndDayOfYear());
        System.out.println("17）获取某个日期的开始时间 " + DateUtils.ins().getDayStartTime(new Date()));
        System.out.println("18）获取某个日期的结束时间 " + DateUtils.ins().getDayEndTime(new Date()));
        System.out.println("19）获取今年是哪一年 " + DateUtils.ins().getNowYear());
        System.out.println("20）获取本月是哪一月 " + DateUtils.ins().getNowMonth());
        System.out.println("21）两个日期相减得到的天数 " + DateUtils.ins().getDiffDays(DateUtils.ins().getDayBegin(), DateUtils.ins().getBeginDayOfYesterday()));
        System.out.println("22）两个日期相减得到的毫秒数 " + DateUtils.ins().dateDiff(DateUtils.ins().getDayBegin(), DateUtils.ins().getBeginDayOfYesterday()));
        System.out.println("23）获取两个日期中的最大日期 " + DateUtils.ins().max(DateUtils.ins().getDayBegin(), DateUtils.ins().getBeginDayOfYesterday()));
        System.out.println("24）获取两个日期中的最小日期 " + DateUtils.ins().min(DateUtils.ins().getDayBegin(), DateUtils.ins().getBeginDayOfYesterday()));
        System.out.println("25）返回某月该季度的第一个月 " + DateUtils.ins().getFirstSeasonDate(new Date()));
        System.out.println("26）返回某个日期下几天的日期 " + DateUtils.ins().getNextDay(new Date(), 1));
        System.out.println("27）返回某个日期前几天的日期 " + DateUtils.ins().getFrontDay(new Date(), 1));
        System.out.println("28）获取某年某月到某年某月按天的切片日期集合（间隔天数的集合）" + DateUtils.ins().getTimeList(2019,1,2019, 3,30));
        System.out.println("29）获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）" + DateUtils.ins().getTimeList(2019, 1, 30));
    }
}
