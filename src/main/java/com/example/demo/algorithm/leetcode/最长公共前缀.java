package com.example.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 最长公共前缀 {
    public static void main(String[] args) {
        String s = "A2B2DC23";
        List<String> split = Arrays.stream(s.split("[A-Z]")).filter(s1 -> !s1.isBlank()).toList();
        System.out.println(split);
    }
}

//class Solution14 {
//    public String longestCommonPrefix(String[] strs) {
//        int minlength = Arrays.stream(strs).sorted(Comparator.comparingInt(String::length)).toList().get(0).length();
//
//    }
//}