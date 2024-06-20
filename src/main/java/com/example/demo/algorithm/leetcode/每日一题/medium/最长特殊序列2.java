package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 最长特殊序列2 {
    public static void main(String[] args) {
        int luSlength = new Solution522().findLUSlength(new String[]{"j","j","viez","ogk","ogk","lfn","ypmhwx","ypmhwx","m","m","ak","ivivzoncju","ivivzoncju","wmybi","wmybi","dyzfjg","dyzfjg"});
        System.out.println(luSlength);
    }
}

class Solution522 {
    public int findLUSlength(String[] strs) {
        Set<String>[] setArr = new Set[11];
        Arrays.setAll(setArr, e -> new HashSet<String>());
        Map<String, Integer> map = new HashMap<>();
        int[] sumArr = new int[11];
        for (String str : strs) {
            int l = str.length();
            sumArr[l]++;
            setArr[l].add(str);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        boolean firstIn = true;
        List<String> baseStrList = new ArrayList<>();
        for (int i = 10; i >= 0; i--) {
            if (sumArr[i] != 0) {
                if (setArr[i].size() == 1 && sumArr[i] == 1 && firstIn || setArr[i].size() >= 1 && firstIn) {
                    firstIn = false;
                    if (setArr[i].size() == 1 && sumArr[i] == 1) return i;
                    for (String next : setArr[i]) {
                        if (map.get(next) == 1) return i;
                        else baseStrList.add(next);
                    }
                } else {
                    Set<String> stringSet = setArr[i];
                    for (String ss : stringSet) {
                        if (map.get(ss) != 1) continue;
                        int diffTime = 0;
                        for (String baseStr : baseStrList) {
                            int j = 0, k = 0;
                            boolean isFind = false;
                            for (; j < ss.length(); j++) {
                                boolean flag = false;
                                char charredAt = ss.charAt(j);
                                while (k < baseStr.length()) {
                                    if (baseStr.charAt(k++) == charredAt) {
                                        flag = true;
                                        break;
                                    }
                                }
                                isFind = flag && j == ss.length() - 1;
                                if (k == baseStr.length()) break;
                            }
                            if (!isFind) diffTime++;
                        }
                        if (diffTime == baseStrList.size()) return ss.length();
                    }
                }
            }
        }
        return -1;
    }
}