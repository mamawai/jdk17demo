package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class 找出不同元素数目差数组 {
    public static void main(String[] args) {
        int[] ints = new Solution2670().distinctDifferenceArray(new int[]{3, 2, 3, 4, 2});

    }
}

class Solution2670 {
    public int[] distinctDifferenceArray(int[] nums) {
        HashSet<Integer> integers = new HashSet<>();
        HashSet<Integer> hashSet = new HashSet<>();
        LinkedList<Integer> dupIdx = new LinkedList<>();
        int times = 0;
        int dis = 0;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            boolean isDistinct = integers.add(num);
            if (!isDistinct) {
                dupIdx.push(i);
            } else {
                dis++;
            }
        }
        int dupPre = 0;
        // 从后向前遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int suffixDistinctSize = hashSet.size();
            ans[i] = dis - (nums.length - i - 1) + dupPre - suffixDistinctSize;
            // 后缀延迟计算size
            hashSet.add(num);
            // 前缀重复延迟计算
            if (dupIdx.size() > 0 && dupIdx.peek() == i) {
                dupPre++;
                dupIdx.pop();
            }
        }
        return ans;
    }
}