package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.PriorityQueue;

public class 超过阈值的最小操作数2 {
    public static void main(String[] args) {

    }
}

class Solution3066 {
    public int minOperations(int[] nums, int k) {
        int ans = 0;
        PriorityQueue<Long> queue = new PriorityQueue<>((o1, o2) -> (int) (o1 - o2));
        for (int num : nums) queue.offer((long) num);
        while (queue.size() > 1 && queue.peek() < k) {
            long a = queue.poll();
            long b = queue.poll();
            queue.offer(Math.min(a, b) * 2 + Math.max(a, b));
            ans++;
        }
        return ans;
    }
}