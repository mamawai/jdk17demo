package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.StringJoiner;

public class 日期转为二进制表示 {
    public static void main(String[] args) {
        String s = new Solution3280().convertDateToBinary("2080-02-29");
        System.out.println(s);
    }
}

class Solution3280 {
    public String convertDateToBinary(String date) {
        String[] split = date.split("-");
        StringJoiner sj = new StringJoiner("-");
        for (String s : split) sj.add(Integer.toBinaryString(Integer.parseInt(s)));
        return sj.toString();
    }
}