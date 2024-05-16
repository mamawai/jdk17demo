package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 完成所有任务的最少时间 {
    public static void main(String[] args) {
        int minimumTime = new Solution2589().findMinimumTime(new int[][]{{1, 5, 1}, {3, 7, 2}, {6, 8, 1}});
        System.out.println(minimumTime);
    }
}

class Solution2589 {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int mx = tasks[tasks.length - 1][1];
        boolean[] run = new boolean[mx + 1];
        for (int[] t : tasks) {
            int start = t[0];
            int end = t[1];
            int d = t[2];
            for (int i = start; i <= end; i++) {
                if (run[i]) {
                    d--; // 去掉运行中的时间点
                }
            }
            for (int i = end; d > 0; i--) { // 剩余的 d 填充区间后缀
                if (!run[i]) {
                    run[i] = true; // 运行
                    d--;
                    ans++;
                }
            }
        }
        return ans;
    }
}