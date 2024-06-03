package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 课程表 {
    public static void main(String[] args) {
        boolean b = new Solution207().canFinish(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {3, 0}});
        System.out.println(b);
    }
}

class Solution207 {
    List<Integer>[] g;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图
        g = new List[numCourses];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] prerequisite : prerequisites) {
            int x = prerequisite[0];
            int y = prerequisite[1];
            g[x].add(y);
        }
        // dfs的时候要把所有的父节点记录
        for (int i = 0; i < g.length; i++) {
            if (g[i].size() > 0) {
                List<Integer> sons = new ArrayList<>();
                sons.add(i);
                if (!dfs(i, sons)) return false;
            }
        }
        return true;
    }

    private boolean dfs(int son, List<Integer> sons) {
        boolean res = true;
        for (Integer fa : g[son]) {
            if (sons.contains(fa)) return false;
            sons.add(fa);
            res &= dfs(fa, sons);
            sons.remove(sons.size() - 1);
        }
        g[son] = new ArrayList<>();
        return res;
    }
}