package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;
import java.util.stream.Collectors;

public class 重新规划路线 {
    public static void main(String[] args) {
        int i = new Solution1466().minReorder(6, new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}});
        System.out.println(i);
    }
}

class Solution1466 {
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        for (int[] edge : connections) {
            e[edge[0]].add(new int[]{edge[1], 1});
            e[edge[1]].add(new int[]{edge[0], 0});
        }
        return dfs(0, -1, e);
    }

    public int dfs(int x, int parent, List<int[]>[] e) {
        int res = 0;
        for (int[] edge : e[x]) {
            if (edge[0] == parent) {
                continue;
            }
            res += edge[1] + dfs(edge[0], x, e);
        }
        return res;
    }
}