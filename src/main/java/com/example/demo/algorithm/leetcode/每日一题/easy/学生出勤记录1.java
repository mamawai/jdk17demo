package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 学生出勤记录1 {
    public static void main(String[] args) {

    }
}

class Solution551 {
    public boolean checkRecord(String s) {
        int a = 0;
        int l = 0;
        char pre = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                a++;
                if (a > 1) {
                    return false;
                }
            }
            if (c == 'L') {
                if (pre != 'L') l = 1;
                else l++;
                if (l > 2) {
                    return false;
                }
            }
            pre = c;
        }
        return true;
    }
}