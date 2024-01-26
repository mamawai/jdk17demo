package com.example.demo.algorithm.leetcode.每日一题;

public class 构造限制重复的字符串 {
    public static void main(String[] args) {
        String s = new SolutionB2182().repeatLimitedString("cczazcc", 3);
        System.out.println(s);
    }
}

class Solution2182 {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder ret = new StringBuilder();
        int firstPositive = getFirstPositive(count, 25);
        dfs(count, repeatLimit, ret, -1, firstPositive);
        return ret.toString();
    }

    private static int getFirstPositive(int[] count, int firstPositive) {
        for (int i = firstPositive; i >= 0; i--) {
            if (count[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private void dfs(int[] count, int N, StringBuilder ret, int bound, int firstPositive) {
        int repeatLimit = N;
        for (int i = firstPositive; i > bound; i--) {
            while (count[i] > 0 && N > 0) {
                count[i]--;
                N--;
                ret.append(Character.toString(i+97));
                if (i < firstPositive) {
                    // 重新计算firstPositive
                    if (count[i] == 0) {
                        firstPositive = getFirstPositive(count, firstPositive);
                    }
                    int preL = ret.length();
                    dfs(count, repeatLimit, ret, i, firstPositive);
                    if (preL < ret.length()) {
                        N = repeatLimit;
                    }
                }
            }
            if (N == 0 || count[i] == 0) {
                N = repeatLimit;
            }
        }
    }
}

class SolutionB2182 {
    static public int N = 26;
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[N];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder ret = new StringBuilder();
        int m = 0;
        for (int i = N - 1, j = N - 2; i >= 0 && j >= 0;) {
            if (count[i] == 0) { // 当前字符已经填完，填入后面的字符，重置 m
                m = 0;
                i--;
            } else if (m < repeatLimit) { // 当前字符未超过限制
                count[i]--;
                ret.append((char)('a' + i));
                m++;
            } else if (j >= i || count[j] == 0) { // 当前字符已经超过限制，查找可填入的其他字符
                j--;
            } else { // 当前字符已经超过限制，填入其他字符，并且重置 m
                count[j]--;
                ret.append((char)('a' + j));
                m = 0;
            }
        }
        return ret.toString();
    }
}