package com.ey.bbms.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.bbms.services.interfaces.ISessionService;

public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private ISessionService sessionService;

    public ISessionService getSessionService() {
	return sessionService;
    }

    public void setSessionService(ISessionService sessionService) {
	this.sessionService = sessionService;
    }

    public String getStringFromHttpRequest(HttpServletRequest request) throws Exception {

	InputStream is = request.getInputStream();
	String str = "";

	if (is != null) {
	    Writer writer = new StringWriter();

	    char[] buffer = new char[1024];
	    try {
		Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		int n;
		while ((n = reader.read(buffer)) != -1) {
		    writer.write(buffer, 0, n);
		}
	    } finally {
		is.close();
	    }
	    str = writer.toString();
	} else {
	    str = "";
	}
	return str;
    }

    public String convertDateFormate(String dt) {// 25-01-2016 25 01 2016 output
						 // 2016-01-25
	String formatedDate = "";

	String[] splitdt = dt.split("-");
	formatedDate = splitdt[2] + "-" + splitdt[1] + "-" + splitdt[0];

	return formatedDate;

    }

    public Integer getDay(String dt) {// 25-01-2016 01 output 01
	String formatedDate = "";

	String[] splitdt = dt.split("-");
	formatedDate = splitdt[0];

	int day = Integer.parseInt(formatedDate);

	return day;

    }

    public Integer getMonth(String dt) {// 25-01-2016 01 output 01
	String formatedDate = "";

	String[] splitdt = dt.split("-");
	formatedDate = splitdt[1];

	int mon = Integer.parseInt(formatedDate);

	return mon;

    }

    public Integer getYear(String dt) {// 25-01-2016 2016 output 2016
	String formatedDate = "";

	String[] splitdt = dt.split("-");
	formatedDate = splitdt[2];

	int year = Integer.parseInt(formatedDate);

	return year;

    }

    public String changetDateFormate(String dt) {// 4/15/2016 4 15 2016 output
						 // 2016-04-15
	String formatedDate = "";

	String[] splitdt = dt.split("/");
	formatedDate = splitdt[2] + "-" + splitdt[0] + "-" + splitdt[1];

	return formatedDate;

    }

    public String changetTimeFormate(String dt) {// 9:41:32 PM 9 41 32 output
						 // 9:41:32
	String formatedTime = "";

	String[] splitt1 = dt.trim().split(" ");
	String[] splitt2 = splitt1[0].split(":");

	String houre = splitt2[0];
	String minite = splitt2[1];
	String secend = splitt2[2];

	if (houre.equalsIgnoreCase("PM")) {
	    int inhoure = Integer.parseInt(houre);
	    inhoure = inhoure + 12;
	    houre = String.valueOf(inhoure);
	}

	formatedTime = houre + ":" + minite + ":" + secend;

	return formatedTime;

    }

    protected SimpleDateFormat DateFormateforView() {
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	return df;
    }

    protected SimpleDateFormat DateFormateforDB() {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	return df;
    }

    protected String getFormatedDate(Date dd) {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String dt = df.format(dd);
	return dt;
    }

    protected SimpleDateFormat DateTimeFormateforView() {
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	return df;
    }

    protected SimpleDateFormat DateTimeFormateforDB() {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return df;
    }

    protected String getFormatedTime(Date dd, String inout, String tt) {
	String dt = "";
	if (inout.equals("0")) {
	    final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
	    Calendar date = Calendar.getInstance();
	    date.setTime(dd);
	    long t = date.getTimeInMillis();
	    int timeAdjust = Integer.parseInt(tt);
	    Date afterMinusMins = new Date(t - (timeAdjust * ONE_MINUTE_IN_MILLIS));

	    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	    dt = df.format(afterMinusMins);
	} else {
	    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	    dt = df.format(dd);
	}
	return dt;
    }

    protected String getFormatedTime(Date dd) {
	String dt = "";

	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	dt = df.format(dd);

	return dt;
    }

    protected String getFormatedNumber(String n) {
	String num = "00";
	if (n.equals("1"))
	    num = "01";
	else if (n.equals("2"))
	    num = "02";
	else if (n.equals("3"))
	    num = "03";
	else if (n.equals("4"))
	    num = "04";
	else if (n.equals("5"))
	    num = "05";
	else if (n.equals("6"))
	    num = "06";
	else if (n.equals("7"))
	    num = "07";
	else if (n.equals("8"))
	    num = "08";
	else if (n.equals("9"))
	    num = "09";
	else if (n.equals("0"))
	    num = "00";
	else
	    num = n;
	return num;
    }

    protected boolean findFutureDate(String date) {
	boolean fo = false;

	String[] splitdt = date.split("-");
	int yy = 0, mm = 0, dd = 0;
	if (!splitdt[0].equals(""))
	    dd = Integer.parseInt(splitdt[0]);
	if (!splitdt[1].equals(""))
	    mm = Integer.parseInt(splitdt[1]);
	mm = mm - 1;
	if (!splitdt[2].equals(""))
	    yy = Integer.parseInt(splitdt[2]);
	// create calendar objects.
	Calendar cal = Calendar.getInstance();
	Calendar future = Calendar.getInstance();

	// print the current date
	// System.out.println("Current date: " + cal.getTime());

	// change date in future calendar
	future.set(yy, mm, dd);
	// System.out.println("Year is " + future.get(Calendar.YEAR));

	// check if calendar date is after current date
	Date time = future.getTime();
	if (future.after(cal)) {
	    // System.out.println("Date " + time + " is after current date.");
	    fo = true;
	}

	return fo;
    }

    public String ConvertDateToJP(String date) {
	String jpdate = "";
	String ar[] = date.split("-");
	if (ar[2] != null) {
	    long cyear = Long.parseLong(ar[2]);
	    long i = cyear - 1988;
	    int mon = Integer.parseInt(ar[1]);
	    int day = Integer.parseInt(ar[0]);

	    jpdate = "平成" + i + "年" + mon + "月" + day + "日";
	}
	return jpdate;
    }

    public String ConvertCDateToJP() {
	String datePattern = "dd-MM-yyyy";
	SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	String date = dateFormatter.format(new Date());
	String jpdate = "";
	String ar[] = date.split("-");
	if (ar[2] != null) {
	    long cyear = Long.parseLong(ar[2]);
	    long i = cyear - 1988;
	    int mon = Integer.parseInt(ar[1]);
	    int day = Integer.parseInt(ar[0]);

	    jpdate = i + "." + mon + "." + day;
	}
	return jpdate;
    }

}
