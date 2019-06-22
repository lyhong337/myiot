package com.chinaunicom.myiot.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date parse(String source, String pattern) {
        if (source == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("使用模式 " + pattern + " 无法解析字符串 " + source + " 生成日期", e);
        }
    }

    
    public static String getYYYY_MM_DD(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return year + "-" + month + "-" + day;
    }

    
    public static String getMM_DD(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return month + "-" + day;
    }

    
    public static String getMMyueDDri(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return month + "月" + day + "日";
    }

    
    public static String getYYYYnianMMyueDDri(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }

    
    public static String getChineseYYYYnianMMyueDDri(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String chineseYear = StringUtils.getStringNumber(year);
        String chineseMonth = StringUtils.getChineseNumber(month);
        String chineseDay = StringUtils.getChineseNumber(day);
        return chineseYear + "年" + chineseMonth + "月" + chineseDay + "日";
    }

    
    public static String getYYYY_MM_DD_HH_MM_SS(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toString();
    }

    
    public static String getMMyueDDri_HH_MM(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return month + "月" + day + "日" + " " + hour + ":" + minute;
    }

    
    public static String getHH_MM(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String strHour = (hour < 10 ? "0" + hour : "" + hour);
        String strMinute = (minute < 10 ? "0" + minute : "" + minute);

        return strHour + ":" + strMinute;
    }

    
    public static String getYYYY_MM_DD(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toString().substring(0, 10);
    }

    
    public static java.sql.Date getSqlDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        return java.sql.Date.valueOf(strDate);
    }

    
    public static java.sql.Timestamp getSqlTimestamp(String strDate) {
        if (strDate == null) {
            return null;
        }
        java.sql.Date date = java.sql.Date.valueOf(strDate);
        return new java.sql.Timestamp(date.getTime());
    }

    
    public static java.sql.Timestamp getSqlTimestampHHMMSS(String strDate) {
        if (strDate == null) {
            return null;
        }
        java.sql.Timestamp time = java.sql.Timestamp.valueOf(strDate);
        return time;
    }

    
    public static java.sql.Date getCurrentSqlDate() {
        Calendar calendar = Calendar.getInstance();

        return new java.sql.Date(calendar.getTime().getTime()); 
    }

    
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        return new Date(calendar.getTime().getTime()); 
    }

    
    public static java.sql.Timestamp getCurrentSqlTimestamp() {
        Calendar calendar = Calendar.getInstance();

        return new java.sql.Timestamp(calendar.getTimeInMillis()); 
    }

    
    public static Date getDate(String strDate, String format) {
        if (strDate == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }

    
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    }

    
    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    
    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    
    public static int getDayNumber() {





        return DateUtils.getDay() == 1 ? 3 : 1;
    }

    
    public static String getYYYYMMDDHHMMSS(Date date) {
        if (date == null) {
            return "";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String strYear = null;
        if (year >= 1000) {
            strYear = "" + year;
        } else if (year >= 100) {
            strYear = "0" + year;
        } else if (year >= 10) {
            strYear = "00" + year;
        } else if (year >= 0) {
            strYear = "000" + year;
        } else {
            
            strYear = "" + year;
        }
        String strMonth = (month >= 10 ? "" + month : "0" + month);
        String strDay = (day >= 10 ? "" + day : "0" + day);
        String strHour = (hour >= 10 ? "" + hour : "0" + hour);
        String strMinute = (minute >= 10 ? "" + minute : "0" + minute);
        String strSecond = (second >= 10 ? "" + second : "0" + second);

        return strYear + strMonth + strDay + strHour + strMinute + strSecond;
    }

    
    public static String getDateAsString(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    
    public static String getFirstDayOfWeek(String date) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String resulte = "";
        if (date == null || date.trim().length() == 0) {
            return resulte;
        } else {
            try {
                Date oldDate = myFormatter.parse(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setFirstDayOfWeek(Calendar.MONDAY);
                calendar.setTime(oldDate);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                resulte = myFormatter.format(calendar.getTime());
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
        return resulte;
    }

    
    public static Date getCurrentWeekStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(startDate);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (Exception ex) {
            throw new RuntimeException("获得本周的开始日期(星期一的日期)出错:", ex);
        }

        return startDate;
    }

    
    public static Date getCurrentMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String startDate = year + "-" + month + "-01";

        return getDate(startDate, "yyyy-MM-dd");
    }

    
    public static Date getCurrentYearStartDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String startDate = year + "-01" + "-01";

        return getDate(startDate, "yyyy-MM-dd");
    }



















    
    public static Date getBeginDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    
    public static Date getEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    
    public static int getIntYYYYMMDD(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return (year * 10000 + month * 100 + day);
    }

    
    public static int getIntHHMMSSSSS(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        return (hour * 10000000 + minute * 100000 + second * 1000 + millisecond);
    }

    
    public static Date getDateFromInt(int yyyymmdd, int hhmmsssss) {
        int year = yyyymmdd / 10000;
        int month = (yyyymmdd - year * 10000) / 100;
        int day = yyyymmdd % 100;
        int hour = hhmmsssss / 10000000;
        int minute = (hhmmsssss - hour * 10000000) / 100000;
        int second = (hhmmsssss - hour * 10000000 - minute * 100000) / 1000;
        int millisecond = hhmmsssss % 1000;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return calendar.getTime();
    }

    
    public static String getChineseTimeConsuming(long elapsedTime) {
        int day = (int) (elapsedTime / 1000 / 60 / 60 / 24);
        int hour = (int) ((elapsedTime % (1000 * 60 * 60 * 24)) / 1000 / 60 / 60);
        int minute = (int) ((elapsedTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60)) / 1000 / 60);
        int second = (int) ((elapsedTime / 1000) % 60);
        int millisecond = (int) (elapsedTime % 1000);

        StringBuilder sb = new StringBuilder();
        if (elapsedTime > 0) {
            if (day > 0) {
                sb.append(day).append("天");
            }
            if (hour > 0) {
                sb.append(hour).append("小时");
            }
            if (minute > 0) {
                sb.append(minute).append("分");
            }
            if (second > 0) {
                sb.append(second).append("秒");
            }
            if (millisecond > 0) {
                sb.append(millisecond).append("毫秒");
            }
        } else {
            sb.append("0秒");
        }

        return sb.toString();
    }

    
    public static String getChineseTimeConsuming(Date beginTime, Date endTime) {
        if (beginTime.after(endTime)) {
            Date tmp = beginTime;
            beginTime = endTime;
            endTime = tmp;
        }

        
        long elapsedTime = endTime.getTime() - beginTime.getTime();
        return getChineseTimeConsuming(elapsedTime);
    }

    
    public static int calculateAge(Date birthday, Date currentDate) {
        
        boolean negative = false;
        if (birthday.after(currentDate)) {
            
            Date tmp = birthday;
            birthday = currentDate;
            currentDate = tmp;
            negative = true;
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(birthday);
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int date1 = cal1.get(Calendar.DATE);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(currentDate);
        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int date2 = cal2.get(Calendar.DATE);

        int age = 0;
        if (month1 > month2 || (month1 == month2 && date1 > date2)) {
            age = year2 - year1 - 1;
        } else {
            age = year2 - year1;
        }

        return negative ? 0 - age : age;
    }

    
    public static int compareDate(Date startDay, Date endDay, int stype) {
        if (startDay == null || endDay == null) {
            return 0;
        }

        String startDayStr = DateUtils.format(startDay, "yyyy-MM-dd");
        String endDayStr = DateUtils.format(endDay, "yyyy-MM-dd");
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(startDayStr));
            c2.setTime(df.parse(endDayStr));
        } catch (Exception e) {
            System.out.println("wrong occured");
        }

        
        int n = 0;
        while (!c1.after(c2)) {
            n++;
            if (stype == 1) {
                
                c1.add(Calendar.MONTH, 1);
            } else {
                
                c1.add(Calendar.DATE, 1);
            }
        }
        n = n - 1;
        if (stype == 2) {
            n = (int) n / 365; 
        }
        String[] u = {"天", "月", "年"};
        System.out.println(startDay + " -- " + endDay + " 相差多少" + u[stype] + ":" + n);

        return n;
    }

	/*
	 * 将Date类型按一定格式转换成String
	 * date Date Date类型的变量
	 * format String 一定格式的字符串。例如：yyyyMMdd,yyyy-MM-dd,yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date date,String format)
	{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/*
	 * 取得一定格式的当前日期
	 * str String 所取日期的格式。例如：yyyyMMdd,yyyy-MM-dd,yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDate(String str)
	{
		SimpleDateFormat tempDate = new SimpleDateFormat(str);
		String datetime = tempDate.format(new Date());
		return datetime;
	}
	/*
	 * 计算n日之后的日期
	 * 输入：date
	 * 输出：str形式的字符串日期
	*/
	public static String getnDay(Date date,int n,String fmt) throws Exception{
		long l = date.getTime();
	    Date d = new Date(l+1000*60*60*24*n);
		SimpleDateFormat sdf=new SimpleDateFormat(fmt);
		String dateString=sdf.format(d);
		return dateString;
	} 
	/*
	 * 取得当前日期,类型是yyyyMMdd
	 * return String 日期字符串
	 * */
    public static String getCurrentDate1()
    {
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat();
    	sdf.applyPattern("yyyyMMdd");
    	String currentDate=sdf.format(date);
        return currentDate;	
    }
  //strDate --yyyyMMdd
  	public static String changeDateformat_DB2(String strDate,String format) {
  		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");// 我必须现在这儿指定一下格式
  		SimpleDateFormat format2 = new SimpleDateFormat(format);// 再在这里指定一下显示给用户的样式
  		String date = "";
  		try {
  			date = format2.format(format1.parse(strDate));
  		} catch (ParseException e) {
  			e.printStackTrace();
  		}
  		return date;
  	}

  	/*
	 * 传入日期格式字符串，计算出年龄 
	 * str String 出生日期    格式为：yyyy-MM-dd
	 * str1 String 要计算的日期    格式为：yyyy-MM-dd
	 */
	public static int getAge(String str,String str1) throws Exception 
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date birthDay=df.parse(str);//出生日期
		Date current=df.parse(str1);//要计算的日期
		Calendar cal = Calendar.getInstance();

		if (current.before(birthDay)) 
		{
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		cal.setTime(current);
		int yearNow = cal.get(Calendar.YEAR);
		//System.out.println("yearNow="+yearNow);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);// 给时间赋值
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}
		return age;
	}

	/* 转变字符串日期的样式*/
	 public static String strDateStyle(String date)
	 {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s = "";
		 if(date.length()<10){
       	 if(date.lastIndexOf("-")!=-1){
       		 s1 = date.substring(0,date.indexOf("-"));
       		 if(s1.length()<4){
       			 s1 = "20" + s1;
       		 }
       		 s2 = date.substring(date.indexOf("-")+1,date.lastIndexOf("-"));
       		 if(s2.length()<2){
       			 s2 = "0" + s2;
       		 }
       		 s3 = date.substring(date.lastIndexOf("-")+1);
       		 if(s3.length()<2){
       			 s3 = "0" + s3;
       		 }
       		 s = s1 + "-" + s2 + "-" + s3;
       	 }else if(date.lastIndexOf("\\")!=-1){
       		 s1 = date.substring(0,date.indexOf("\\"));
       		 if(s1.length()<4){
       			 s1 = "20" + s1;
       		 }
       		 s2 = date.substring(date.indexOf("\\")+1,date.lastIndexOf("\\"));
       		 if(s2.length()<2){
       			 s2 = "0" + s2;
       		 }
       		 s3 = date.substring(date.lastIndexOf("\\")+1);
       		 if(s3.length()<2){
       			 s3 = "0" + s3;
       		 }
       		 s = s1 + "-" + s2 + "-" + s3;
       	 }else if(date.lastIndexOf("/")!=-1){
       		 s1 = date.substring(0,date.indexOf("/"));
       		 if(s1.length()<4){
       			 s1 = "20" + s1;
       		 }
       		 s2 = date.substring(date.indexOf("/")+1,date.lastIndexOf("/"));
       		 if(s2.length()<2){
       			 s2 = "0" + s2;
       		 }
       		 s3 = date.substring(date.lastIndexOf("/")+1);
       		 if(s3.length()<2){
       			 s3 = "0" + s3;
       		 }
       		 s = s1 + "-" + s2 + "-" + s3;
       	 }
        }else{
       	 s = date.replace('/','-').replace('\\','-');
        }
		 return s;
	  } 
	 
	 public static String toDateC(String date)
	 {
    	 String dateC=date.substring(0,4)+"年"+date.substring(5,7)+"月"+date.substring(8,10)+"日";
    	 return dateC;
	} 
     
     public static String toDateC2(String date)
	 {
    	 String dateC=date.substring(0,4)+"年"+date.substring(4,6)+"月"+date.substring(6,8)+"日";
    	 return dateC;
	} 
     
     /**  
      * 判断当前日期是星期几<br>  
      * <br>  
      * @param pTime 修要判断的时间<br>  
      * @return dayForWeek 判断结果<br>  
      * @Exception 发生异常<br>  
      */   
  public static int dayForWeek(String pTime) throws  Exception {  
	   SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM-dd" );  
	   Calendar c = Calendar.getInstance();  
	   c.setTime(format.parse(pTime));  
	   
	   int  dayForWeek = 0 ;  
	   if (c.get(Calendar.DAY_OF_WEEK) == 1 ){  
		   dayForWeek = 7 ;  
	   }else {  
		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1 ;  
	   }  
	   return  dayForWeek;  
  }
	
}
