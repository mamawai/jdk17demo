package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;

public class 切蛋糕的最小总开销2 {
    public static void main(String[] args) {

    }
}


class Solution3219 {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        long res = 0L;
        int h = 0, v = 0;
        for (int i = m - 2, j = n - 2; ;) {
            if (i < 0 && j < 0) break;
            if (i >= 0 && j >= 0) {
                if (horizontalCut[i] >= verticalCut[j]) {
                    res += (long) horizontalCut[i--] * (v + 1);
                    h++;
                } else {
                    res += (long) verticalCut[j--] * (h + 1);
                    v++;
                }
            } else if (i >= 0) {
                res += (long) horizontalCut[i--] * (v + 1);
                h++;
            } else {
                res += (long) verticalCut[j--] * (h + 1);
                v++;
            }
        }
        return res;
    }
}