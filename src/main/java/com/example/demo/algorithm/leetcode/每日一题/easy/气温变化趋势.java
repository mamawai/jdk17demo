package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 气温变化趋势 {
    public static void main(String[] args) {
        int i = new SolutionLCP61().temperatureTrend(new int[]{21, 18, 18, 18, 31}, new int[]{34, 32, 16, 16, 17});
        System.out.println(i);
    }
}

class SolutionLCP61 {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ans = 0;
        int tmp = 0;
        for (int i = 1; i < temperatureA.length; i++) {
            int a = temperatureA[i] - temperatureA[i - 1];
            int b = temperatureB[i] - temperatureB[i - 1];
            if (a > 0 && b > 0) tmp++;
            else if (a == 0 && b == 0) tmp++;
            else if (a < 0 && b < 0) tmp++;
            else {
                ans = Math.max(ans, tmp);
                tmp = 0;
            }
        }
        return Math.max(ans, tmp);
    }
}