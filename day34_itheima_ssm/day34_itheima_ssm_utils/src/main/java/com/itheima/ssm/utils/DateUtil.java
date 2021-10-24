package com.itheima.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //日期转换成字符串
    public static String Date2String(Date d_date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String s_date = sdf.format(d_date);
        return s_date;
    }

    //字符串转换成日期
    public static Date String2Date(String s_date,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d_date = sdf.parse(s_date);
        return d_date;
    }

}
