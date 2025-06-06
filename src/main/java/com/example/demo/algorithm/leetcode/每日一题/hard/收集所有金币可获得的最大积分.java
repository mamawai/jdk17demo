package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 收集所有金币可获得的最大积分 {
    public static void main(String[] args) {
        int i = new Solution2920().maximumPoints(
                new int[][]{{0,1},{0,2},{1,3},{3,4},{0,5},{6,2},{5,7},{3,8},{9,7}},
                new int[]{0,5,10,5,6,5,0,2,0,0},
                7);
        System.out.println(i);
    }
}

class Solution2920 {
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        // 建图
        int n = coins.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) { // 建图
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        int[][] memo = new int[n][14];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        // 方法一收集金币
        return Math.max(dfs(g, coins, k, 0, 0, - 1, memo) + coins[0] - k,
        // 方法二收集金币
        dfs(g, coins, k,0, 1, -1, memo) + (coins[0] >> 1));
    }

    private int dfs(List<Integer>[] g, int[] coins, int k, int idx, int times, int father, int[][] memo) {
        if (times == 13) return 0;
        if (memo[idx][times] != -1) return memo[idx][times];
        int res = 0;
        for (int child : g[idx]) {
            if (child == father) continue;
            res += Math.max(
                    // 如果要用方法1
                    dfs(g, coins, k, child, times, idx, memo) + (coins[child] >> times) - k,
                    // 如果要用方法2
                    dfs(g, coins, k, child, times + 1, idx, memo) + (coins[child] >> times + 1)
            );
        }
        return memo[idx][times] = res;
    }
}



class Solution2920B {
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int[][] memo = new int[n][14];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(0, 0, -1, memo, g, coins, k);
    }

    private int dfs(int i, int j, int fa, int[][] memo, List<Integer>[] g, int[] coins, int k) {
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res1 = (coins[i] >> j) - k;
        int res2 = coins[i] >> (j + 1);
        for (int ch : g[i]) {
            if (ch == fa) continue;
            res1 += dfs(ch, j, i, memo, g, coins, k); // 不右移
            if (j < 13) { // j+1 >= 14 相当于 res2 += 0，无需递归
                res2 += dfs(ch, j + 1, i, memo, g, coins, k); // 右移
            }
        }
        return memo[i][j] = Math.max(res1, res2); // 记忆化
    }
}