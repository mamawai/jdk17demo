package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 划分数组得到最小的值之和 {
    public static void main(String[] args) {
        int i = new Solution3117().minimumValueSum(new int[]{2,3,5,7,7,7,5}, new int[]{0,7,5});
        System.out.println(i);
        ArrayList<String> binary = new ArrayList<String>();
        long number = (long) 5 << 36 | (long) 1 << 32 | 7;
        while(number != 0){
            binary.add(String.valueOf(number%2));
            number /= 2;
        }
        Collections.reverse(binary);
        for (String s : binary) {
            System.out.print(s);
        }
    }
}

class Solution3117 {
    public int minimumValueSum(int[] nums, int[] andValues) {
        Map<Long, Integer> memo = new HashMap<>();
        int ans = dfs(0, 0, -1, nums, andValues, memo);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    private int dfs(int i, int j, int and, int[] nums, int[] andValues, Map<Long, Integer> memo) {
        int n = nums.length;
        int m = andValues.length;
        if (n - i < m - j) { // 剩余元素不足
            return Integer.MAX_VALUE / 2; // 除 2 防止下面 + nums[i] 溢出
        }
        if (j == m) { // 分了 m 段
            return i == n ? 0 : Integer.MAX_VALUE / 2;
        }
        and &= nums[i];
        // 三个参数压缩成一个 long
        long mask = (long) i << 36 | (long) j << 32 | and;
        if (memo.containsKey(mask)) { // 之前计算过
            return memo.get(mask);
        }
        int res = dfs(i + 1, j, and, nums, andValues, memo); // 不划分
        if (and == andValues[j]) { // 划分，nums[i] 是这一段的最后一个数
            res = Math.min(res, dfs(i + 1, j + 1, -1, nums, andValues, memo) + nums[i]);
        }
        memo.put(mask, res); // 记忆化
        return res;
    }
}