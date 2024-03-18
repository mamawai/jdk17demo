package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;
import java.util.stream.Collectors;

public class 摧毁一系列目标 {
    public static void main(String[] args) {
        int i = new Solution2453().destroyTargets(new int[]{5,2}, 4);
        System.out.println(i);
    }
}

class Solution2453 {
    public int destroyTargets(int[] nums, int space) {
        /*
        预处理+枚举:
        1.枚举每个数字，计算出对space取余后的结果，这个结果范围在[0,space-1]
        2.只要取余后结果一致，那么必定存在一个最小值使得所有同余数的点被摧毁
        3.只要找出出现次数最多的几个余数，然后再再这些余数之中找出对应的最小的nums[i]就是答案
        时间复杂度:O(N) 空间复杂度:O(N)
         */
        HashMap<Integer, Integer> map = new HashMap<>(); // 统计余数出现次数
        List<Integer> list = new ArrayList<>(); // 存储出现次数最多的数字
        int maxNum = -1;    // 当前出现次数最多的余数
        for (int num : nums) {
            int mod = num % space;
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            int curNum = map.get(key);
            if (curNum > maxNum) {
                maxNum = curNum;
                list.clear();
                list.add(key);
            } else if (curNum == maxNum) {
                list.add(key);
            }
        }
        int res = 0x3f3f3f3f;
        for (int num : Arrays.stream(nums).sorted().boxed().toList()) {
            int mod = num % space;
            if (list.contains(mod) && num < res) res = num;
        }
        return res;
    }
}