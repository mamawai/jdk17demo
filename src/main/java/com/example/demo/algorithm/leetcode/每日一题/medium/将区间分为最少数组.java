package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 将区间分为最少数组 {
    public static void main(String[] args) {

    }
}

class Solution2406 {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        var pq = new PriorityQueue<Integer>();
        for (var p : intervals) {
            if (!pq.isEmpty() && pq.peek() < p[0]) pq.poll();
            pq.offer(p[1]);
        }
        return pq.size();
    }
}