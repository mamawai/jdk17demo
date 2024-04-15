package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;

public class 使数组连续的最少操作数 {
    public static void main(String[] args) {
        int i = new Solution2009().minOperations(new int[]{3,4,5,6,7});
        System.out.println(i);
    }
}

class Solution2009 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = n - 1;
        Arrays.sort(nums);
        // 问题转化：任意两个数字之差小于等于 n - 1 且这两个数组中间夹着的数字个数最大 | 如果任意两个数字之间的差都大于 n - 1 那么需要替换 n - 1个数字
        int contains = 1;// 指无需替换的数字个数
        int minusSum = 0;// 指窗口左右端点的数字之差
        int removeIndex = 0;// 负责滑动左窗口的下标
        int[] minus = new int[n - 1];// 两两元素之差数组
        // 遍历一遍两两做差 滑动窗口
        for (int i = 0, j = 1; j < n; i++, j++) {
            // 这里注意对窗口内重复元素的处理
            minusSum += minus[i] = nums[j] - nums[i];
            if (minus[i] != 0) contains++;// 出现重复元素contains不++ 因为重复元素一定要替换
            // while循环 直到 minusSum <= n - 1
            while (minusSum > n - 1) {
                minusSum -= minus[removeIndex];
                if (minus[removeIndex++] != 0) contains--;// 重复元素被移除contains不-- 因为重复元素一定要替换
            }
            ans = Math.min(ans, n - contains);
        }
        return ans;
    }
}
