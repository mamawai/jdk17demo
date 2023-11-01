package com.example.demo.algorithm.al_test;

import java.util.HashMap;
import java.util.Map;

public class slideWindow {
    public static void main(String[] args) {
        String s = "OOCBNJGKKALLLLLLLBCNOAV";
        String t = "ABC";
        // 维护s串中滑动窗口中各个字符出现次数
        Map<Character, Integer> hs = new HashMap<>();
        // 维护t串中各个字符出现次数
        Map<Character, Integer> ht = new HashMap<>();
        // 记录t串中的字符以及出现次数
        for (int i = 0; i < t.length(); i++) {
            ht.put(t.charAt(i), ht.getOrDefault(t.charAt(i), 0)+1);
        }

        String ans="";
        // cnt维护s串[left,right]中满足t串的元素的个数，记录相对应字符的总数
        int len=Integer.MAX_VALUE,cnt=0;

        // 区间[left,right]表示当前滑动窗口
        for (int left=0,right = 0; right < s.length(); right++) {
            // 向hs中放入当前的字符 以及出现的次数
            hs.put(s.charAt(right), hs.getOrDefault(s.charAt(right), 0)+1);
            // 如果ht表中也包含当前字符
            if (ht.containsKey(s.charAt(right))) {
                // 并且hs表中的字符个数<=ht表中的字符个数,说明该字符是必须的
                if (hs.get(s.charAt(right)) <= ht.get(s.charAt(right))) {
                    cnt++;
                }
            }
            // 收缩滑动窗口
            // 如果左边界的值不在ht表中 或者 它在hs表中的出现次数多于ht表中的出现次数 那就说明可以将他从左边放出去即将hs中的字符计数减去1
            // 这里的while循环会在字符串左侧出现多余字符的时候收缩
            while(left < right && (!ht.containsKey(s.charAt(left)) || hs.get(s.charAt(left)) > ht.get(s.charAt(left)))){
                hs.put(s.charAt(left),hs.get(s.charAt(left)) - 1);
                left++;
            }
            // 此时滑动窗口包含符串 t 的全部字符
            // len记录当前最小子串长度
            if (cnt==t.length()&&right-left+1<len) {
                len=right-left+1;
                ans=s.substring(left,right+1);
            }
        }
        System.out.println(ans);
    }
}
