package leetcode_inta.exam_main.leetcode_exam2020.D177;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author inta
 * @date 2020/2/23
 * @describe
 */
public class Q1 {

    private int[] mon = { 0,31,59,90,120,151,181,212,243,273,304,334 };//字典
    private int cal(int y, int m, int d)//给出年月日，计算距离0000年0月1日的天数和
    {
        if(((y % 100 != 0 && y % 4 == 0) || y % 400 == 0) && m > 2) d ++;
        return y * 365 + y / 4 - y / 100 + y / 400 + mon[m - 1] + d;//闰年，大于2月需要再加一天
    }

    public int daysBetweenDates(String date1, String date2) {
        String[] d1 = date1.split("-");
        String[] d2 = date2.split("-");
        int day1 = cal(Integer.valueOf(d1[0]), Integer.valueOf(d1[1]), Integer.valueOf(d1[2]));
        int day2 = cal(Integer.valueOf(d2[0]), Integer.valueOf(d2[1]), Integer.valueOf(d2[2]));
        return Math.abs(day1 - day2);
    }

    public int daysBetweenDates2(String date1, String date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int res = 0;
        try {
            Date d1 = simpleDateFormat.parse(date1);
            Date d2 = simpleDateFormat.parse(date2);
            res = (int)(Math.abs(d1.getTime() - d2.getTime()) / (3600 * 1000 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int daysBetweenDates3(String date1, String date2) {
        LocalDate localDate = LocalDate.parse(date1);
        LocalDate localDate2 = LocalDate.parse(date2);
        Long days = ChronoUnit.DAYS.between(localDate, localDate2);
        return (int) Math.abs(days);
    }
}
