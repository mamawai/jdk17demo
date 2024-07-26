package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.test.StringUtils;

import java.util.List;

public class 快乐数 {
    public static void main(String[] args) {
        int sum = new SolutionB().getSum(6, 3);
        System.out.println(sum);
    }
}

class Solution202 {

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    // 快慢指针
    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}

class SolutionB {
    public int getSum(int a, int b) {
        //  110
        //   11
        // 拆分成 -> 101和100做和   while循环到b为0即进位结果为0
        //       -> 1和1000做和
        //       -> 1001和 0做和
        while (b != 0) {
            // 进位结果 100
            int carry = (a & b) << 1;
            // 无进位加法
            a = a ^ b;
            // 更新b为进位结果
            b = carry;
        }
        return a;
    }
}