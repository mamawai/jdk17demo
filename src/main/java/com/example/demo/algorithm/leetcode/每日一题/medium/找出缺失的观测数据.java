package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class 找出缺失的观测数据 {
    public static void main(String[] args) {
        int[] ints = new Solution2028().missingRolls(new int[]{3, 2, 4, 3}, 4, 2);
        System.out.println("");
    }
}

class Solution2028 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int rest = (m + n) * mean;
        for (int roll : rolls) {
            rest = rest - roll;
        }
        if (rest <= 6 * n && rest >= n) {
            int[] ans = new int[n];
            int h = rest / n;
            int k = rest - h * n;
            Arrays.fill(ans, h);
            for (int i = 0; i < k; i++) {
                ans[i]++;
            }
            return ans;
        }
        return new int[]{};
    }
}