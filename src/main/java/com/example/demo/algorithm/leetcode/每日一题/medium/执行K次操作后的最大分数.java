package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class 执行K次操作后的最大分数 {
    public static void main(String[] args) {

    }
}

class Solution2530 {
    public long maxKelements(int[] nums, int k) {
        long ans = 0L;
        Queue<Integer> queue = Arrays.stream(nums).boxed().collect(Collectors.toCollection(() -> new PriorityQueue<>((o1, o2) -> o2 - o1)));
        for (int i = 0; i < k; i++) {
            Integer polled = queue.poll();
            ans += polled;
            queue.offer((int) Math.ceil((double) polled /3));
        }
        return ans;
    }
}