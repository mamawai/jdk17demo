package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.LinkedList;

public class 考试的最大困扰度 {
    public static void main(String[] args) {
        int i = new Solution2024B().maxConsecutiveAnswers("TTFFFTFFTTTT", 1);
        System.out.println(i);
    }
}

class Solution2024 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        // 滑动窗口
        return Math.max(maxConsecutiveChar(answerKey, k, 'F'),maxConsecutiveChar(answerKey, k, 'T'));
    }
    private int maxConsecutiveChar(String answerKey, int k, char ch) {
        int res = 0;
        int left = 0;
        int right = 0;
        int n = answerKey.length();
        while (right < n) {
            char cr = answerKey.charAt(right);
            if (cr == ch) k--;
            if (k == -1) {
                res = Math.max(res, right - left);
                while (left <= right && k == -1) {
                    char cl = answerKey.charAt(left);
                    if (cl == ch) k++;
                    left++;
                }
            }
            right++;
        }
        if (k >= 0) res = Math.max(res, right - left);
        return res;
    }
}

class Solution2024B {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int left = 0, countT = 0, countF = 0, maxLen = 0;

        for (int right = 0; right < answerKey.length(); right++) {
            if (answerKey.charAt(right) == 'T') {
                countT++;
            } else {
                countF++;
            }

            // 如果窗口中最小字符数量超过k，收缩窗口
            while (Math.min(countT, countF) > k) {
                if (answerKey.charAt(left) == 'T') {
                    countT--;
                } else {
                    countF--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}