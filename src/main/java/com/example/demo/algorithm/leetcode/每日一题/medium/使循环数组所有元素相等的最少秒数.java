package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 使循环数组所有元素相等的最少秒数 {
    public static void main(String[] args) {

    }
}

class Solution2808 {
    public int minimumSeconds(List<Integer> nums) {
        HashMap<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            mp.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        int minTimes = Integer.MAX_VALUE;
        for (List<Integer> positions : mp.values()) {
            // 计算首尾下标的距离  11211
            int sw = nums.size() - (positions.get(positions.size() - 1) - positions.get(0));
            // 遍历计算每两个下标之间最大的差
            int maxMinus = 0;
            if (positions.size() > 1) {
                for (int i = 0; i < positions.size() - 1; i++) {
                    maxMinus = Math.max(maxMinus, positions.get(i+1) - positions.get(i));
                }
            }
            maxMinus = Math.max(maxMinus, sw);
            minTimes = Math.min(maxMinus, minTimes);
        }
        return minTimes/2;
    }
}