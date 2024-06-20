package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 跳跃游戏 {
    public static void main(String[] args) {
        boolean b = new Solution55().canJump(new int[]{3,2,1,0,4});
        System.out.println(b);
    }
}

class Solution55 {
    boolean[] memo;
    public boolean canJump(int[] nums) {
        memo = new boolean[nums.length];
        Arrays.fill(memo, true);
        if (nums.length == 1) return true;
        return jump(0, nums);
    }

    private boolean jump(int pos, int[] nums) {
        if (memo[pos]) {
            for (int i = nums[pos]; i >= 1; i--) {
                int newPos = pos + i;
                if (newPos == nums.length - 1) return true;
                if (newPos > nums.length - 1) continue;
                if (jump(newPos, nums)) {
                    return true;
                }
            }
            memo[pos] = false;
        }
        return memo[pos];
    }
}