package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class 找到字符串中所有字母异位词 {
    public static void main(String[] args) {
        List<Integer> anagrams = new Solution438().findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }
}

class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p.length() > s.length()) return ans;
        int[] words = new int[26];
        for (char word : p.toCharArray()) {
            words[word - 97] ++;
        }
        int wLength = p.length();
        int left = 0;
        int right = wLength - 1;
        String substr = s.substring(left, right + 1);
        Map<Character, Integer> map = new HashMap<>();
        for (char c : substr.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        while (true) {
          boolean flag = true;
          for (Map.Entry<Character, Integer> entry : map.entrySet()) {
              if (words[entry.getKey() - 97] != entry.getValue()) {
                  flag = false;
                  break;
              }
          }
          if (flag) ans.add(left);
          char leftChar = s.charAt(left);
          Integer integer = map.get(leftChar);
          if (integer - 1 == 0) map.remove(leftChar);
          else map.put(leftChar, integer - 1);
          left++;
          right++;
          if (right >= s.length()) break;
          map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
        }
        return ans;
    }
}