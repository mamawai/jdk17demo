package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 统计已测试设备 {
    public static void main(String[] args) {

    }
}

class Solution2960 {
    public int countTestedDevices(int[] batteryPercentages) {
        int count = 0;
        for (int bp : batteryPercentages)
            if (bp > count) count++;
        return count;
    }
}