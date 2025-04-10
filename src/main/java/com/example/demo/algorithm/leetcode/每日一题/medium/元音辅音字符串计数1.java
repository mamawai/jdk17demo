package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 元音辅音字符串计数1 {
    public static void main(String[] args) {

    }
}



class Solution3305 {
    public long countOfSubstrings(String word, int k) {
        char[] s = word.toCharArray();
        return f(s, k) - f(s, k + 1);
    }

    private long f(char[] word, int k) {
        final int VOWEL_MASK = 1065233;
        long ans = 0;
        int[] cnt1 = new int['u' - 'a' + 1];
        int size1 = 0; // 元音种类数
        int cnt2 = 0;
        int left = 0;
        for (char b : word) {
            b -= 'a';
            if ((VOWEL_MASK >> b & 1) > 0) {
                if (cnt1[b]++ == 0) {
                    size1++;
                }
            } else {
                cnt2++;
            }
            while (size1 == 5 && cnt2 >= k) {
                int out = word[left] - 'a';
                if ((VOWEL_MASK >> out & 1) > 0) {
                    if (--cnt1[out] == 0) {
                        size1--;
                    }
                } else {
                    cnt2--;
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }
}