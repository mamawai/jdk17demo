package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.HashMap;
import java.util.Map;

public class 情侣牵手 {
    public static void main(String[] args) {
        int i = new Solution765().minSwapsCouples(new int[]{0, 2, 4, 6, 7, 1, 3, 5});
    }
}

class Solution765 {
    public int minSwapsCouples(int[] row) {
        int[] map = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            // row[i] 作为下标
            map[row[i]] = i;
        }
        int swapTimes = 0;
        for (int i = 0;i < row.length - 1;i+=2) {
            int j = i + 1;
            if (!(Math.abs(row[i] - row[j]) == 1 && Math.min(row[i], row[j])%2 == 0)) {
                // 交换其中的一个
                int idx = -1;
                if (row[i]%2==0) {
                    idx = map[row[i] + 1];
                } else {
                    idx = map[row[i] - 1];
                }
                int tmp = row[idx];
                row[idx] = row[j];
                row[j] = tmp;
                map[row[j]] =j;
                map[row[idx]] = idx;
                swapTimes++;
            }
        }
        return swapTimes;
    }
}