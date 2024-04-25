package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总数4 {
    public static void main(String[] args) {
        int i = new Solution377().combinationSum4(new int[]{1, 2, 3}, 4);
        System.out.println(i);
    }
}

class Solution377 {
    public int combinationSum4(int[] candidates, int target) {
        Arrays.sort(candidates);
        int[] dp = new int[target + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int candidate : candidates) {
                if (i > candidate) dp[i] += dp[i - candidate];
                else if (i == candidate) dp[i]++;
            }
        }
        return dp[target];
    }
}
