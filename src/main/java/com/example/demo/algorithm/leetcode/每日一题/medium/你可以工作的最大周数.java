package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class 你可以工作的最大周数 {
    public static void main(String[] args) {
        long l = new Solution1953().numberOfWeeks(new int[]{13,13,3,3,3,3,1});
        System.out.println(l);
    }
}

class Solution1953 {
    public long numberOfWeeks(int[] milestones) {
        int mx = 0;
        long s = 0;
        for (int e : milestones) {
            s += e;
            mx = Math.max(mx, e);
        }
        long rest = s - mx;
        return mx > rest + 1 ? rest * 2 + 1 : s;
    }
}