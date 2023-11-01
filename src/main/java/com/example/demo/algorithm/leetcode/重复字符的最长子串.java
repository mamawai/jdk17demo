package com.example.demo.algorithm.leetcode;

import java.util.HashSet;

public class 重复字符的最长子串 {
    public static void main(String[] args) {
        int length = new SolutionLengthOfLongestSubstring().lengthOfLongestSubstring("aab");
        System.out.println(length);
    }
}

class SolutionLengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        // 定义HashSet存放每次遍历字符
        HashSet<Character> charSet = new HashSet<>();
        // 遍历字符串
        char[] charArray = s.toCharArray();
        if (charArray.length == 1) {
            return 1;
        }
        int cnt = 0;
        for (int left = 0, right = 0; right < charArray.length; right++) {
            // 当剩余长度小于等于cnt时就不用再找了
            if (cnt >= charArray.length - left) {
                break;
            }
            boolean addSuccessfully = charSet.add(charArray[right]);
            if (addSuccessfully && right == charArray.length - 1) {
                cnt = charSet.size();
            }
            if (!addSuccessfully) {
                // 记录当前不重复长度
                cnt = Math.max(cnt, charSet.size());
                // 如果添加失败那么将左指针右移 右指针复位到左指针
                left++;
                right = left - 1;
                // 清空set
                charSet.clear();
            }
        }
        if (cnt == 0) {
            // 说明始终没有重复的
            return charArray.length;
        }
        return cnt;
    }
}