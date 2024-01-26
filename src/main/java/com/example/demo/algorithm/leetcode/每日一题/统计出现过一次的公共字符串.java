package com.example.demo.algorithm.leetcode.每日一题;

import java.util.HashMap;
import java.util.Map;

public class 统计出现过一次的公共字符串 {
    public static void main(String[] args) {
        int i = new Solution2085().countWords(new String[]{"a", "ab"}, new String[]{"a", "a", "a", "ab"});

        System.out.println(i);
    }}

class Solution2085 {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        for (String s : words1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : words2) {
            if(!map.containsKey(s) || map.get(s)  > 1){
                continue;
            }
            int temp = map.get(s) - 1;
            map.put(s,temp);
            if(temp == 0){
                ans++;
            }
            if(temp == -1){
                ans--;
            }
        }
        return ans;
    }
}