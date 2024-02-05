package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 石子游戏6 {
    public static void main(String[] args) {

    }
}

class Solution1686 {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        // 要计算选择之后 aliceSum - bobSum的最大值
        int aSum = 0;
        int bSum = 0;
        int[][] sum = new int[aliceValues.length][2];
        for (int i = 0; i < aliceValues.length; i++) {
            sum[i][0] = aliceValues[i] + bobValues[i];
            sum[i][1] = i;
        }
        List<int[]> list = Arrays.stream(sum).sorted((o1, o2) -> o2[0] - o1[0]).toList();
        for (int i = 0; i < list.size(); i++) {
            int[] arr = list.get(i);
            if (i%2 == 0) {
                aSum+=aliceValues[arr[1]];
            } else {
                bSum+=bobValues[arr[1]];
            }
        }
        return Integer.compare(aSum, bSum);
    }
}