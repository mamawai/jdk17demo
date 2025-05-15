package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;
import java.util.Map;

public class 统计好子数组的数目 {
    public static void main(String[] args) {
        long l = new Solution2537B().countGood(
                new int[]{3,1,4,3,2,2,4},
                2
        );
        System.out.println(l);
    }
}

class Solution2537 {
    public long countGood(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int pairs = 0;
        int left = 0;
        for (int x : nums) {
            int c = cnt.getOrDefault(x, 0);
            pairs += c; // 进
            cnt.put(x, c + 1);
            while (pairs >= k) {
                x = nums[left];
                c = cnt.get(x);
                pairs -= c - 1; // 出
                cnt.put(x, c - 1);
                left++;
            }
            ans += left;
        }
        return ans;
    }
}


class Solution2537B {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int left = 0;
        int right = 0;
        int pairs = 0;
        long ans = 0;
        while (true) {
            if (pairs < k && right < nums.length) {
                int c = cnt.getOrDefault(nums[right], 0);
                pairs += c;
                cnt.put(nums[right++], c + 1);
                ans += left;
            } else if (pairs >= k && left < nums.length){
                int x = nums[left];
                int c = cnt.get(x);
                pairs -= c - 1;
                cnt.put(x, c - 1);
                left++;
                ans++;
            } else break;
        }
        return ans;
    }
}
