package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 括号生成 {
    public static void main(String[] args) {
        List<String> list = new Solution22().generateParenthesis(8);
        System.out.println(list);
        // ())(()
    }
}

class Solution22 {
    private int n;
    private final List<Integer> path = new ArrayList<>();
    private final List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        this.n = n;
        dfs(0, 0);
        return ans;
    }

    // balance = 左括号个数 - 右括号个数
    private void dfs(int i, int balance) {
        if (path.size() == n) {
            char[] s = new char[n * 2];
            Arrays.fill(s, ')');
            for (int j : path) s[j] = '(';
            ans.add(new String(s));
            return;
        }
        // 可以填 0 到 balance 个右括号
        for (int close = 0; close <= balance; ++close) { // 填 close 个右括号
            path.add(i + close); // 填 1 个左括号
            dfs(i + close + 1, balance - close + 1);
            path.remove(path.size() - 1);
        }
    }
}

class Solution22B {
    private int n;
    private char[] path;
    private final List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        this.n = n;
        path = new char[n * 2];
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int open) {
        if (i == n * 2) {
            ans.add(new String(path));
            return;
        }
        if (open < n) { // 可以填左括号
            path[i] = '(';
            dfs(i + 1, open + 1);
        }
        if (i - open < open) { // 可以填右括号
            path[i] = ')';
            dfs(i + 1, open);
        }
    }
}