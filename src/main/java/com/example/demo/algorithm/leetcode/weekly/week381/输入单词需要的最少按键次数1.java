package com.example.demo.algorithm.leetcode.weekly.week381;

public class 输入单词需要的最少按键次数1 {
    public static void main(String[] args) {

    }
}

class Solution3014 {
    public int minimumPushes(String word) {
        int length = word.length();
        if (length <= 8) return length;
        if (length <= 16) return (length - 8) * 2 + 8;
        if (length <= 24) return 8 + 16 + (length - 16) * 3;
        if (length <= 26) return 8 + 16 + 24 + (length - 24) * 4;
        return -1;
    }
}
