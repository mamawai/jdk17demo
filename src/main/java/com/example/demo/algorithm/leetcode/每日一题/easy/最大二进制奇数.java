package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 最大二进制奇数 {
    public static void main(String[] args) {
        String s = new Solution2864().maximumOddBinaryNumber("010");
        System.out.println(s);
    }
}

class Solution2864 {
    public String maximumOddBinaryNumber(String s) {
        String ans = "1";
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        String[] array = s.split("");
        for (String word : array) {
            if (word.equals("1")) {
                sb.append("1");
            } else {
                sb1.append("0");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).append(sb1).append(ans).toString();
    }
}