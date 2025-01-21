package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 统计打字方案数 {
    public static void main(String[] args) {
        int i = new Solution2266().countTexts("22222");
        System.out.println(i);
    }
}

class Solution2266 {
    public int countTexts(String pressedKeys) {
        int mod = 1000000007;
        int n = pressedKeys.length();
        int[] f = new int[n + 1];
        f[0] = 1; // 初始化f[0]
        int[] offsets = {0, 0, 2, 2, 2, 2, 2, 3, 2, 3}; // 存储每个数字对应的按键次数 - 1， 往前面遍历的次数
        for (int i = 0; i < n; i++) {
            int num = pressedKeys.charAt(i) - '0';
            if (i != 0) {
                f[i + 1] = f[i];
                for (int j = i - 1; j >= Math.max(i - offsets[num], 0); j--) {
                    if (pressedKeys.charAt(j) - '0' == num) f[i + 1] = (f[i + 1] + f[j]) % mod;
                    else break;
                }
            } else f[i + 1] = f[i];
        }
        return f[n];
    }
}