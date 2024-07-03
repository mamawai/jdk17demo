package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 最大化一张图中的路径价值 {
    public static void main(String[] args) {
        int i = new Solution2065().maximalPathQuality(new int[]{1,2,3,4},
                new int[][]{{0,1,10},{1,2,11},{2,3,12},{1,3,13}}, 50);
        System.out.println(i);
    }
}

class Solution2065 {
    int[][] memo;
    List<int[]>[] g;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        // 用剩余时间和节点 来记忆化搜索
        int n = values.length;
        int[] vis = new int[values.length];
        memo = new int[maxTime+1][n +1];
        for (int[] r : memo) {
            Arrays.fill(r, -1);
        }
        // 建图
        g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            g[from].add(new int[]{to, cost});
            g[to].add(new int[]{from, cost});
        }
        return dfs(maxTime, 0, 0, values, vis);
    }

    private int dfs(int restTime, int pos, int value, int[] values, int[] vis) {
        // 访问pos节点 计算当前的value
        int vn = vis[pos] != 0 ? 0 : values[pos];
        value += vn;
        vis[pos]++;
        if (memo[restTime][pos] == -2) {
            vis[pos]--;
            return -2;
        }
        if (restTime < 10) {
            if (pos == 0) {
                vis[pos]--;
                memo[restTime][pos] = 0;
                return value;
            } else {
                vis[pos]--;
                return memo[restTime][pos] = -2;
            }
        }
        for (int[] nextPos : g[pos]) {
            int next = nextPos[0];
            int cost = nextPos[1];
            int rest = restTime - cost;
            if (rest < 0) {
                continue;
            }
            if (memo[rest][next] == -1) {
                int dfsed = dfs(rest, next, value,  values, vis);
                memo[restTime][pos] = Math.max(memo[restTime][pos], dfsed >= 0? dfsed + vn : dfsed);
            } else {
                memo[restTime][pos] =  Math.max(memo[restTime][pos], Math.max(memo[rest][next], vn));
            }
        }
        vis[pos]--;
        // 如果dfs到的这个节点的restTime 小于四周的路径耗时 那么memo此时的情况为-2
        return memo[restTime][pos] == -1 ? memo[restTime][pos] = -2: memo[restTime][pos];
    }
}

/**
 * 暴力
 */
class Solution2065B {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int t = e[2];
            g[x].add(new int[]{y, t});
            g[y].add(new int[]{x, t});
        }

        boolean[] vis = new boolean[n];
        vis[0] = true;
        return dfs(0, 0, values[0], vis, g, values, maxTime);
    }

    private int dfs(int x, int sumTime, int sumValue, boolean[] vis, List<int[]>[] g, int[] values, int maxTime) {
        int res = x == 0 ? sumValue : 0;
        for (int[] e : g[x]) {
            int y = e[0];
            int t = e[1];
            if (sumTime + t > maxTime) {
                continue;
            }
            if (vis[y]) {
                res = Math.max(res, dfs(y, sumTime + t, sumValue, vis, g, values, maxTime));
            } else {
                vis[y] = true;
                // 每个节点的价值至多算入价值总和中一次
                res = Math.max(res, dfs(y, sumTime + t, sumValue + values[y], vis, g, values, maxTime));
                vis[y] = false; // 恢复现场
            }
        }
        return res;
    }
}

/**
 * Dijkstra 算法
 */
class Solution2605 {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int t = e[2];
            g[x].add(new int[]{y, t});
            g[y].add(new int[]{x, t});
        }

//        int[] dis = new int[n];
//        Arrays.fill(dis, Integer.MAX_VALUE);
//        dis[0] = 0;
//        boolean[] done = new boolean[n];
//        while (true) {
//            int x = -1;
//            for (int i = 0; i < n; i++) {
//                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
//                    x = i;
//                }
//            }
//            // x < 0 说明dis已经更新好了
//            if (x < 0) break;
//            // 证明x节点到0已被更新
//            done[x] = true;
//            for (int[] e : g[x]) {
//                // 下一个节点
//                int y = e[0];
//                // 更新y到x的最短路
//                dis[y] = Math.min(dis[y], e[1] + dis[x]);
//            }
//        }




        // Dijkstra 算法 （稀疏图）
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        // 用pq小顶堆来代替上方for循环寻找最小路径
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) { // x 之前出堆过
                continue;
            }
            for (int[] e : g[x]) {
                int y = e[0];
                int newDis = dx + e[1];
                if (newDis < dis[y]) {
                    dis[y] = newDis; // 更新 x 的邻居的最短路
                    pq.offer(new int[]{newDis, y});
                }
            }
        }

        boolean[] vis = new boolean[n];
        vis[0] = true;
        return dfs(0, 0, values[0], vis, g, values, maxTime, dis);
    }

    private int dfs(int x, int sumTime, int sumValue, boolean[] vis, List<int[]>[] g, int[] values, int maxTime, int[] dis) {
        int res = x == 0 ? sumValue : 0;
        for (int[] e : g[x]) {
            int y = e[0];
            int t = e[1];
            // 相比方法一，这里多了 dis[y]
            if (sumTime + t + dis[y] > maxTime) {
                continue;
            }
            if (vis[y]) {
                res = Math.max(res, dfs(y, sumTime + t, sumValue, vis, g, values, maxTime, dis));
            } else {
                vis[y] = true;
                // 每个节点的价值至多算入价值总和中一次
                res = Math.max(res, dfs(y, sumTime + t, sumValue + values[y], vis, g, values, maxTime, dis));
                vis[y] = false; // 恢复现场
            }
        }
        return res;
    }
}