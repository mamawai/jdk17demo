package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 最小区间 {
    public static void main(String[] args) {
        ArrayList<List<Integer>> nums = new ArrayList<>();
        List<Integer> list = Arrays.asList(4, 10, 15, 24, 26);
        List<Integer> list1 = Arrays.asList(0, 9, 12, 20);
        List<Integer> list2= Arrays.asList(5, 18, 22, 30);
        nums.add(list);
        nums.add(list1);
        nums.add(list2);
        int[] ints = new Solution632().smallestRange(nums);
        System.out.println(Arrays.toString(ints));
    }
}

class Solution632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] ans = new int[]{-100000, 1000000}; // 初始化ans
        List<int[]> wholeNumsList = new ArrayList<>(); // 整合所有数字 并记录每个数字的来源i
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            for (int num : list) {
                wholeNumsList.add(new int[]{num, i}); // .[0]是num .[1]是idx
            }
        }
        Collections.sort(wholeNumsList, (a, b) -> a[0] - b[0]);// 排序所有数字
        // 实现滑动窗口
        int l = 0, r = 0;
        Set<Integer> isExist = new HashSet<>(); // 是否存在
        int[] existTimes = new int[nums.size()]; // 存在的次数
        isExist.add(wholeNumsList.get(l)[1]);
        existTimes[wholeNumsList.get(l)[1]]++;
        while (true) {
            if (isExist.size() < nums.size()) { // 如果窗口内数字个数小于nums.size()，说明没覆盖全，则继续向右滑动窗口
                r++;
                if (r >= wholeNumsList.size()) break;
                isExist.add(wholeNumsList.get(r)[1]);
                existTimes[wholeNumsList.get(r)[1]]++;
            } else { // 当覆盖全了记录一次ans
                if (wholeNumsList.get(r)[0] - wholeNumsList.get(l)[0] < ans[1] - ans[0]
                        || (wholeNumsList.get(r)[0] - wholeNumsList.get(l)[0] == ans[1] - ans[0] && wholeNumsList.get(l)[0] < ans[0])) {
                    ans[0] = wholeNumsList.get(l)[0];
                    ans[1] = wholeNumsList.get(r)[0];
                }
                if (existTimes[wholeNumsList.get(l)[1]] == 1) isExist.remove(wholeNumsList.get(l)[1]);
                existTimes[wholeNumsList.get(l)[1]]--;
                l++; // 滑动l
            }
        }
        return ans;
    }
}