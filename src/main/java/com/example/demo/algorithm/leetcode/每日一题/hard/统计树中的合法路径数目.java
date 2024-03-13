package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 统计树中的合法路径数目 {
    public static void main(String[] args) {
        long l = new Solution2867().countPaths(5, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}});
        System.out.println(l);
    }
}

class Solution2867 {
        private final static int MX = (int) 1e5;
        private final static boolean[] np = new boolean[MX + 1]; // 质数=false 非质数=true

        static {
            np[1] = true;
            for (int i = 2; i * i <= MX; i++) {
                if (!np[i]) {
                    for (int j = i * i; j <= MX; j += i) {
                        np[j] = true;
                    }
                }
            }
        }

        public long countPaths(int n, int[][] edges) {
            List<Integer>[] g = new List[n + 1];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (var e : edges) {
                int x = e[0], y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            long ans = 0;
            int[] size = new int[n + 1];
            var nodes = new ArrayList<Integer>();
            for (int x = 1; x <= n; x++) {
                if (np[x]) { // 跳过非质数
                    continue;
                }
                int sum = 0;
                for (int y : g[x]) { // 质数 x 把这棵树分成了若干个连通块
                    if (!np[y]) {
                        continue;
                    }
                    if (size[y] == 0) { // 尚未计算过
                        nodes.clear();
                        dfs(y, -1, g, nodes); // 遍历 y 所在连通块，在不经过质数的前提下，统计有多少个非质数
                        for (int z : nodes) {
                            size[z] = nodes.size();
                        }
                    }
                    // 这 size[y] 个非质数与之前遍历到的 sum 个非质数，两两之间的路径只包含质数 x
                    // 这里ans计算的是夹 x 的情况 ； sum记录的是以x为顶点的情况
                    ans += (long) size[y] * sum;
                    sum += size[y];
                }
                ans += sum; // 从 x 出发的路径
            }
            return ans;
        }

        private void dfs(int x, int fa, List<Integer>[] g, List<Integer> nodes) {
            nodes.add(x);
            for (int y : g[x]) {
                if (y != fa && np[y]) {
                    dfs(y, x, g, nodes);
                }
            }
        }
}