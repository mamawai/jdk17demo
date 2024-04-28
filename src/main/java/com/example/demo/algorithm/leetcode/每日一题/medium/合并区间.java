package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.IntFunction;

public class 合并区间 {
    public static void main(String[] args) {

    }
}

class Solution56 {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int[] interval : intervals) {
            queue.offer(interval);
        }
        int[] cur = queue.poll();
        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            if (polled[0] > cur[1]) {
                ans.add(cur);
                cur = polled;
            } else if (polled[1] > cur[1]) {
                cur[1] = polled[1];
            }
        }
        ans.add(cur);
        return ans.toArray(value -> new int[0][]);
    }
}
