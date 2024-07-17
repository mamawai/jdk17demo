package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.HashMap;
import java.util.Map;

public class 找到两个数组中的公共元素 {
    public static void main(String[] args) {

    }
}
class Solution2956 {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int ans1 = 0;
        int ans2 = 0;
        int[] arr = new int[101];
        for (int n1 : nums1) {
            arr[n1]++;
        }
        for (int n2 : nums2) {
            if (arr[n2] > 0) {
                ans1 += arr[n2];
                arr[n2] = -1;
                ans2++;
            } else if (arr[n2] == -1) ans2++;
        }
        return new int[]{ans1, ans2};
    }
}