package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 给小朋友们分糖果1 {
    public static void main(String[] args) {
        long l = new Solution2929().distributeCandies(3, 3);
        System.out.println(l);
    }
}

class Solution2929 {
    public long distributeCandies(int n, int limit) {
        long ans = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            if (n - i > 2 * limit) {
                continue;
            }
            ans += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }
        return ans;
    }
}