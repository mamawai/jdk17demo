package com.example.demo.algorithm.leetcode.每日一题;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class 一周中的第几天 {
    public static void main(String[] args) {
        String s = new Solution1185().dayOfTheWeek(31, 8, 2019);
        System.out.println(s);
    }
}

class Solution1185 {
    public String dayOfTheWeek(int day, int month, int year) {

        int dayOfYear = LocalDate.parse("2019-02-10", DateTimeFormatter.ISO_LOCAL_DATE).getDayOfYear();

        return DayOfWeek.from(LocalDate.of(year, month, day)).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}

class SolutionB1185 {
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] amount = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            ++amount[1];
        }

        int ans = 0;
        for (int i = 0; i < month - 1; ++i) {
            ans += amount[i];
        }
        return ans + day;
    }
}