package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.LinkedList;

public class 下一个更大元素2 {
    public static void main(String[] args) {
        int[] ints = new Solution503().nextGreaterElements(new int[]{1, 2, 2, 3, 2, 4, 5, 3});// 2,3,3,4,4,5,-1,4
        System.out.println(ints);
    }
}

class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> stack = new LinkedList<>();
        // 从后往前
        for (int i = 2 * n - 1; i >= 0; i--) {
            int x = nums[i % n];
            if (stack.isEmpty()) {
                stack.push(x);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() <= x) {
                stack.pop();
            }
            if (i < n && !stack.isEmpty()) ans[i] = stack.peek();
            stack.push(x);
        }
        return ans;
    }
}