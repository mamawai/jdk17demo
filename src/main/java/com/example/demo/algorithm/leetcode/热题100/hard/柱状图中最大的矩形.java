package com.example.demo.algorithm.leetcode.热题100.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 柱状图中最大的矩形 {
    public static void main(String[] args) {
        int largestRectangleArea = new Solution84().largestRectangleArea(new int[]{6, 7, 5, 2, 4, 5, 9, 3});
        System.out.println(largestRectangleArea);
    }
}

class Solution84 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        // left right表示以该下标高度为基准围成的最大矩形面积
        Arrays.fill(right, n);
        // 存下标i 单调栈是以 height[i] 单调递增保存的
        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}