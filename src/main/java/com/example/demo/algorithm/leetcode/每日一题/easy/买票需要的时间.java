package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

public class 买票需要的时间 {
    public static void main(String[] args) {
        int i = new Solution2073().timeRequiredToBuy(new int[]{2, 3, 2}, 2);
        System.out.println(i);
    }
}

class Solution2073 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != 0) {
                tickets[i]--;
                ans++;
                if (tickets[i] == 0 && i == k) break;
            }
            if (i == tickets.length - 1) i = -1;
        }
        return ans;
    }
}