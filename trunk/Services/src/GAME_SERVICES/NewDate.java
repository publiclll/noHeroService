package GAME_SERVICES;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NewDate extends Date {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Calendar c = Calendar.getInstance();

	public static final long TIME_MILLISECOND = 1;

	public static final long TIME_SECOND = 1000 * TIME_MILLISECOND;

	public static final long TIME_MINUTE = 60 * TIME_SECOND;

	public static final long TIME_HOUR = 60 * TIME_MINUTE;

	public static final long TIME_DAY = 24 * TIME_HOUR;

	public static final long TIME_WEEK = 7 * TIME_DAY;

	public NewDate() {
		super();
	}

	public NewDate(Calendar c) {
		this.c = c;
		setTime(c.getTime().getTime());
	}
	
	@SuppressWarnings("deprecation")
	public NewDate(String str) {
		super(str);
	}
	
	public NewDate(long ms) {
		super(ms);
	}

	public NewDate(Date d) {
		super(d.getTime());
	}

	public NewDate(NewDate d) {
		super(d.getTime());
	}

	public NewDate(java.sql.Date d) {
		super(d.getTime());
	}

	public NewDate(java.sql.Timestamp ts) {
		super(ts.getTime());
	}

	public NewDate(int year, int month, int day) {
		this(year, month, day, 0, 0, 0, 0);
	}

	public NewDate(int year, int month, int day, int hour, int minute, int second) {
		this(year, month, day, hour, minute, second, 0);
	}

	public NewDate(int year, int month, int day, int hour, int minute, int second, int millis) {
		c.set(year, month - 1, day, hour, minute, second);
		setDate(c.getTime());
	}

	public void setCalendar(Calendar c2) {
		c = c2;
	}

	public void setLocale(TimeZone zone, Locale locale) {
		c = Calendar.getInstance(locale);
	}

	public NewDate addWeek(int w) {
		return addMillis(w * TIME_WEEK);
	}

	public NewDate addDay(int d) {
		return addMillis(d * TIME_DAY);
	}

	public NewDate addHour(int h) {
		return addMillis(h * TIME_HOUR);
	}

	public NewDate addMinute(int m) {
		return addMillis(m * TIME_MINUTE);
	}

	public NewDate addSecond(int s) {
		return addMillis(s * TIME_SECOND);
	}

	public NewDate addMillis(long t) {
		setTime(getTime() + t);
		return this;
	}

	public String fmt_yyyyMMddHHmmss() {
		String fmt = "yyyy-MM-dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(fmt);
		return dateFormat.format(this);
	}


	
	public int getYearMonthDay() {
		return 0;
	}

	// 与当前时间差
	public long diffCurrentTime() {
		return System.currentTimeMillis() - getTime();
	}

	// 时间�?
	public long difference(Date dat2) {
		return dat2.getTime() - getTime();
	}

	public NewDate setYearMonthDay(int year, int month, int day) {
		c.setTime(this);
		c.set(year, month - 1, day);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public NewDate setHourMinuteSec(int hour, int min, int sec) {
		c.setTime(this);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, sec);
		c.set(Calendar.MILLISECOND, 0);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public int getYear() {
		c.setTime(this);
		return c.get(Calendar.YEAR);
	}

	public NewDate setYear2(int year) {
		c.setTime(this);
		c.set(Calendar.YEAR, year);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public int getMonth() {
		c.setTime(this);
		return c.get(Calendar.MONTH);
	}

	public NewDate setMonth2(int month) {
		c.setTime(this);
		c.set(Calendar.MONTH, month - 1);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public int getWeekOfYear() {
		c.setTime(this);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	public int getWeekOfMonth() {
		c.setTime(this);
		return c.get(Calendar.WEEK_OF_MONTH);
	}

	public int getDayOfWeek() {
		c.setTime(this);
		return c.get(Calendar.DAY_OF_WEEK) - 1; // 中国习惯是周�?��1。欧洲习惯周�?周一2
	}

	public int getDay() {
		c.setTime(this);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public NewDate setDay(int day) {
		c.setTime(this);
		c.set(Calendar.DAY_OF_MONTH, day);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public int getHour() {
		c.setTime(this);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public NewDate setHour(int hour) {
		c.setTime(this);
		c.set(Calendar.HOUR_OF_DAY, hour);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public int getMinute() {
		c.setTime(this);
		return c.get(Calendar.MINUTE);
	}

	public NewDate setMinute(int minute) {
		c.setTime(this);
		c.set(Calendar.MINUTE, minute);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public int getSecound() {
		c.setTime(this);
		return c.get(Calendar.SECOND);
	}

	public NewDate setSecound(int secound) {
		c.setTime(this);
		c.set(Calendar.SECOND, secound);
		this.setTime(c.getTimeInMillis());
		return this;
	}

	public void setDate(Date d) {
		this.setTime(d.getTime());
	}

	public void setDate(java.sql.Date d) {
		this.setTime(d.getTime());
	}

	public void setTimestamp(java.sql.Timestamp ts) {
		this.setTime(ts.getTime());
	}

	public long dateDiffIt(Date d) {
		return msDiffIt(d.getTime());
	}

	public long msDiffIt(long ms) {
		return getTime() - ms;
	}

	public long nowDiffIt() {
		return System.currentTimeMillis() - getTime();
	}

	public int maxDayOfMonth() {
		c.setTime(this);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public boolean in(NewDate min, NewDate max) {
		return this.after(min) && this.before(max);
	}
	
	public boolean in(Date min, Date max) {
		return this.after(min) && this.before(max);
	}

	public boolean notIn(Date min, Date max) {
		return this.before(min) || this.after(max);
	}

	public String toString() {
		return fmt_yyyyMMddHHmmss();
	}

	// 工作�?
	public boolean workDay() {
		int dow = getDayOfWeek();
		return (dow >= 1 && dow <= 5);
	}

	// 周末
	public boolean weekend() {
		return !(workDay());
	}

	// 工作时间(�?�?)
	public boolean workTime9to18() {
		int h = getHour();
		return (h >= 9 && h < 18);
	}

	// 工作时间�?
	public boolean noWorkTime() {
		return !(workTime9to18());
	}

	public NewDate create() {
		return new NewDate(this);
	}


//	/**
//	 * 是否是今�?
//	 * 
//	 * @return
//	 * @throws ParseException 
//	 */
//	public boolean isNowDay() throws ParseException {
//		NewDate today = new NewDate().parseFmt(DateEx.fmt_yyyy_MM_dd);
//		NewDate tom = new NewDate().addMillis(TIME_DAY);
//		return in(today, tom);
//	}
	
	
	
	/**
	 * 是否是昨天以�?
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public boolean isBeforeYesterDay() throws ParseException {
		NewDate yes = new NewDate().addMillis(-TIME_DAY);
		return before(yes);
	}
}
