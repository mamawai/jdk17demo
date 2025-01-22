package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 你可以获得的最大硬币数目 {
    public static void main(String[] args) {

    }
}

class Solution1561 {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int res = 0;
        int i = 0;
        int j = piles.length - 2;
        while (i < j) {
            i++;
            res += piles[j];
            j -= 2;
        }
        return res;
    }
}