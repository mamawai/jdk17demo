package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 关闭分部的可行集合数目 {
    public static void main(String[] args) {
        int n = new Solution2959().numberOfSets(4, 5, new int[][]{
                {0, 1, 2},
                {1, 2, 5},
                {0, 2, 2},
                {2, 3, 1}
        });
//        int n1 = new Solution2959().numberOfSets(3, 5, new int[][]{
//                {2,0,4},
//                {1,0,3},
//                {1,0,2},
//        });
//        int n2 = new Solution2959().numberOfSets(5, 13, new int[][]{
//                {3,0,5},
//                {2,1,3},
//                {1,0,6},
//                {3,2,19},
//                {2,1,29},
//                {2,1,30},
//                {1,0,5},
//                {2,0,29},
//                {4,3,15},
//
//        });
        int n3 = new Solution2959().numberOfSets(4, 12, new int[][]{
                {3,2,2},
                {1,0,5},
                {3,0,7},
                {3,1,15},
                {2,0,12},
                {1,0,1}
        });
        System.out.println(n);
    }
}

class Solution2959 {
    int[] memo;
    int[][] g;
    int res;
    int maxDis;
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        // 保底 全删除 留一个
        // 比如我选0节点 然后再选1节点 如果1节点加入不行那么再试试2节点 如果所有节点都不行那么 从1开始选2节点。。。
        // 如果01节点好使继续012 013 014 新节点的加入并不会改变原有节点的可行性 只需要计算新增节点到各个节点的最小值即可
        this.maxDis = maxDistance;
        res = n + 1;// 保底
        // 去重roads
        Set<Integer> candidates = new HashSet<>();
        // 先建图 顺便 起始对 就从road dis <= maxDis里面选吧
        g = new int[n][n];
        for (int[] e : g) {
            Arrays.fill(e, 1001);
        }
        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            int dis = road[2];
            if (dis <= maxDistance) {
                if (candidates.add((1 << x) + (1 << y))) {
                    res++;
                }
            }
            g[x][y] = Math.min(g[x][y], dis);
            g[y][x] = Math.min(g[y][x], dis);
        }
        // 压缩状态 0代表不选1代表选 如果四个节点 1111
        int size = 1 << n;
        memo = new int[size];
        // 记忆化搜索 memo 比如 0 1 - 2; 0 2 - 1直接跳过
        // 遍历可选的节点对
        for (int candidate : candidates) {
            int[] picked = new int[n];
            for (int i = 0; i < n; i++) {
                if (((candidate >> i) & 1) == 1) {
                    picked[i]++;
                }
            }
            // 遍历节点
            for (int i = 0; i < n; i++) {
                if (picked[i] == 0) dfs(picked, candidate, i);
            }
        }
        return res;
    }

    private void dfs(int[] picked, int candidate, int newP) {
        // 记忆化
        if (memo[candidate + (1 << newP)] != 0) return;
        // 标记此状态搜索过 不管成功还是失败
        memo[candidate + (1 << newP)]++;
        // 看是否可到达 历史状态节点之间肯定可到达 那么只要看新节点 是否连接到至少一个历史节点即可到达
        // 再看dij找newP到各节点的最短路是否<=maxDis
        if (!isArrivalAndDij(picked, newP)) return;
        res++;
        picked[newP]++;
        for (int i = 0; i < picked.length; i++) {
            if (picked[i] == 0) dfs(picked, candidate + (1 << newP), i);
        }
        picked[newP]--;
    }
    private boolean isArrivalAndDij(int[] picked, int newP) {
        if (!isArrival(picked, newP)) return false;
        // dij
        int n = picked.length;
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[newP] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, newP});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) continue;

            for (int i = 0; i < n; i++) {
                // 为0代表xi两点之间没有连线
                if (g[x][i] == 1001) continue;
                // 为0代表这节点要忽略掉
                if (picked[i] == 0) continue;
                int newDis = dx + g[x][i];
                if (newDis < dis[i]) {
                    dis[i] = newDis;
                    pq.offer(new int[]{newDis, i});
                }
            }
        }
        for (int d : dis) {
            // d > maxDis 说明该节点不能入选 返回false
            if (d != Integer.MAX_VALUE && d > maxDis) return false;
        }
        return true;
    }

    private boolean isArrival(int[] picked, int newP) {
        for (int j = 0; j < picked.length; j++) {
            if (picked[j] > 0) {
                for (int i = 0; i < g[newP].length; i++) {
                    if (g[newP][i] == 1001) continue;
                    // 这里要下标j 跟下标i比较 因为j和i才是节点编号
                    if (i == j) return true;
                }
            }
        }
        return false;
    }
}