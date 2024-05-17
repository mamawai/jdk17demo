package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 安排工作以达到最大收益 {
    public static void main(String[] args) {
        int i = new Solution826().maxProfitAssignment(
                new int[]{9,16,16,29,35,41,42,46,54,57,61,63,69,72,75,76,84,93,93,95},
                new int[]{4,7,18,22,25,32,34,35,38,44,51,60,61,71,74,77,89,95,96,96},
                new int[]{29,81,10,90,13,11,88,50,7,28,28,85,66,45,50,89,93,7,89,40});
        System.out.println(i);
    }
}

class Solution826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int maxProfit = 0;
        Arrays.sort(worker);
        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        int lastProfit = 0;
        int lastHard = 0;
        int j = 0;
        for (int ability : worker) {
            while (ability >= lastHard && j < n) {
                if (jobs[j][0] > ability) {
                    break;
                }
                int[] polled = jobs[j++];
                // 难度从低到高 报酬需要单调不减
                if (polled[1] > lastProfit) {
                    lastProfit = polled[1];
                    lastHard = polled[0];
                }
            }
            maxProfit += lastProfit;
        }
        return maxProfit;
    }
}

class Solution826B{
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        Arrays.sort(worker);
        int ans = 0, j = 0, maxProfit = 0;
        for (int w : worker) {
            while (j < n && jobs[j][0] <= w) {
                maxProfit = Math.max(maxProfit, jobs[j++][1]);
            }
            ans += maxProfit;
        }
        return ans;
    }
}