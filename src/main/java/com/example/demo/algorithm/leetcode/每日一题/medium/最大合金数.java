package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 最大合金数 {
    public static void main(String[] args) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> e = new ArrayList<>();
        e.add(1);
        e.add(1);
        e.add(1);
        lists.add(e);
        ArrayList<Integer> e1 = new ArrayList<>();
        e1.add(1);
        e1.add(1);
        e1.add(10);
        lists.add(e1);
        ArrayList<Integer> st = new ArrayList<>();
        st.add(0);
        st.add(0);
        st.add(100);
        ArrayList<Integer> costt = new ArrayList<>();
        costt.add(1);
        costt.add(2);
        costt.add(3);
        int i = new Solution2861().maxNumberOfAlloys(3, 2, 15, lists, st, costt);
        System.out.println(i);
    }
}

class Solution2861 {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int left = 1, right = budget + Collections.max(stock), ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            boolean valid = false;
            for (int i = 0; i<k; i++) {
                long spend = 0L;
                for (int j = 0; j < n; j++) {
                    spend += Math.max((long)composition.get(i).get(j)*mid - stock.get(j), 0) * cost.get(j);
                }
                if (spend <= budget) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}