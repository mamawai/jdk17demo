package com.example.demo.algorithm.leetcode.每日一题;

import java.util.ArrayList;
import java.util.List;

public class 不浪费原料的汉堡制作方案 {
    public static void main(String[] args) {
        List<Integer> integerList = new Solution1276().numOfBurgers(2385088, 164763);
        System.out.println(integerList);
    }
}

class Solution1276 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        // 都是一片奶酪 说明 cheeseSlices = jumbo + small
                       // tomatoSlices = 4jumbo + 2small
        List<Integer> ans = new ArrayList<>();
        int jumbo = 0;
        int small = 0;
        int i = tomatoSlices - 2 * cheeseSlices;
        if (i % 2 == 0 && i >= 0) {
            jumbo = i / 2;
            small = cheeseSlices - jumbo;
            if (small >= 0) {
                ans.add(jumbo);
                ans.add(small);
            }
        }
        return ans;
    }
}