package com.example.demo.algorithm.leetcode.每日一题;

import java.util.LinkedList;
import java.util.Map;

public class 最长交替子数组 {
    public static void main(String[] args) {
        int i = new Solution2765().alternatingSubarray(new int[]{4,5,6,5,6});
        System.out.println(i);
    }
}

class Solution2765 {
    public int alternatingSubarray(int[] nums) {
        int l = 0;
        int r = 1;
        int n = nums.length;
        int count = 0;
        int last = 0;
        int res = 0;
        for (; r < n && l < n ; r++, l++) {
            int minus = nums[r] - nums[l];
            if (count == 0 && minus == 1) {
                count++;
            } else {
                if (count > 0) {
                    if (last == -minus) {
                        count++;
                    } else {
                        res = Math.max(res, count + 1);
                        count = 0;
                        if (minus == 1) {
                            count++;
                        }
                    }
                }
            }
            last = minus;
        }
        res  = Math.max(res, count + 1);
        return res == 1 ? -1 : res;
    }
}