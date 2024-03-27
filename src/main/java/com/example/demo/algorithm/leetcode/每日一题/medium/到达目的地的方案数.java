package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 到达目的地的方案数 {
    public static void main(String[] args) {

    }
}

class Solution1976 {
    public int countPaths(int n, int[][] roads) {
        // e 是邻接表，根据 roads 创建
        int mod = 1000000007;
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int x = road[0], y = road[1], t = road[2];
            e[x].add(new int[]{y, t});
            e[y].add(new int[]{x, t});
        }
        // dis 用来记录源到各个点当前最短路径的长度。会在访问当前出队列点的相邻点的过程中被刷新。
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        int[] ways = new int[n];
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, 0});
        dis[0] = 0;
        ways[0] = 1;

        while (!pq.isEmpty()) {
            long[] arr = pq.poll();
            long t = arr[0];
            int u = (int) arr[1];
            if (t > dis[u]) {
                continue;
            }
            for (int[] next : e[u]) {
                int v = next[0], w = next[1];
                if (t + w < dis[v]) {
                    dis[v] = t + w;
                    ways[v] = ways[u];
                    pq.offer(new long[]{t + w, v});
                } else if (t + w == dis[v]) {
                    ways[v] = (ways[u] + ways[v]) % mod;
                }
            }
        }
        return ways[n - 1];
    }
}

class Solution1976B {
    public int countPaths(int n, int[][] roads) {
        // e 是邻接表，根据 roads 创建
        int mod = 1000000007;
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int x = road[0], y = road[1], t = road[2];
            e[x].add(new int[]{y, t});
            e[y].add(new int[]{x, t});
        }
        // dis 用来记录源到各个点当前最短路径的长度。会在访问当前出队列点的相邻点的过程中被刷新。
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[0] = 0;
        boolean[] vis = new boolean[n];
        int[] ways = new int[n];
        ways[0] = 1;
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && (x < 0 || dis[i] < dis[x]))
                    x = i;
            }
            if (x == n-1) // 找到终点，提前退出
                return ways[x];
            vis[x] = true; // 标记，在后续的循环中无需反复更新 x 到其余点的最短路长度
            for (int[] rt : e[x]) {
                if (dis[rt[0]] > dis[x] + rt[1]) {
                    dis[rt[0]] = dis[x] + rt[1];
                    ways[rt[0]] = ways[x];
                } else if (dis[rt[0]] == dis[x] + rt[1]) {
                    ways[rt[0]] = ways[rt[0]] + ways[x];
                }
            }
        }
    }
}