package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 接雨水 {
    public static void main(String[] args) {
        int trap = new Solution42().trap(new int[]{6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3});
        System.out.println(trap);
    }
}

class Solution42 {
    public int trap(int[] height) {
        int total = 0;
        int black = 0;
        int left = 0;
        int right = height.length - 1;
        int calculateH = 0;
        while (left <= right) {
            if (Math.min(height[left], height[right]) > calculateH) {
                total += (right - left + 1) * (Math.min(height[left], height[right]) - calculateH);
                calculateH = Math.min(height[left], height[right]);
            }
            if (height[left] <= height[right]) {
                if (height[left] > calculateH) {
                    total += height[left] - calculateH;
                }
                black += height[left];
                left++;
            } else {
                if (height[right] > calculateH) {
                    total += height[right] - calculateH;
                }
                black += height[right];
                right--;
            }
        }
        return total - black;
    }
}