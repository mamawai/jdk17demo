package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.HashSet;
import java.util.Set;

public class 找出数组中的Kor值 {
    public static void main(String[] args) {
        int kOr = new Solution2917().findKOr(new int[]{7, 12, 9, 8, 9, 15}, 4);
        System.out.println(kOr);
    }
}

class Solution2917 {
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 31; ++i) {
            int cnt = 0;
            for (int num : nums) {
                // 右移num i位就是看当前i位是否是1 0的情况过滤掉
                if (((num >> i) & 1) != 0) {
                    ++cnt;
                }
                if (cnt >= k) {
                    ans |= 1 << i;
                    break;
                }
            }
        }
        return ans;
    }
}
