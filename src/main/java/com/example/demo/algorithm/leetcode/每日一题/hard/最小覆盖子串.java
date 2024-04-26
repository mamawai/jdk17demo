package com.example.demo.algorithm.leetcode.每日一题.hard;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 最小覆盖子串 {
    public static void main(String[] args) {
        String s = new Solution76B().minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }
}

class Solution76 {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        int[] min = new int[]{0, s.length()};
        int[] map = new int[58];
        Arrays.fill(map, -1);
        int cnt = 0;
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 65;
            if (map[index] < 0) map[index] ++;
            map[index] ++;
            cnt++;
        }
        int left = 0;
        int right = 0;
        int[] sum = new int[58];
        Arrays.fill(sum, -1);
        while (right < s.length()) {
            int index = s.charAt(right) - 65;
            int times = map[index];
            if (times != -1) {
                if (sum[index] == -1) sum[index] = 1;
                else sum[index]++;
            }
            while (isCover(sum, map)) {
                int curTimes = map[s.charAt(left) - 65];
                if (curTimes != -1) {
                    if (min[1] - min[0] > right - left) {
                        min[0] = left;
                        min[1] = right;
                    }
                    sum[s.charAt(left) - 65]--;
                }
                left++;
            }

            right++;
        }
        if (min[1] + 1 > s.length()) return "";
        return s.substring(min[0], min[1] + 1);
    }

    private boolean isCover(int[] sum, int[] map) {
        for (int i = 0; i < map.length; i++) {
            if (sum[i] < map[i]) return false;
        }
        return true;
    }
}

/**
 * 优化
 */
class Solution76B {
    public String minWindow(String S, String t) {
        char[] s = S.toCharArray();
        int m = s.length;
        int ansLeft = -1;
        int ansRight = m;
        int left = 0;
        int less = 0;
        int[] cntS = new int[128]; // s 子串字母的出现次数
        int[] cntT = new int[128]; // t 中字母的出现次数
        for (char c : t.toCharArray()) {
            if (cntT[c]++ == 0) {
                less++; // 有 less 种字母的出现次数 < t 中的字母出现次数
            }
        }
        for (int right = 0; right < m; right++) { // 移动子串右端点
            char c = s[right]; // 右端点字母（移入子串）
            if (++cntS[c] == cntT[c]) {
                less--; // c 的出现次数从 < 变成 >=
            }
            while (less == 0) { // 涵盖：所有字母的出现次数都是 >=
                if (right - left < ansRight - ansLeft) { // 找到更短的子串
                    ansLeft = left; // 记录此时的左右端点
                    ansRight = right;
                }
                char x = s[left++]; // 左端点字母（移出子串）
                if (cntS[x]-- == cntT[x]) {
                    less++; // x 的出现次数从 >= 变成 <
                }
            }
        }
        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }
}