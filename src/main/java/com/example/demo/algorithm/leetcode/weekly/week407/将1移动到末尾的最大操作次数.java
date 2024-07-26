package com.example.demo.algorithm.leetcode.weekly.week407;

public class 将1移动到末尾的最大操作次数 {
    public static void main(String[] args) {
        int i = new Solution407q3().maxOperations("1");
        System.out.println(i);
    }
}

class Solution407q3 {
    public int maxOperations(String s) {
        int ans = 0;
        int n = s.length();
        while (n > 0 && s.charAt(n - 1) == '1') {
            n--;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (s.charAt(i) == '1') {
                i++;
                cnt++;
                count++;
            }
            if (cnt > 0) {
                ans += count;
                i--;
            }
        }
        return ans;
    }
}