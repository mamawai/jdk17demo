package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 跳跃游戏2 {
    public static void main(String[] args) {
        int jump = new Solution45().jump(new int[]{2,3,1,1,4,1,1,1,1});
        System.out.println(jump);
    }
}

class Solution45 {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int maxL = nums[0] + 1;
        int r = nums[0];
        int l = 1;
        int step = 1;
        while(maxL < nums.length) {
            step++;
            int max = 0;
            for(int i = l; i <= r; i++) {
                if (max <= nums[i] + i) {
                    max = nums[i] + i;
                }
                l = i + 1;
            }
            r = max;
            maxL = r + 1;
        }
        return step;
    }
}