package com.itlozg.admin.util;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类
 */
public class DateUtil {
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    // 获取明年 01-01 12:00:00 的毫秒级时间戳
    public static Long yearTimeInMillis() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Long time = calendar.getTimeInMillis();
        return time;
    }

    /**
     * 得到n天之后的日期 是 Long 类型的时间毫秒
     *
     * @param days
     * @return
     */
    public static Long getDateDayLongTime(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); //   日期减 如果不够减会将月变动
        Long date = canlendar.getTime().getTime();
        return date;
    }

    /**
     * 获取md5字符串
     *
     * @param str
     * @return
     */
    public static String getMd5(String str) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for (byte x : bs) {
            if ((x & 0xff) >> 4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }

    //获取2天后的时间
    public static String twoDayAgo(Integer day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        Date time = cal.getTime();
        String threeDayAgo = sdf.format(time);//两天后的时间。
        return threeDayAgo;
    }


    /**
     * 获取unix时间戳
     *
     * @return
     * @throws Exception
     */
    public static Long getUnixTime(String dateStr) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long epoch = df.parse(dateStr).getTime();
            return epoch / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    //获取明天0点00分00秒的时间戳。返回的是秒。
    public static Long getTomorrowLongTimes() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Long end = cal.getTimeInMillis();
        return end / 1000;
    }

    public static void main(String[] args) {
        String startTime = "2022-03-17 00:00:00";
        String laststartTime = getPastDate(7,DateUtil.strToDate(startTime));
        System.err.println("laststartTime: "+laststartTime);
//        System.out.println("getTomorrowLongTimes:" + getTomorrowLongTimes());
    }

    //获取当时当明日0时所剩余的 秒
    public static Long getTodayRemaidTimes() {
        Calendar cal = Calendar.getInstance();
        Long start = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Long end = cal.getTimeInMillis();
        return (end - start) / 1000;
    }

    /**
     * 获取当前时间，距离当天凌晨还有多少秒
     */
    public static Long lingchenTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long timeout = ((calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000);
        return timeout;// 当前时间距离当天的零点 有多少秒
    }

    /***
     * 传入String格式日期格式化并返回日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String date) {
        Date data = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            data = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String yesterdayDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }


    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description:(日期比较，如果s>=e 返回true 否则返回false)
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }

        if (fomatDate(s).getTime() >= fomatDate(e).getTime()) {
            return true;
        } else {
            return false;
        }
        //	return
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDateMiao(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * <li>功能描述：两个String 时间相减得到多少秒
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getIntMiao(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * <li>功能描述：两个Date时间相减得到多少秒
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDateMiao(Date beginDateStr, Date endDateStr) {
        long day = 0;
        day = (endDateStr.getTime() - beginDateStr.getTime()) / (1000);
        return day;
    }

    /**
     * 得到n天之后的日期 是 string时间戳的格式
     *
     * @param days
     * @return
     */
    public static String getDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); //   日期减 如果不够减会将月变动
        Long date = canlendar.getTime().getTime();
        return date.toString();
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之前的日期
     *
     * @param days
     * @return
     */
    public static String getBeforeDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        String dateStr = sdfDays.format(date);

        return dateStr;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(today);
        return result;
    }


    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateString(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 按照yyyy-MM-dd的格式，字符串转日期
     *
     * @param date
     * @return
     */
    public static Date strDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        } else {
            return null;
        }
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     *
     * @param date
     * @return
     */
    public static Date str2Date(String date) {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        } else {
            return null;
        }
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (null == format) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 按照参数format的格式，日期转字符串,精确到秒
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateMiao(Date date, String format) {
        if (null == format) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 把时间根据时、分、秒转换为时间段
     *
     * @param StrDate
     */
    public static String getTimes(String StrDate) {
        String resultTimes = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now;

        try {
            now = new Date();
            Date date = df.parse(StrDate);
            long times = now.getTime() - date.getTime();
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            StringBuffer sb = new StringBuffer();
            // sb.append("发表于：");
            if (day > 0) {
                sb.append(day + "天前");
            } else if (hour > 0) {
                sb.append(hour + "小时前");
            } else if (min > 0) {
                sb.append(min + "分钟前");
            } else {
                sb.append(sec + "秒前");
            }

            resultTimes = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultTimes;
    }

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /*
    时间相加，一个日期，加上一个具体的天数
     */
    public static String xiangjiaDate(String LocalDateTime, String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = sdf.parse(LocalDateTime);
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.add(Calendar.DATE, Integer.valueOf(day));
            String temp = "";
            temp = sdf.format(cl.getTime());
            return temp;
        } catch (ParseException e) {
            //Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
    LocalDateTime 转String
     */
    public static String timeZhuanHuan(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String localTime = df.format(localDateTime);
        return localTime;
    }

    public static Integer computingTime(Date small) {
        long before = System.currentTimeMillis();
        long after = small.getTime();
        if (before > after) {
            long mistake = before - after;
            Double mist = new Double(mistake);
            return (int) Math.ceil(mist / 1000 / 60);
        } else {
            return null;
        }

    }

    public static String todayTime() {
        Date date = new Date();//获取今天的时间。
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    public static String yesterdayTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        String yesterday = sdf.format(time);//昨天时间。
        return yesterday;
    }

    //获取三天前的时间
    public static String threeDayAgo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Date time = cal.getTime();
        String threeDayAgo = sdf.format(time);//两天前的时间。
        return threeDayAgo;
    }


	/*
	String 类型时间的 - -  转换成/
	 */
	/*public static  String  str3String(String date){

		date = "yyyy/MM/dd HH:mm:ss";
		return date;
	}*/

    public static String formatDate(Date date) {
        String format = "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date modifyTimeByDay(Date date, int day){
        // 创建Calendar 的实例
        Calendar calendar = Calendar.getInstance();
        // 当前时间减去一天，即一天前的时间
        calendar.add(Calendar.DAY_OF_MONTH,day);
        return  calendar.getTime();
    }
}
