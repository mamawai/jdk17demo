package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;

public class 单调数组对的数目1 {
    public static void main(String[] args) {
        int i = new Solution3250B().countOfPairs(new int[]{2,3,2,4,3});
        System.out.println(i);
    }
}

class Solution3250 {

    int[][] memo;
    public int countOfPairs(int[] nums) {
        if (nums.length == 1) return nums[0] + 1;
        int mod = 1000000007, n = nums.length;
        memo = new int[n][51];
        int num = nums[0];
        while (num >= 0) memo[0][num] = canPick(num--, nums, 0) ? 1 : 0; // 初始化memo第一列的值
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            while (cur >= 0) {
                if (canPick(cur, nums, i)) { // 判断是否能取当前的值
                    for (int j = 0; j <= cur; j++) { // 将上一个小于等于cur的值累加
                        if (nums[i - 1] - j >= nums[i] - cur) { // 判断能否取到前一个值
                            memo[i][cur] = (memo[i][cur] + memo[i - 1][j]) % mod;
                        }
                    }
                }
                cur--;
            }
        }
        // 加和memo最后一列数据
        int ans = 0;
        for (int each : memo[n - 1]) ans = (ans + each) % mod;
        return ans;
    }

    /**
     * 判断该位置能否取这个值
     */
    private boolean canPick(int num, int[] nums, int idx) {
        if (idx == 0) {
            int nxt = num;
            while (nxt <= nums[idx + 1]) {
                if (nums[idx] - num >= nums[idx + 1] - nxt) return true;
                else nxt++;
            }
        } else {
            if (idx == nums.length - 1) return true; // 默认最后一个数字都可以取
            int nxt = num;
            while (nxt <= nums[idx + 1]) {
                if (nums[idx] - num >= nums[idx + 1] - nxt && nums[idx - 1] >= nums[idx] - num) return true;
                else nxt++;
            }
        }
        return false;
    }
}


class Solution3250B {
    public int countOfPairs(int[] nums) {
        final int MOD = 1_000_000_007;
        int n = nums.length;
        int m = Arrays.stream(nums).max().getAsInt();
        long[][] f = new long[n][m + 1]; // f[i][j]表示，下标0到i中单调数组对的个数，且正向不减数组的arr[i] = j
        long[] s = new long[m + 1];

        Arrays.fill(f[0], 0, nums[0] + 1, 1); // 初始化前缀和
        for (int i = 1; i < n; i++) {
            s[0] = f[i - 1][0];
            for (int k = 1; k <= m; k++) {
                s[k] = (s[k - 1] + f[i - 1][k]) % MOD; // f[i-1] 的前缀和 先都算出来虽然不一定全部用得上
            }
            for (int j = 0; j <= nums[i]; j++) {
                int maxK = j + Math.min(nums[i - 1] - nums[i], 0); // 用maxK判断要前后单调的满足条件,即maxK小于0时该值j不能取 ->那么就赋值0
                f[i][j] = maxK >= 0 ? s[maxK] % MOD : 0;
            }
        }

        return (int) (Arrays.stream(f[n - 1], 0, nums[n - 1] + 1).sum() % MOD); //加和
    }
}