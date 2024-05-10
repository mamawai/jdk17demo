package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 盛最多水的容器 {
    public static void main(String[] args) {
        int i = new Solution11().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(i);
    }
}

class Solution11 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, (r - l) * Math.min(height[l], height[r]));
            if (height[l] >= height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return maxArea;
    }
}