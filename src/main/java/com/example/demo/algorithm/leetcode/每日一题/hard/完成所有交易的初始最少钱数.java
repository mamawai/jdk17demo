package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 完成所有交易的初始最少钱数 {
    public static void main(String[] args) {

    }
}

class Solution2412 {
    public long minimumMoney(int[][] transactions) {
        long totalLose = 0;
        int res = 0;
        for (int[] t : transactions) {
            int cost = t[0];
            int cashback = t[1];
            totalLose += Math.max(cost - cashback, 0);
            res = Math.max(res, Math.min(cost, cashback));
        }
        return totalLose + res;
    }
}