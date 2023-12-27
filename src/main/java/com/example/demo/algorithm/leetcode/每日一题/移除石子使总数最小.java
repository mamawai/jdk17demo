package com.example.demo.algorithm.leetcode.每日一题;

import java.util.*;

public class 移除石子使总数最小 {
    public static void main(String[] args) {
        int min = new Solution1962().minStoneSum(new int[]{4,3,6,7}, 3);
        System.out.println(min);
    }
}

class Solution1962 {
    public int minStoneSum(int[] piles, int k) {
        // 构造大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int pile : piles) {
            maxHeap.offer(pile);
        }
        for (int i = 0; i < k; i++) {
            Integer poll = maxHeap.poll();
            int res = poll - poll / 2;
            maxHeap.add(res);
        }
        return maxHeap.stream().reduce(0, Integer::sum);
    }
}