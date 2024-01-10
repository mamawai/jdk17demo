package com.example.demo.algorithm.leetcode.每日一题;

import java.util.ArrayDeque;
import java.util.Deque;

public class 队列中可以看到的人数 {
    public static void main(String[] args) {
        int[] ints = new Solution1944().canSeePersonsCount(new int[]{10, 6, 8, 5, 11, 9});;
    }
}
/**
 * 单调栈
 */
class Solution1944 {
    public int[] canSeePersonsCount(int[] heights) {
        // 从右到左入栈
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            int count = 0;
            int h = heights[i];
            if (stack.isEmpty()) {
                stack.push(h);
            } else {
                while (!stack.isEmpty() && h >= stack.peek()) {
                    stack.pop();
                    count++;
                }
                stack.push(h);
                if (stack.size() > 1) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }
}