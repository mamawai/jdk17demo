package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayList;
import java.util.List;

public class 找出峰值 {
    public static void main(String[] args) {

    }
}

class SolutionfindPeaks {
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i < mountain.length - 1; i++) {
            if(mountain[i] > mountain[i + 1] && mountain[i] < mountain[i - 1]) {
                ans.add(i);
            }
        }
        return ans;
    }
}