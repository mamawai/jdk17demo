package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 图中的最长环 {
    public static void main(String[] args) {
        int i = new Solution2360().longestCycle(new int[]{-1,4,-1,2,0,4});
        System.out.println(i);
    }
}

class Solution2360 {
    public int longestCycle(int[] edges) {
        // 至多有一个出边
        int res = -1;
        boolean[] visited = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            if (visited[i]) continue;
            int[] ret = dfs(edges, visited, i, 0);
            if (ret[0] != -1 && ret[2] == 1) res = Math.max(res, ret[1]);
        }
        return res;
    }

    private int[] dfs(int[] edges, boolean[] visited, int i, int step) {
        if (i == -1 || visited[i]) return new int[]{i, step, -1}; // 下标，步长，是否成环
        int nxt = edges[i];
        visited[i] = true;
        int[] ret = dfs(edges, visited, nxt, step + 1);
        if (ret[0] == i) {
            ret[1] = ret[1] - step;
            ret[2] = 1;
        }
        return ret;
    }
}