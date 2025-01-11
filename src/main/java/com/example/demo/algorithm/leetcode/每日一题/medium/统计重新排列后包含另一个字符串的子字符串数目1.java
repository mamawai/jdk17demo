package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 统计重新排列后包含另一个字符串的子字符串数目1 {
    public static void main(String[] args) {
        long l = new Solution3297().validSubstringCount("abcabc", "abc");
        System.out.println(l);
    }
}

class Solution3297 {
    public long validSubstringCount(String word1, String word2) {
        long ans = 0;
        int[] tar = new int[26]; // 统计word2 中每个字符出现的次数
        int total = 0; // 统计word2 有多少种字符
        for (int i = 0; i < word2.length(); i++) if (tar[word2.charAt(i) - 'a']++ == 0) total++;
        int l = 0, r = 0; // 滑动窗口边界
        int[] cnt = new int[26]; // 统计word1 中每个字符出现的次数
        int complete = 0; // 完成度
        while (l <= word1.length() - word2.length()) {
            if (complete < total) { // 如果没完成
                if (r == word1.length()) break; // 如果右边界已经到末尾，还没完成，直接退出
                int expandIdx = word1.charAt(r) - 'a'; // 扩展index
                if (++cnt[expandIdx] == tar[expandIdx]) complete++; // 只有当第一次相等时才加一
                r++; // 扩大
            } else { // 如果完成
                while (true) {
                    ans += word1.length() - r + 1; // 先统计ans
                    int contractIdx = word1.charAt(l++) - 'a'; // 收缩 并记录收缩的下标
                    if (--cnt[contractIdx] < tar[contractIdx]) { // word1 字符出现次数减一
                        complete--;// 收缩窗口内字符个数小于目标中字符个数，完成度减一
                        break; // 退出循环
                    }
                }
            }
        }
        return ans;
    }
}