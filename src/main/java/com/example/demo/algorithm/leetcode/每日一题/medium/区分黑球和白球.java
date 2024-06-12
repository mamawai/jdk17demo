package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 区分黑球和白球 {
    public static void main(String[] args) {
        long l = new Solution2938().minimumSteps("101100010");
        System.out.println(l);
    }
}

class Solution2938 {
    public long minimumSteps(String s) {
        long step = 0L;
        long count = 0L;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                count++;
                step += i - count + 1;
            }
        }
        return step;
    }
}