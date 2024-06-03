package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 分糖果2 {
    public static void main(String[] args) {
        int[] ints = new Solution1103().distributeCandies(60, 4);

    }
}

class Solution1103 {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int times = 1;
        while (candies > 0) {
            for (int i = 0; i < ans.length; i++) {
                if (candies <= 0) break;
                ans[i] += Math.min(times, candies);
                candies -= times++;
            }
        }
        return ans;
    }
}