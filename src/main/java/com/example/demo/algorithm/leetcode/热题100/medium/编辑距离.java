package com.example.demo.algorithm.leetcode.热题100.medium;

import java.util.Arrays;

public class 编辑距离 {
    public static void main(String[] args) {
        int i = new Solution72().minDistance("intention", "execution");
        System.out.println(i);
    }
}

class Solution72 {
    private char[] s, t;
    private int[][] memo;

    public int minDistance(String text1, String text2) {
        s = text1.toCharArray();
        t = text2.toCharArray();
        int n = s.length, m = t.length;
        memo = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], -1); // -1 表示还没有计算过
        return dfs(n - 1, m - 1);
    }

    // i是源 j是目标
    private int dfs(int i, int j) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (memo[i][j] != -1) return memo[i][j]; // 之前算过了
        if (s[i] == t[j]) return memo[i][j] = dfs(i - 1, j - 1);
        // dfs(i - 1, j) 删除一位 源字符串位数-1继续dfs， dfs(i, j - 1) 新增 源字符串位数 + 1,新增的字母一定是要跟目标的最后一位一样所以同时往前移一位 dfs(i + 1 - 1, j - 1)
        // 替换就是dfs(i-1, j-1) 替换成一样的字母 双方位数不变 但是操作数要加1
        return memo[i][j] = Math.min(Math.min(dfs(i - 1, j), dfs(i, j - 1)), dfs(i - 1, j - 1)) + 1;
    }
}