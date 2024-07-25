package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 重新放置石块 {
    public static void main(String[] args) {
        List<Integer> list = new Solution2766().relocateMarbles(
                new int[]{1,4,10,24,46,55,61,63,71},
                new int[]{10,52,1,80,63,55,4,46,71,24},
                new int[]{52,42,80,55,50,62,60,17,46,38});
        System.out.println(list);
    }
}

class Solution2766 {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        int n = moveFrom.length;
        Set<Integer> fromSet = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        Set<Integer> numSet = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            fromSet.add(moveFrom[i]);
            for (int j = n - 1; j >= 0 ; j--) {
                if (i == j) {
                    set.add(moveTo[i]);
                    break;
                }
                if (moveTo[i] == moveFrom[j]) break;
            }
        }
        for (int num : nums) if (!fromSet.contains(num)) numSet.add(num);
        numSet.addAll(set);
        List<Integer> res = new ArrayList<>(numSet);
        Collections.sort(res);
        return res;
    }
}