package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;
import java.util.function.IntPredicate;

public class 网络延迟时间 {
    public static void main(String[] args) {
        int i = new Solution743().networkDelayTime(new int[][]{{1,2,1},{2,1,3}}, 2, 2);
        System.out.println(i);
    }
}

class Solution743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] e = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            e[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            int src = time[0], tar = time[1], t = time[2];
            e[src].add(new int[]{tar, t});
        }
        int[] minTimes = new int[n + 1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        minTimes[k] = 0;
        minTimes[0] = -1;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            List<int[]> paths = e[polled];
            if (paths == null) continue;
            for (int[] path : paths) {
                int tar = path[0];
                int t = path[1];
                int preT = minTimes[polled];
                if (minTimes[tar] > t + preT) {
                    minTimes[tar] = t + preT;
                    queue.offer(tar);
                }
            }
        }
        if (Arrays.stream(minTimes).allMatch(value -> value != Integer.MAX_VALUE)) {
            return Arrays.stream(minTimes).max().getAsInt();
        } else {
            return -1;
        }
    }
}