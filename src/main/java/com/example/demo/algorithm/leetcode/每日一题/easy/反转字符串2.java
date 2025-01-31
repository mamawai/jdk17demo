package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 反转字符串2 {
    public static void main(String[] args) {
        String s = new Solution541().reverseStr("abcdefg", 2);
        System.out.println(s);
    }
}

class Solution541 {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i += 2 * k) {
            for (int j = Math.min(n - 1, i + k - 1); j >= i; j--) sb.append(s.charAt(j));
            if (i + k <= n) sb.append(s, i + k, Math.min(n, i + 2 * k));
        }
        return sb.toString();
    }
}

class Solution541B {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, arr.length) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}