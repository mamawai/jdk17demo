package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 不相交的线 {
    public static void main(String[] args) {
        int i = new Solution1035().maxUncrossedLines(new int[]{1, 1, 2, 4, 2}, new int[]{2, 1, 4, 2, 1});
        System.out.println(i);
    }
}

/**
 * dp好题啊
 */
class Solution1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
               }
            }
        }
        return dp[m][n];
    }
}