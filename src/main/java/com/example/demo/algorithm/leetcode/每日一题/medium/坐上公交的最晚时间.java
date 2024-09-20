package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 坐上公交的最晚时间 {
    public static void main(String[] args) {
        int i = new Solution2332().latestTimeCatchTheBus(new int[]{5}, new int[]{7,8}, 1);
        System.out.println(i);
    }
}

class Solution2332 {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        // 排序
        Arrays.sort(buses);
        Arrays.sort(passengers);
        // 定义变量
        int bn = buses.length, pn = passengers.length, j = 0, ans = 0;
        if (bn == 1 && pn == 1) {
            if (buses[0] < passengers[0]) return buses[0];
            else {
                if (passengers[0] == buses[0]) return passengers[0] - 1;
                else return capacity == 1 ? passengers[0] - 1 : buses[0];
            }
        }
        int lastCap = 0;
        for (int leftTime : buses) {
            int cap = capacity;
            for (; j < pn; j++) {
                if (passengers[j] <= leftTime) cap--;
                else {
                    // 如果车没上满，最后一个等于leftTime，则插队
                    if (j > 0 && leftTime == passengers[j]) {
                        int tmp = j;
                        while (tmp >= 1 && passengers[tmp] - 1 == passengers[tmp - 1]) tmp--;
                        ans = passengers[tmp] - 1;
                    } else ans = leftTime;// 反之直接赋值leftTime
                    break;
                }
                if (cap == 0) {
                    int tmp = j;
                    // 如果车上满了，需要插队，不能与乘客时间一样
                    while (tmp >= 1 && passengers[tmp] - 1 == passengers[tmp - 1]) tmp--;
                    ans = passengers[tmp] - 1;
                    j++;
                    break;
                }
            }
            lastCap = cap;
        }
        if (lastCap > 0 && j > 0) {
            // 说明最后一班车没装满
            if (passengers[j - 1] < buses[bn - 1]) return buses[bn - 1];
            else {
                int tmp = j - 1;
                while (tmp >= 1 && passengers[tmp] - 1 == passengers[tmp - 1]) tmp--;
                return passengers[tmp] - 1;
            }
        }
        return ans == 0 ? buses[bn - 1] : ans ;
    }
}
