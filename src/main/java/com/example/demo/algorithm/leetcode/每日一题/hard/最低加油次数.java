package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class 最低加油次数 {
    public static void main(String[] args) {
        int i = new Solution871B().minRefuelStops(1000, 83, new int[][]{
                {47, 220},{65,1}, {98,113},
                {126,196},{186,218}, {320,205},
                {686,317},{707,325},{754,104},{781,105}
        });
        System.out.println(i);
    }
}

class Solution871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int[] station : stations) queue.offer(station);
        int ans = 0;
        while (startFuel < target) {
            int needChange = startFuel;
            if (queue.isEmpty()) return -1;
            ArrayList<int[]> tmp = new ArrayList<>();
            while (!queue.isEmpty()) {
                int[] polled = queue.poll();
                if (needChange >= polled[0]) {
                    needChange += polled[1];
                    ans++;// 加了一次油
                    if (needChange >= target) return ans;
                    if (!tmp.isEmpty()) queue.addAll(tmp);
                    tmp.clear();
                }
                else tmp.add(polled);
            }
            if (startFuel == needChange) return -1;
            startFuel = needChange;
            if (!tmp.isEmpty()) queue.addAll(tmp);
        }
        return ans;
    }
}
class Solution871B {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= stations[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }
}