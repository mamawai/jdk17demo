package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.LinkedList;

public class 找出最具竞争力的子序列 {
    public static void main(String[] args) {
        int[] ints = new Solution1673().mostCompetitive(new int[]{2,4,3,3,5,4,9,6}, 4);
        System.out.println("");
    }
}

class Solution1673 {
    public int[] mostCompetitive(int[] nums, int k) {
        LinkedList<Integer> stack = new LinkedList<>();
        //  4,2,3,3,5,4,3,1,9,6,2
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
                while (!stack.isEmpty() && stack.peek() > nums[i]) {
                    // 虽然应该poll掉 但是要看剩余长度是否允许poll
                    // 剩余长度
                    int restSize = k - stack.size();
                    if (restSize < nums.length - i) {
                        // 此时poll
                        stack.poll();
                    } else if (restSize == nums.length - i) {
                        break;
                    }
                }
                if (stack.size() < k) stack.push(nums[i]);
        }
        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = stack.poll();
        }
        return ans;
    }
}