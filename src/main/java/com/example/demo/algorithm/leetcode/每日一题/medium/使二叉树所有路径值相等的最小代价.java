package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 使二叉树所有路径值相等的最小代价 {
    public static void main(String[] args) {
        int i = new Solution2673().minIncrements(15, new int[]{764,1460,2664,764,2725,4556,5305,8829,5064,5929,7660,6321,4830,7055,3761});
        System.out.println(i);
    }
}

class Solution2673 {
    int ans = 0;
    public int minIncrements(int n, int[] cost) {
        // 计算有叶子节点的节点并补全
        dfs((n - 1) / 2, n, cost);
        return ans;
    }

    private void dfs(int begin, int end, int[] cost) {
        if (begin <= 0) {
            return;
        }
        for (int i = begin, j = begin + 1; j < end; i+=2, j+=2) {
            int ci = 2*i + 1;
            int costI = cost[i];
            if (ci < cost.length) {
                costI += cost[ci];
            }
            int cj = 2*j + 1;
            int costJ = cost[j];
            if (cj < cost.length) {
                costJ += cost[cj];
            }
            int abs = Math.abs(costI - costJ);
            int max = Math.max(costI, costJ);
            cost[i] = max;
            cost[j] = max;
            if (abs == 0) continue;
            ans += abs;
        }
        dfs((begin-1) / 2, begin, cost);
    }
}