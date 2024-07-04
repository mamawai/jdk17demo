package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 质数的最大距离 {
    public static void main(String[] args) {
        int i = new Solution3115().maximumPrimeDifference(new int[]{4, 2, 9, 5, 3});
        System.out.println(i);
    }
}

class Solution3115 {
    public int maximumPrimeDifference(int[] nums) {
        // 一百以内质数
        int[] primes = {
                2, 3, 5, 7, 11,
                13, 17, 19, 23, 29,
                31, 37, 41, 43, 47,
                53, 59, 61, 67, 71,
                73, 79, 83, 89, 97
        };
        boolean[] isPrime = new boolean[101];
        for (int prime : primes) {
            isPrime[prime] = true;
        }
        // 最大距离那么一定是第一次出现质数的下标 和 最后一次出现质数的下标
        // 双指针左右一起找呗
        int left = 0;
        int right = nums.length - 1;
        boolean firstR = false;
        boolean firstL = false;
        while (right >= left) {
            if (firstR && firstL) break;
            if (!isPrime[nums[right]] && !firstR) {
                right--;
            } else if (isPrime[nums[right]]) firstR = true;
            if (!isPrime[nums[left]] && !firstL) {
                left++;
            } else if (isPrime[nums[left]]) firstL = true;
        }
        return right - left;
    }
}