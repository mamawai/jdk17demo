package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 魔塔游戏 {
    public static void main(String[] args) {
        int i = new SolutionLC30().magicTower(new int[]{1,1,-1,-1});
        System.out.println(i);
    }
}

class SolutionLC30 {
    public int magicTower(int[] nums) {
        long cur = 0;
        long toMinus = 0;
        int times = 0;
        // 小顶堆
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        for (int num : nums) {
            if (num < 0) neg.offer(num);
            cur += num;
            // 当出现第一个小于零的前缀和就先把最小的负数剔除 poll到appendNum中并记录次数
            while (cur < 0) {
                Integer polled = neg.poll();
                toMinus += polled;
                cur -= polled;
                times++;
            }
        }
        if (cur + toMinus < 0) {
            return -1;
        }
        return times;
    }
}