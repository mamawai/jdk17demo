package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.test.StringUtils;

public class 字符串及其反转中是否存在同一子字符串 {
    public static void main(String[] args) {
        boolean isTrue = new Solution3083().isSubstringPresent("lfeabcaef");
        System.out.println(isTrue);
    }
}

class Solution3083 {
    public boolean isSubstringPresent(String s) {
        char[] arr = s.toCharArray();
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[i] == arr[j] && arr[i - 1] == arr[j + 1]) return true;
            }
        }
        return false;
    }
}
