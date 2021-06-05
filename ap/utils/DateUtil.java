
package ap.utils; 

import java.sql.Timestamp;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.time.Instant; 
import java.time.LocalDate; 
import java.time.ZoneId; 
import java.time.temporal.ChronoUnit; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar; 
import java.util.Date; 
import java.util.GregorianCalendar; 
import java.util.List; 
import ap.explorer.Range;
import ap.global.gl;  

public class DateUtil { 


	public static final int  MONDAY 	= 2; 
	 
	public static final int  TUESDAY 	= 3; 
	 
	public static final int  WEDNESDAY 	= 4; 
	 
	public static final int  THURSDAY 	= 5; 
	 
	public static final int  FRIDAY 	= 6; 
	 
	public static String DATE_DEFAULT = "--.--.----"; 
	 
		 
	public static long years_between(Date a, Date b) 
	{ 
		return ChronoUnit.YEARS.between(local_date(a),local_date(b)); 
	} 
	 
	public static long days_between(Range range) 
	{ 
		return days_between(range.getDate_from(),range.getDate_to()); 
	} 
	 
	public static long days_between(Date a, Date b) 
	{ 
		 
		if(b==null) 
			return gl.E_ERROR; 
		 
		return ChronoUnit.DAYS.between(local_date(a),local_date(b)); 
	} 
	 
	public static int equals(Date left,Date right) 
	{ 
		return left.compareTo(right); 
	} 
	 
	public static boolean is_equals(Date left,Date right) 
	{ 
		return (equals(left,right)==gl.E_EMPTY); 
	} 
	
	public static boolean is_null(Date left) 
	{ 
		return (equals(left,to_date(DATE_DEFAULT))==gl.E_EMPTY); 
	} 
	
	
	public static boolean date_is_in_range(Date target,Date from , Date to) 
	{ 
		 
		return (target.after(from) && target.before(to)); 
	} 
	 
	 
	public static boolean isa_normal_date_format(String value) 
	{ 
		Date c = to_date(value); 
		 
		if(DateUtil.date_is_in_range(c,to_date("01.01.1899"),to_date("01.01.2050"))) 
			return true; 

			return false; 
	} 
	 
	 
	public static boolean is_fibo_date(String value) 
	{ 
		Date c = to_date_from_fibo_string(value); 
		 
		if(DateUtil.date_is_in_range(c,to_date("01.01.1899"),to_date("01.01.2020"))) 
			return true; 

			return false; 
		 
	} 
	 
	public static boolean in_range(Date date,Date begin_date,Date end_date) 
	{ 
		 
		return  ( 
				 
				( date.compareTo(begin_date) == gl.E_EMPTY || date.after(begin_date)) && 
			    ( date.compareTo(end_date) == gl.E_EMPTY || date.before(end_date)) 
			 
				 ); 
	 
	} 
	 
	public static boolean isa_date_is_in_range(Date date,Range range) 
	{ 
		 return in_range(date,range.getDate_from(),range.getDate_to()); 
	} 
	 
	 
	public static  LocalDate local_date(java.util.Date date) 
	{ 
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(); 
	} 
	 
	public static  Date as_date(LocalDate date) 
	{ 
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()); 
	} 
	 
	public static java.time.DayOfWeek dow(java.util.Date date) { 
		 
		LocalDate ld = DateUtil.local_date(date); 
		 
		return ld.getDayOfWeek(); 
	} 
	 
	public static int day_of_year(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.DAY_OF_YEAR); 
	} 
	 
	 
	 
	public static int day_of_week(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.DAY_OF_WEEK); 
	} 
	 
	public static int day_of_month(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.DAY_OF_MONTH); 
	} 

	public static int year(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.YEAR); 
	} 

	 
	 
	public static int week_of_month(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.WEEK_OF_MONTH); 
	} 
	 
	public static int week_of_year(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.WEEK_OF_YEAR); 
	} 
	 
	public static int month_of_year(java.util.Date date) { 
		 
		Calendar cl = Calendar.getInstance(); 
			 
		cl.setTime(date); 
		 
		return cl.get(Calendar.MONTH); 
	} 
	 
	public static int quartal(java.util.Date date) { 
		 
		int m_onth = month_of_year(date); 
		 
		int month = m_onth + 1; 
				 
		if(month == 1 ||  month ==2 ||  month == 3) 
			return 1; 
		 
		if(month == 4 ||  month ==5 ||  month == 6) 
			return 2; 
		 
		if(month == 7 ||  month ==8 ||  month == 9) 
			return 3; 
		 
		if(month == 10 ||  month ==11 ||  month == 12) 
			return 4; 
		 
		 
			return gl.E_ERROR; 
		 
			 
	} 
	 
	 
	public static List<Range> year_series(Date from,Date to) 
	{ 
		 
		String year_from = String.format("01.01.%s", "%d"); 
		 
		String year_to   = String.format("31.12.%s", "%d"); 
		 
		int start_year = year(from); 
		 
		int year_period = (year(to) - start_year)+1; 
		 
		List<Range> list = new ArrayList<Range> (); 
		 
		 
		for(int i=0;i<year_period;i++) 
		{ 
			String a = String.format(year_from,start_year); 
			 
			String b = String.format(year_to,start_year); 
			 
			Range td = new Range(to_date(a),to_date(b)); 
			 
			list.add(td); 
			 
			start_year++; 
			 
		} 
		 
			return list; 
	} 
	 
	 
	public static void testDaysBetween(String a , String b) 
	{ 
		gl.smn(String.format("Days between %s and %s  : %03d",a,b, 
			    days_between(to_date(a),to_date(b))) 
			  ); 
		 
	} 
	 
	public static void testYearsBetween(String a , String b) 
	{ 
		gl.smn(String.format("Years between %s and %s  : %03d",a,b, 
			    years_between(to_date(a),to_date(b))) 
			  ); 
		 
	} 
	 
	public static void test_getDateYearSeries(String a , String b) 
	{ 
		 
		year_series(to_date(a),to_date(b)).forEach( 
				c-> 
				{ 
					gl.smn(c.toString()); 
				} 
				); 
		 
	} 
	 
	public static Date add_days(Date input,int days) 
	{ 
		LocalDate ld = local_date(input); 
		 
		return as_date(ld.plusDays(days)); 
		 
	} 
	 
	public static Date minus_days(Date input,int days) 
	{ 
		LocalDate ld = local_date(input); 
		 
		return as_date(ld.minusDays(days)); 
		 
	} 
	 

	public static String date_time_stamp() 
	{ 

		Calendar now = GregorianCalendar.getInstance(); 

		java.util.Date creation_date = now.getTime(); 
		 
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); 

		String date_time_string = df.format(creation_date); 
		 
		return date_time_string; 

	} 
	 
	public static String date_time_stamp_file(long msc) 
	{ 

		Date dt = new Date(msc); 
		 
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); 

		String date_time_string = df.format(dt); 
		 
		return date_time_string; 

	} 
	 
	public static String get_date_stamp_now(String fmt) 
	{ 

		Calendar now = GregorianCalendar.getInstance(); 

		java.util.Date creation_date = now.getTime(); 
		 
		SimpleDateFormat df = new SimpleDateFormat(String.format("dd%sMM%syyyy",fmt,fmt)); 

		String date_time_string = df.format(creation_date); 
		 
		return date_time_string; 

	} 

	public static String get_stamp_for_zip() 
	{ 

		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		return sdf.format(timestamp);
		
	} 
	
	public static String to_string(Date dt) 
	{ 
		if (dt == null) 
			return DATE_DEFAULT; 
		 
		return to_string(dt,"."); 
	} 
	 
	public static String to_string(Date dt, String fmt) 
	{ 

		SimpleDateFormat df = new SimpleDateFormat(String.format("dd%sMM%syyyy",fmt,fmt)); 

		String date_time_string = df.format(dt); 
		 
		return date_time_string; 

	} 

	public static Date to_date_from_datetime(String value_date) 
	{ 
		return to_date(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"),value_date); 
	} 
	 
	 
	public static Date to_date(String value_date) 
	{ 
		 
		return to_date(new SimpleDateFormat("dd.MM.yyyy"),value_date); 
		 
	} 
	 
	 
	public static Date to_date_from_fibo_string(String value_date) 
	{ 
		 
		return to_date(new SimpleDateFormat("yyyy.MM.dd"),value_date); 
		 
	} 
	 
	public static Date to_date(SimpleDateFormat sdf, String value_date) 
	{ 
		Date dt = null; 
		 
		try { 
			dt = sdf.parse(value_date); 
		} catch (ParseException e) { 
			 
			return null; 
		} 
		 
			return dt; 
	} 
	 
	public static String get_date_stamp_now() 
	{ 
		return get_date_stamp_now("_"); 
	} 
	 
	public static String getDateStampFile(long msc) 
	{ 
		return stamp_file(msc,"_"); 
	} 
	 
	 
	public static Date date_from_file_stamp(long msc) 
	{ 
		Date dt = new Date(msc); 
		 
		return dt; 
	} 
	 
	public static String stamp_file(long msc,String fmt) 
	{ 

		Date dt = new Date(msc); 
		 
		SimpleDateFormat df = new SimpleDateFormat(String.format("dd%sMM%syyyy",fmt,fmt)); 

		String date_time_string = df.format(dt); 
		 
		return date_time_string; 

	} 
	 
	public static boolean in_range(Date date,String begin_date,String end_date) 
	{ 
		Date begin = to_date(begin_date); 
		 
		Date end = to_date(end_date); 
		 
		return  in_range(date,begin,end); 
		 
	} 
	 
	 
	 
	public static void test_add_minus_Date(String a) 
	{ 
		 
		 
		gl.smn(String.format("Input : %s add 2 :%s minus 3 : %s ", 
				 
				to_string(to_date(a)), 
				to_string(DateUtil.add_days(to_date(a),2)), 
				to_string(DateUtil.minus_days(to_date(a),3)) 
				 
				)); 
				 
				 
	} 
	 
	public static void test_date_in_range(String [] dates,Range range) 
	{ 
		 
		 Arrays.asList(dates).forEach(d->{
			 
			 gl.tx(new Object(){},DateUtil.isa_date_is_in_range(DateUtil.to_date(d), range),
					 gl.sf("Тестирование даты...[%s]...в диапазоне...[%s]",d,range.toString()));

		 });		 
				 
	} 
	
	public static void main(String[] args) { 

		/* 
		testDaysBetween("01.01.2016" ,"10.01.2017"); 
		 
		testDaysBetween("10.01.2016" ,"01.01.2017"); 
		 
		testDaysBetween("01.01.2017" ,"01.01.2017"); 

		gl.smn("Year : " + (getYear(gl.getDateFromString("03.03.1941"))+1) ); 
		 
		*/ 
		 
		//test_getDateYearSeries("01.01.2010" ,"10.01.2015") ; 
		 
		//test_add_minus_Date("01.01.2010"); 
		 
		//testDaysBetween("06.02.2019" ,"14.12.2018"); 
		
		test_date_in_range(new String []{"01.02.1998","01.07.1998","01.03.1998"},Range.get_instance("01.02.1998","01.03.1998")); 
		 
	} 

} 
// Revision : 10.09.2018 12:49:16 
