package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 爱生气的书店老板 {
    public static void main(String[] args) {
        int i = new Solution1052().maxSatisfied(new int[]{2,6,6,9}, new int[]{0,0,1,1}, 1);
        System.out.println(i);
    }
}

class Solution1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int ans = 0;
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0) ans+=customers[i];
        }
        // 定义窗口初始状态
        int l = 0;
        int r = minutes - 1;
        int cure = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) cure += customers[i];
        }
        int max = cure;
        while (r < grumpy.length - 1) {
            if (grumpy[l] == 1) cure -= customers[l];
            l++;
            if (grumpy[++r] == 1) cure += customers[r];
            max = Math.max(max, cure);
        }
        return ans + max;
    }
}
