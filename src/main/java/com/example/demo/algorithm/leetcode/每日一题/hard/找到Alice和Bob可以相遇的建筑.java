package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 找到Alice和Bob可以相遇的建筑 {
    public static void main(String[] args) {
        int[] ints = new Solution2940().leftmostBuildingQueries(new int[]{5, 3, 8, 2, 6, 1, 4, 6}, new int[][]{{0, 7}, {0, 1}, {3, 2}, {1, 3}, {3, 5}});
        System.out.println(Arrays.toString(ints));
    }
}

class Solution2940 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        List<int[]>[] qs = new ArrayList[heights.length];
        Arrays.setAll(qs, i -> new ArrayList<>());

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp; // 保证 a <= b
            }
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b; // a 直接跳到 b
            } else {
                qs[b].add(new int[]{heights[a], i}); // 离线询问
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < heights.length; i++) {
            while (!pq.isEmpty() && pq.peek()[0] < heights[i]) {
                // 堆顶的 heights[a] 可以跳到 heights[i]
                ans[pq.poll()[1]] = i;
            }
            for (int[] q : qs[i]) {
                pq.offer(q); // 后面再回答
            }
        }
        return ans;
    }
}