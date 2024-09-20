package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 求出最长好子序列1 {
    public static void main(String[] args) {
        int i = new Solution3176C().maximumLength(new int[]{78,79,80,80,78,79,79,78}, 2);
        System.out.println(i);
    }
}

class Solution3176 {
    int[] nums;
    int[][][] memo;
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        // 表示当前k时，上一个数的下标是pre，在下标i位置上（包括位置i）后面的最长好子序列的长度
        memo = new int[n][n + 1][k + 1];
        for (int[][] row : memo) {
            for (int[] a : row) {
                Arrays.fill(a, -1);
            }
        }
        this.nums = nums;
        return dfs(0, k, n);
    }

    private int dfs(int i, int k, int j) {
        if (k < 0) return -1;
        if (i == nums.length) return 0;
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // pick i
        int res = 0;
        res = Math.max(res, dfs(i + 1, (j == nums.length || nums[j] == nums[i]) ? k : k - 1, i) + 1);
        // not pick i
        res = Math.max(res, dfs(i + 1, k, j));
        return memo[i][j][k] = res;
    }
}

class Solution3176B {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][n + 1][k + 1];  // DP 数组

        // 自底向上递推，逆序遍历 i
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                for (int t = 0; t <= k; t++) {
                    // 不选 nums[i]
                    dp[i][j][t] = dp[i + 1][j][t];

                    // 选 nums[i]
                    if (j == n || nums[i] == nums[j]) {
                        dp[i][j][t] = Math.max(dp[i][j][t], dp[i + 1][i][t] + 1);
                    } else if (t > 0) {
                        dp[i][j][t] = Math.max(dp[i][j][t], dp[i + 1][i][t - 1] + 1);
                    }
                }
            }
        }
        return dp[0][n][k];
    }
}

class Solution3176C {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k+1];   // dp[i][l]表示以nums[i]结尾，存在l个下标满足不等条件的最长好子序列长度, 初始状态值都为0
        int res = 1;                    // 结果初始为最小值1
        for(int i = 0; i < n; i++){
            dp[i][0] = 1;                                   // 表示nums[i]自己作为一个子序列
            for(int l = 0; l <= Math.min(k, i); l++){       // 枚举l的范围，[0,i]共i+1个数最多有i个下标可以判断seq[i] != seq[i + 1]，且上限为k
                for(int j = 0; j < i; j++){                 // 枚举[0, i)的元素nums[j]，将nums[i]追加到nums[j]结尾的最长好子序列
                    int flag = nums[i] != nums[j] ? 1 : 0;  // 判断nums[i]和前一个nums[j]是否相等
                    if(l - flag >= 0){
                        // 如果nums[i] = nums[j], 不等条件的下标个数不会增加，因此从nums[j]的l状态dp[j][l]转移过来
                        // 如果nums[i] != nums[j], 不等条件的下标个数增加1个，因此从nums[j]的l状态dp[j][l-1]转移过来
                        dp[i][l] = Math.max(dp[i][l], 1 + dp[j][l-flag]);
                    }
                }
                res = Math.max(res, dp[i][l]);              // 更新最大值，每个状态都有可能是最大值
            }
        }
        return res;
    }
}