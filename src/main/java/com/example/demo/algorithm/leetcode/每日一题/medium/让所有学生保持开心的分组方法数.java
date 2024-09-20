package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 让所有学生保持开心的分组方法数 {
    public static void main(String[] args) {
        int i = new Solution2860().countWays(Arrays.asList(1,1));
        System.out.println(i);
    }
}

class Solution2860 {
    public int countWays(List<Integer> nums) {
        int[] numbers = nums.stream().mapToInt(i -> i).toArray();
        Arrays.sort(numbers);
        int res = 1; // 全选
        if (numbers[0] != 0) res++; // 全不选
        int cnt = 0;
        int i = 0;
        for (; i < numbers.length - 1; i++) {
            cnt++;
            if (cnt > numbers[i] && cnt < numbers[i + 1]) res++;
        }
        return res;
    }
}