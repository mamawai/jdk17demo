package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 负二的进制转换 {
    public static void main(String[] args) {

    }
}

class Solution1017 {
    public String baseNeg2(int n) {
        if(n == 0)return "0";   // 0直接返回
        StringBuilder res = new StringBuilder();
        // 类似十进制转二进制的方法，只是余数如果为负数需要修正
        while(n != 0){
            int mod = n % (-2);
            n = n / (-2);
            if(mod == -1){
                // 修正余数为-1->1，对应商也要加1
                n++;
                mod = 1;
            }
            res.append(mod);
        }
        return res.reverse().toString();     // 生成的二进制是逆序的，需要反转
    }
}