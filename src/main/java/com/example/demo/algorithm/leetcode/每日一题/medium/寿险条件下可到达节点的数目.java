package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 寿险条件下可到达节点的数目 {
    public static void main(String[] args) {
        int i = new Solution2368().reachableNodes(10, new int[][]{{4,1},{1,3},{1,5},{0,5},{3,6},{8,4},{5,7},{6,9},{3,2}}, new int[]{2, 7});
        System.out.println(i);
    }
}

class Solution2368 {
    int ans = 0;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] isrestricted = new boolean[n];
        for (int x : restricted) {
            isrestricted[x] = true;
        }
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] v : edges) {
            g[v[0]].add(v[1]);
            g[v[1]].add(v[0]);
        }
        dfs(0, -1, g, isrestricted);
        return ans;
    }

    private void dfs(int tar, int fa, List<Integer>[] g, boolean[] isrestricted) {
        ans++;
        for (int y : g[tar]) {
            if (y != fa && !isrestricted[y]) {
                dfs(y, tar, g, isrestricted);
            }
        }
    }
}