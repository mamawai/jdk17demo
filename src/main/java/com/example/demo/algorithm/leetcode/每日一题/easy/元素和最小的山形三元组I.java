package com.example.demo.algorithm.leetcode.每日一题.easy;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 元素和最小的山形三元组I {
    public static void main(String[] args) {
        int i = new Solution2908().minimumSum(new int[]{43,42,31,37,15,15,10,36,23});
        System.out.println(i);
    }
}

class Solution2908 {
    public int minimumSum(int[] nums) {
//        Queue<Integer> pairs = new PriorityQueue<>((o1, o2) -> o1 - o2);
//        pairs.add(nums[1]);
//        for (int i = 2; i < nums.length; i++) {
//            int num = nums[i];
//            pairs.add(num);
//        }

        int[] ints = new int[nums.length];
        ints[nums.length -1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i > 1; i --) {
            ints[i] = Math.min(nums[i], ints[i + 1]);
        }
        int ans = Integer.MAX_VALUE;
        int leftMin = nums[0];
//        for (int i = 1; i < nums.length - 1; i++) {
//            if (nums[i] > leftMin && pairs.peek() < nums[i]) {
//                ans = Math.min(ans, nums[i] + leftMin + pairs.peek());
//            }
//            leftMin = Math.min(leftMin, nums[i]);
//            pairs.remove(nums[i]);
//        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > leftMin && ints[i + 1] < nums[i]) {
                ans = Math.min(ans, nums[i] + leftMin + ints[i + 1]);
            }
            leftMin = Math.min(leftMin, nums[i]);
        }
        return ans == Integer.MAX_VALUE ? -1 :ans;
    }
}