package com.example.demo.algorithm.leetcode.每日一题;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 按分隔符拆分字符串 {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("one.two.three");
        words.add("four.five");
        words.add("six");
        List<String> list = new Solution2788().splitWordsBySeparator(words, '.');
        System.out.println(list);
        System.out.println("a.a.a".split("\\.")[0]);
    }
}

class Solution2788 {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            String[] split = word.split(String.valueOf('\\') + separator);
            ans.addAll(Arrays.stream(split).filter(s -> !isBlank(s)).toList());
        }
        return ans;
    }
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }
}
