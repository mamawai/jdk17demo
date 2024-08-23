package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 找出与数组相加的整数2 {
    public static void main(String[] args) {
        int i = new Solution3132().minimumAddedInteger(new int[]{7,10,9,2,5,8,1,0,4,0}, new int[]{10,1,2,1,6,3,9,8});
        System.out.println(i);
    }
}

class Solution3132 {
        public int minimumAddedInteger(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            // 枚举保留 nums1[2] 或者 nums1[1] 或者 nums1[0]
            // 倒着枚举是因为 nums1[i] 越大答案越小，第一个满足的就是答案
            for (int i = 2; i > 0; i--) {
                int x = nums2[0] - nums1[i];
                // 在 {nums1[i] + x} 中找子序列 nums2
                int j = 0;
                for (int k = i; k < nums1.length; k++) {
                    if (nums2[j] == nums1[k] + x && ++j == nums2.length) {
                        // nums2 是 {nums1[i] + x} 的子序列
                        return x;
                    }
                }
            }
            // 题目保证答案一定存在
            return nums2[0] - nums1[0];
        }

}