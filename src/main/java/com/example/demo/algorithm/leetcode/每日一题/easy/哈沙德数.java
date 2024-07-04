package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 哈沙德数 {
    public static void main(String[] args) {

    }
}

class Solution3099 {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int k = x;
        int n = 0;
        while (x > 0) {
            int i = x % 10;
            x /= 10;
            n += i;
        }
        return (k % n) == 0 ? n : -1;
    }
}