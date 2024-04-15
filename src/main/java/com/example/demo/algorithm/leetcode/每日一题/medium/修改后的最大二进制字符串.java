package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

public class 修改后的最大二进制字符串 {
    public static void main(String[] args) {
        String s = new Solution1702().maximumBinaryString("111");
        //
        System.out.println(s);
    }
}

class Solution1702 {
    public String maximumBinaryString(String binary) {
        int head = 0;
        int tail = 0;
        boolean flag = true;
        for (char c : binary.toCharArray()) {
            if (c == '1' && flag) head++;
            else if (c == '0') flag = false;
            else if (c == '1') tail++;
        }
        int restLen = binary.length() - tail - head;
        if (restLen > 0) {
            return "1".repeat(head + restLen - 1) + "0" + "1".repeat(tail);
        } else {
            return "1".repeat(head + tail);
        }
    }
}