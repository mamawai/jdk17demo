package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.Arrays;
import java.util.Map;

public class 分糖果 {
    public static void main(String[] args) {

    }
}

class Solution575 {
    public int distributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        int n = candyType.length;
        int eat = n / 2;
        int tar = 0;
        int distinct = 0;
        for (int ct : candyType) {
            if (ct != tar) {
                tar = ct;
                distinct++;
            }
        }
        return Math.min(eat, distinct);
    }
}