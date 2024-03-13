package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 距离相等的条形码 {
    public static void main(String[] args) {
        int[] ints = new Solution1054().rearrangeBarcodes(new int[]{1});

    }
}

class Solution1054 {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[barcodes.length];
        for (int i : barcodes) {
            map.put(i,map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int index = 0;
        int[] poll = pq.poll();
        int[] poll1 = pq.poll();
        if (poll1 == null) {
            return barcodes;
        }
        while (poll[1] != 0 || poll1[1] != 0) {
            if (poll[1] != 0 && poll1[1] != 0) {
                ans[index++] = poll[0];
                ans[index++] = poll1[0];
                poll[1]--;
                poll1[1]--;
                pq.offer(poll);
                pq.offer(poll1);
                poll = pq.poll();
                poll1 = pq.poll();
            } else if (poll[1] != 0) {
                ans[index] = poll[0];
                break;
            }
        }
        return ans;
    }
}