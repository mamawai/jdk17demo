package com.example.demo.algorithm.B;

import com.example.demo.algorithm.A.完美走位;

import java.util.Arrays;
import java.util.Scanner;

public class 按单词下标区间翻转文章内容 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int start = Integer.parseInt(sc.nextLine());
        int end = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(s,start,end));
    }

    private static String getResult(String s, int start, int end) {
        String[] words = s.split(" ");
        StringBuilder ans = new StringBuilder();
        if (start >= end) {
            return s;
        }
        if (end <= 0) {
            return s;
        }
        if (start >= words.length - 1) {
            return s;
        }
        if (end > words.length -1) {
            end = words.length -1;
        }
        if (start < 0) {
            start = 0;
        }
        for (int j = start, k = end;j <= k ;j++,k--) {
            String tmp = "";
            tmp = words[k];
            words[k] = words[j];
            words[j] = tmp;
        }
        for (String word : words) {
            ans.append(word).append(" ");
        }
        return ans.toString();
    }
}
