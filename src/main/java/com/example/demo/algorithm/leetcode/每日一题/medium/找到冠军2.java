package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 找到冠军2 {
    public static void main(String[] args) {
        int champion = new Solution2924Ling().findChampion(3, new int[][]{{0, 2}, {1, 0}});
        System.out.println(champion);
    }
}


    class Solution2924Ling {
        public int findChampion(int n, int[][] edges) {
            boolean[] isWeak = new boolean[n];
            for (int[] e : edges) {
                isWeak[e[1]] = true; // 不是冠军
            }
            int ans = -1;
            for (int i = 0; i < n; i++) {
                if (isWeak[i]) {
                    continue;
                }
                if (ans != -1) {
                    return -1; // 冠军只能有一个
                }
                ans = i;
            }
            return ans;
        }
    }

class Solution2924MySelf {
    public int findChampion(int n, int[][] edges) {
        int ans = -1;
        int times = 0;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, e->new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[1]].add(edge[0]);
        }
        for (int i = 0; i < g.length; i++) {
            List<Integer> fas = g[i];
            if (fas.isEmpty()) {
                ans = i;
                times++;
            }
            if (times > 1) return -1;
        }
        return ans;
    }
}
