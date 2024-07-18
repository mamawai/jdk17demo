package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.lang.reflect.Array;
import java.util.*;

public class 访问消失节点的最少时间 {
    public static void main(String[] args) {
        Map<Integer, Integer>[] g = new HashMap[3];
        g[0].put(1,1);
    }
}

class Solution3112 {
    Map<Integer, Integer>[] g;
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        g = new HashMap[n];
        Arrays.setAll(g, i -> new HashMap<>());
        // 建图
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int l = edge[2];
            g[x].put(y, Math.min(l, g[x].getOrDefault(y, Integer.MAX_VALUE)));
            g[y].put(x, Math.min(l, g[y].getOrDefault(x, Integer.MAX_VALUE)));
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        dis[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) continue;
            for (Map.Entry<Integer, Integer> entry : g[x].entrySet()) {
                int y = entry.getKey();
                int da = disappear[y];
                int newDis = dx + entry.getValue();
                if (newDis >= da) continue;
                if (newDis < dis[y] || dis[y] < 0) {
                    dis[y] = newDis;
                    pq.offer(new int[]{newDis, y});
                }
            }
        }
        return dis;
    }
}