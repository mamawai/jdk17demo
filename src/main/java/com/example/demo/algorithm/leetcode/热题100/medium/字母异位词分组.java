package com.example.demo.algorithm.leetcode.热题100.medium;

import java.util.*;

public class 字母异位词分组 {
    public static void main(String[] args) {
        System.out.println(Arrays.compare(new int[]{1,2,7},new int[]{1,2,3}));
        System.out.println('a' - 97);
    }
}

class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}