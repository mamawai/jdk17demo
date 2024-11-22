package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.Map;

public class 执行操作可获得的最大总奖励1 {
    public static void main(String[] args) {
        int i = new Solution3180().maxTotalReward(new int[]{7,8,3,20});
        System.out.println(i);
    }
}

class Solution3180 {
    int[][] memo;
    public int maxTotalReward(int[] rewardValues) {
        // 取最后一个保证最大
        int n = rewardValues.length;
        Arrays.sort(rewardValues);
        int res = rewardValues[n - 1];
        memo = new int[n][2 * res];        // 记忆化搜索
        int restMax = res - 1;
        int i = res + dfs(rewardValues, 0, restMax, 0);
        return i;
    }

    private int dfs(int[] rewardValues, int i, int restMax, int total) {
        if (memo[i][total] != 0) return memo[i][total];
        if (total + rewardValues[i] > restMax) return total;// 选了第i位如果爆了就返回 total
        if (i != 0 && (rewardValues[i] == rewardValues[i - 1] || total >= rewardValues[i]))
            return memo[i][total] = dfs(rewardValues, i + 1, restMax, total);// 重复不选 或 总和大于该位不选
        else return memo[i][total] = Math.max(
                dfs(rewardValues, i + 1, restMax, total + rewardValues[i]) // 选了第i位
                , dfs(rewardValues, i + 1, restMax, total)// 没选第i位
        );
    }
}