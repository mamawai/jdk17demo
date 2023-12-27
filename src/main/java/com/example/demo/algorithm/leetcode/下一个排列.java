package com.example.demo.algorithm.leetcode;

public class 下一个排列 {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
        new Solution31().nextPermutation(nums);
        for (int j :nums) {
            System.out.print(j);
        }
    }
}
/**
 * 比如 1235422 -> 先交换5422中比3大的第一个数字 1245322 ->再将5322反转 -> 1242235
 */
class Solution31 {
    public void nextPermutation(int[] nums) {
        // 12345 12354 12543
        // 77545
        // 1 2 5 4 3 3-> 1 2 5
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] >= nums[i+1]) {
                i--;
            } else {
                break;
            }
        }
        if (i == -1) {
            for (int k = 0; k < nums.length/2; k++){
                int tmp = nums[k];
                nums[k] = nums[nums.length - 1 - k];
                nums[nums.length - 1 - k] = tmp;
            }
        } else {
            int cur = nums[i];
            int p = nums.length - 1;
            while (p >= 0) {
                if (nums[p] > cur) {
                    break;
                }
                p--;
            }
            int tmp = nums[p];
            nums[p] = cur;
            nums[i] = tmp; // 1453322

            int k = i+1;
            int q = 0;
            int range = (nums.length - k) / 2+ k;
            for (; k < range;k++,q++) {
                int t = nums[k];
                nums[k] = nums[nums.length - 1 -q];
                nums[nums.length - 1 - q] = t;
            }

        }
    }
}