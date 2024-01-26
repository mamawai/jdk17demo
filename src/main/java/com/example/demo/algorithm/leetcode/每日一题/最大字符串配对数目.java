package com.example.demo.algorithm.leetcode.每日一题;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class 最大字符串配对数目 {
    public static void main(String[] args) {
        int i = new Solution2744().maximumNumberOfStringPairs(new String[]{"cd", "ac", "dc", "ca", "zz"});
        System.out.println(i);
    }
}

class Solution2744 {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<Integer, List<String>> map = new HashMap<>();
        int ans = 0;
        a:for (String s : words) {
            char[] charArray = s.toCharArray();
            int key = charArray[0] + charArray[1];
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            if (!CollectionUtils.isEmpty(orDefault)) {
                for (String o : orDefault) {
                    if (o.charAt(0) == charArray[1]) {
                        ans++;
                        continue a;
                    }
                }
                orDefault.add(s);
            } else {
                orDefault.add(s);
            }
            map.put(key, orDefault);
        }
        return ans;
    }
}