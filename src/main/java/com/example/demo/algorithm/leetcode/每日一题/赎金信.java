package com.example.demo.algorithm.leetcode.每日一题;

import java.util.HashMap;
import java.util.Map;

public class 赎金信 {
    public static void main(String[] args) {
        boolean b = new Solution383().canConstruct("aba", "acenba");
        System.out.println(b);
    }
}

class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars1 = ransomNote.toCharArray();
        char[] chars2 = magazine.toCharArray();
        for (char c : chars2) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for (char c : chars1) {
            if (map.get(c) == null) {
                return false;
            } else {
                int value = map.get(c) - 1;
                if (value < 0) {
                    return false;
                }
                map.put(c, value);
            }
        }
        return true;
    }
    // 把每个字母转成arr的下标进行统计
    public boolean canConstructB(String ransomNote, String magazine) {
        int[] arr= new int[26];
        for(int i=0;i<ransomNote.length();i++){
            arr[ransomNote.charAt(i)-'a']+=1;
        }

        for(int i=0;i<magazine.length();i++){
            arr[magazine.charAt(i)-'a']-=1;
        }
        for(int i=0;i<26;i++){
            if(arr[i]>0) return false;
        }
        return true;
    }
}