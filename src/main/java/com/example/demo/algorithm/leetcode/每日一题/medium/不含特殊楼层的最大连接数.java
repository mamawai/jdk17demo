package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 不含特殊楼层的最大连接数 {
    public static void main(String[] args) {

    }
}

class Solution2274 {
    public int maxConsecutive(int bottom, int top, int[] special) {
        int res = 0;
        Arrays.sort(special);
        for (int i = 0; i < special.length - 1; i++) res = Math.max(special[i + 1] - special[i] - 1, res);
        return Math.max((Math.max(special[0] - bottom, top - special[special.length - 1])), res);
    }
}