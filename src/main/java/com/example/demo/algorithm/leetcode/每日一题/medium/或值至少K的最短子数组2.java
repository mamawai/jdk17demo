package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 或值至少K的最短子数组2 {
    public static void main(String[] args) {
        int i = new Solution3097B().minimumSubarrayLength(new int[]{2, 1, 8, 1, 1, 10, 1}, 10);
        System.out.println(i);
    }
}

class Solution3097 {
    public int minimumSubarrayLength(int[] nums, int k) {
        // 101 | 11 = 111
        int ans = Integer.MAX_VALUE;
        int[] bitSet = new int[30];
        int left = 0;
        int right = 0;
        int OR = 0;
        while (left < nums.length) {
            if (right < nums.length) {
                int num = nums[right];
                int idx = 0;
                while (num > 0) {
                    if ((num & 1) == 1) bitSet[idx]++; // 右端点记录到bitSet
                    num >>= 1;
                    idx++;
                }
                OR |= nums[right++]; // 记录当前按位或的和
            }
            if (OR >= k) { // 如果当前的按位或大于等于k，记录当前的长度
                ans = Math.min(ans, right - left);
                while (left < right) { // 左移端点直到 OR小于k
                    int leftNum = nums[left];
                    int leftIdx = 0;
                    while (leftNum > 0) {
                        if ((leftNum & 1) == 1) {
                            if (--bitSet[leftIdx] == 0) OR -= 1 << leftIdx;
                        }
                        leftNum >>= 1;
                        leftIdx++;
                    }
                    left++;
                    if (OR < k) break;
                    if (left != right) ans = Math.min(ans, right - left); // 这里也要更新一下零长度不更新
                }
            } else if (right == nums.length) break; // 如果右端点已经到头，OR小于k，左端点不用右移了直接break
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

class Solution3097B {
    public int minimumSubarrayLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x >= k) {
                return 1;
            }
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) { // 如果 x 是 nums[j] 的子集，就退出循环，因为或运算加上子集结果不变
                nums[j] |= x;
                if (nums[j] >= k) {
                    ans = Math.min(ans, i - j + 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}