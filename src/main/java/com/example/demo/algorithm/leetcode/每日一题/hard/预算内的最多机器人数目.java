package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 预算内的最多机器人数目 {
    public static void main(String[] args) {
        int i = new Solution2398().maximumRobots(
                new int[]{11,12,19},
                new int[]{10,8,7},
                19);
        System.out.println(i);
    }
}

class Solution2398 {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int ans = 0;
        int n = chargeTimes.length;
        long[] prefixSum = new long[n + 1];
        long sum = 0;
        // 计算runningCosts的前缀和
        for (int i = 0; i < n; i++) prefixSum[i + 1] = sum += runningCosts[i];
        // 单调队列存在chargeTimes的索引
        Deque<Integer> q = new ArrayDeque<>();
        // 滑动窗口
        int left = 0;
        int right = 1;
        q.offer(0);
        while (left <= right) {
            long rCosts = prefixSum[right] - prefixSum[left];
            int k = right - left;
            int maxCharge = q.isEmpty() ? 0 :chargeTimes[q.peekFirst()];
            long totalCost = rCosts * k + maxCharge;
            if (totalCost <= budget) {
                ans = Math.max(ans, right - left);
                right++;
                if (right > n) break;
                while (!q.isEmpty() && chargeTimes[q.peekLast()] <= chargeTimes[right - 1]) {
                    q.pollLast();
                }
                q.addLast(right - 1);
            } else {
                left++;
                if (!q.isEmpty() && q.peekFirst() == left - 1) {
                    q.pollFirst();
                }
            }
        }
        return ans;
    }
}

class Solution2398B {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int res = 0, n = chargeTimes.length;
        long runningCostSum = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            runningCostSum += runningCosts[i];
            while (!q.isEmpty() && chargeTimes[q.peekLast()] <= chargeTimes[i]) {
                q.pollLast();
            }
            q.addLast(i);
            while (j <= i && (i - j + 1) * runningCostSum + chargeTimes[q.peekFirst()] > budget) {
                if (!q.isEmpty() && q.peekFirst() == j) {
                    q.pollFirst();
                }
                runningCostSum -= runningCosts[j];
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}