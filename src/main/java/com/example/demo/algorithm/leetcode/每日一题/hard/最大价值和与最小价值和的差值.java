package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 最大价值和与最小价值和的差值 {
    public static void main(String[] args) {
        long l = new Solution2538B().maxOutput(9, new int[][]{{1,7},{5,2},{2,3},{6,0},{0,4},{4,7},{7,3},{3,8}}, new int[]{6,13,8,10,4,5,8,3,12});
        System.out.println(l);
    }
}
/**
 * 超时 时间复杂度n²了
 */
class Solution2538 {
    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] e = new List[n];
        Arrays.setAll(e, h -> new ArrayList<>());
        for (int[] edge : edges) {
            e[edge[0]].add(edge[1]);
            e[edge[1]].add(edge[0]);
        }
        int ans = 0;
        List<Integer> nodes = new ArrayList<>();
        for ( int i = 0, eLength = e.length; i < eLength; i++) {
            List<Integer> o = e[i];
            if (o.size() == 1) nodes.add(i);
        }

        for (Integer i : nodes) {
            int headPrice = price[i];
            List<int[]> pairs = new ArrayList<>();
            dfs(pairs, e, i, -1, 0, price);
            for (int[] pair : pairs) {
                if (pair[1] >= headPrice) {
                    ans = Math.max(ans, pair[0]);
                } else {
                    ans = Math.max(ans, pair[0] - pair[1] + headPrice);
                }
            }
        }
        return ans;
    }

    private void dfs(List<int[]> pairs, List<Integer>[] e, int son, int fa, int sum, int[] price) {
        for (Integer nb : e[son]) {
            if (nb != fa)  {
                dfs(pairs, e, nb, son, sum + price[nb], price);
            }
            if (nb == fa && e[son].size() == 1) {
                pairs.add(new int[]{sum, price[son]});
            }
        }
    }
}

/**
 * 树形DP 时间复杂度 n
 */
class Solution2538B {
    private List<Integer>[] g;
    private int[] price;
    private long ans;

    public long maxOutput(int n, int[][] edges, int[] price) {
        this.price = price;
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x); // 建树
        }
        dfs(0, -1);
        return ans;
    }

    private long[] dfs(int x, int fa) {
        // maxS1是包含当前节点的路径和 maxS2是不包含当前节点的路径和
        long p = price[x], maxS1 = p, maxS2 = 0;
        for (Integer y : g[x]) {
            if (y != fa) {
                long[] res = dfs(y, x);
                long s1 = res[0], s2 = res[1];
                // 前面最大带叶子的路径和 + 当前不带叶子的路径和 maxS2 + s1
                // 前面最大不带叶子的路径和 + 当前带叶子的路径和 maxS1 + s2
                ans = Math.max(ans, Math.max(maxS1 + s2, maxS2 + s1));
                // 更新maxS1 maxS2
                maxS1 = Math.max(maxS1, s1 + p);
                maxS2 = Math.max(maxS2, s2 + p); // 这里加上 p 是因为 x 必然不是叶子 叶子节点走不进来这个循环
            }
        }
        return new long[]{maxS1, maxS2};
    }
}