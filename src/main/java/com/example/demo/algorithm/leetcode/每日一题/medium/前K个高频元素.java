package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;
import java.util.function.Consumer;

public class 前K个高频元素 {
    public static void main(String[] args) {

    }
}

class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        map.entrySet().forEach(queue::offer);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll().getKey();
        }
        return ans;
    }
}
