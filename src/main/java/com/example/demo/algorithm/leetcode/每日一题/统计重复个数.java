package com.example.demo.algorithm.leetcode.每日一题;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;

public class 统计重复个数 {
    public static void main(String[] args) {
        int maxRepetitions = new Solution466().getMaxRepetitions("acb", 1, "acb", 1);
        System.out.println(maxRepetitions);
    }
}

class Solution466 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // 找到循环节 思路是对的 4720 3282 3281 | 541 1235
        // 遍历s2
        // 当前s1的个数
        Map<int[], Integer> map = new HashMap<>();
        int s1_cnt = 0;
        int s2_cnt = 0;
        int cur = 0;
        Set<Integer> pos = new HashSet<>();
        for (int i = 0; i < s2.length(); i++) {

        }
        int finalCur = cur;
        int[] ints = {0, 0};
        int[] res = map.entrySet().stream()
                .filter(integerEntry -> integerEntry.getValue() == finalCur)
                .map(Map.Entry::getKey)
                .reduce(ints, (ints1, ints2) -> {
                    ints1[0] = Math.abs(ints1[0] - ints2[0]);
                    ints1[1] = Math.abs(ints1[1] - ints2[1]);
                    return ints1;
                });
        int everyLeg1 = res[0];
        int everyLeg2 = res[1];
        int before1 = 0;
        if (s1_cnt % everyLeg1 != 0) {
            before1 = s1_cnt - everyLeg1;
        }
        // 循环节个数
        int circleTimes = (n1 - before1) / everyLeg1;
        return circleTimes * everyLeg2 / n2;
    }
}