package com.example.demo.algorithm.leetcode;

import java.util.*;

public class N字形变换 {
    public static void main(String[] args) {
        String converted = new Solution6().convert("AB", 1);
        System.out.println(converted);
    }
}

class Solution6 {
    public String convert(String s, int numRows) {
        // putStrategy 以 1,2,3...numRows...,3,2 为一个周期
        // 共需要numRows个list存放字符
        StringBuilder[] nArray = new StringBuilder[numRows];
        int index = 0;
        boolean upFlag = true;
        StringBuilder ans = new StringBuilder();
        if (numRows == 1) return s;
        for (int j = 0; j < s.length(); j++) {
            if (nArray[index] == null) {
                nArray[index] = new StringBuilder();
                nArray[index].append(s.charAt(j));
            } else {
                nArray[index].append(s.charAt(j));
            }
            // 判断index的递增或递减
            if (index < numRows - 1 && upFlag) {
                index++;
            } else {
                upFlag = false;
                index--;
                if (index == 0) {
                    upFlag = true;
                }
            }
        }
        for (StringBuilder stringBuilder : nArray) {
            if (stringBuilder == null) {
                break;
            }
            ans.append(stringBuilder);
        }
        return ans.toString();
    }
}