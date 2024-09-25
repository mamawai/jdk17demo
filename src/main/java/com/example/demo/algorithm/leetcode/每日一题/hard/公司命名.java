package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class 公司命名 {
    public static void main(String[] args) {
        long l = new Solution2306().distinctNames(new String[]{"coffee", "donuts", "time", "toffee"});
        System.out.println(l);
    }
}

class Solution2306 {
    public long distinctNames(String[] ideas) {
        HashSet<String>[] setList = new HashSet[26];
        Arrays.setAll(setList, e -> new HashSet<String>());
        for (String idea : ideas) {
            int idx = idea.charAt(0) - 'a';
            setList[idx].add(idea.substring(1));
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            a : for (int j = i + 1; j < 26; j++) {
                int sizeI = setList[i].size();
                int sizeJ = setList[j].size();
                for (String s : setList[i]) {
                    if (setList[j].contains(s)) {
                        sizeI--;
                        sizeJ--;
                        if(sizeI == 0 || sizeJ == 0) continue a;
                    }
                }
                ans += (long) sizeI * sizeJ * 2;
            }
        }
        return ans;
    }
}
