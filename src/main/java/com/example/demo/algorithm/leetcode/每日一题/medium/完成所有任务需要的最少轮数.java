package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 完成所有任务需要的最少轮数 {
    public static void main(String[] args) {
        int i = new Solution2244().minimumRounds(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4});
        System.out.println(i);
    }
}

class Solution2244 {
    public int minimumRounds(int[] tasks) {
        if (tasks.length == 1) return -1;
        // 先排序
        Arrays.sort(tasks);
        // dp[i] = Min(dp[i - 2] + 1(能否取到?如果-1说明不可行应该返回Integer.Max), dp[i-1] + 1(同前)); 如果dp[i] = MaxValue 返回 -1结束反之继续;
        int[] dp = new int[tasks.length];
        dp[0] = Integer.MAX_VALUE;
        if (tasks[0] == tasks[1]) dp[1] = 1;
        else dp[1] = Integer.MAX_VALUE;
        if (tasks.length != 2) {
            if (tasks[0] == tasks[1] && tasks[1] == tasks[2]) dp[2] = 1;
            else dp[2] = Integer.MAX_VALUE;
            for (int i = 3; i < dp.length; i++) {
                dp[i] = Math.min(getThree(tasks, dp, i), getTwo(tasks, dp, i));
            }
        }
        return dp[dp.length - 1] ==  Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }

    private int getThree(int[] tasks, int[] dp, int i) {
        if (dp[i - 3] == Integer.MAX_VALUE) return dp[i - 3];
        if (tasks[i] == tasks[i - 1] && tasks[i - 1] == tasks[i - 2]) return dp[i - 3] + 1;
        else return Integer.MAX_VALUE;
    }
    private int getTwo(int[] tasks, int[] dp, int i) {
        if (dp[i - 2] == Integer.MAX_VALUE) return dp[i - 2];
        if (tasks[i] == tasks[i - 1]) return dp[i - 2] + 1;
        else return Integer.MAX_VALUE;
    }
}