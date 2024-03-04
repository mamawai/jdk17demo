package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 树中距离之和 {
    public static void main(String[] args) {
        int[] ints = new Solution834().sumOfDistancesInTree(6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}});
        for (int i : ints) {
            System.out.print(i + " ");
        }
    }
}

/**
 * 换根dp
 */
class Solution834 {
    private List<Integer>[] g;
    private int[] ans, size;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        g = new ArrayList[n]; // g[x] 表示 x 的所有邻居
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        ans = new int[n];
        size = new int[n];
        dfs(0, -1, 0); // 0 没有父节点
        reroot(0, -1); // 0 没有父节点
        return ans;
    }

    private void dfs(int x, int fa, int depth) {
        ans[0] += depth; // depth 为 0 到 x 的距离
        size[x] = 1;
        for (int y : g[x]) { // 遍历 x 的邻居 y
            if (y != fa) { // 避免访问父节点
                dfs(y, x, depth + 1); // x 是 y 的父节点
                size[x] += size[y]; // 累加 x 的儿子 y 的子树大小
            }
        }
    }

    private void reroot(int x, int fa) {
        for (int y : g[x]) { // 遍历 x 的邻居 y
            if (y != fa) { // 避免访问父节点
                ans[y] = ans[x] + g.length - 2 * size[y];
                reroot(y, x); // x 是 y 的父节点
            }
        }
    }
}
