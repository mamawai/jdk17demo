package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 使字符串平衡的最小交换次数 {
    public static void main(String[] args) {

    }
}

// 找左面第一个右括号 跟找右面第一个左括号交换
class Solution1963 {
    public int minSwaps(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int c = 0;
        int j = s.length - 1;
        for (char b : s) {
            if (b == '[') {
                c++;
            } else if (c > 0) {
                c--;
            } else { // c == 0
                // 找最右边的左括号交换
                while (s[j] == ']') {
                    j--;
                }
                s[j] = ']'; // s[i] = '[' 可以省略
                ans++;
                c++; // s[i] 变成左括号，c 加一
            }
        }
        return ans;
    }
}