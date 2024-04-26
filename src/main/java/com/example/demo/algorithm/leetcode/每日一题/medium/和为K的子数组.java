package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;

public class 和为K的子数组 {
    public static void main(String[] args) {
        int i = new Solution560().subarraySum(new int[]{1,1,0,-1,-1,2,-2,2,-2}, 0);
        System.out.println(i);
    }
}

class Solution560 {
    public int subarraySum(int[] nums, int k) {
        // 1 1 0 -1 -1 2 -2 2 -2
        // 1
        // 1 + 1 + 0 -1
        // + 1 + 0 -1
        // + 0 -1
        // -1
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) ans++;
            int j = i;
            while (j-- > 0) {
                nums[j] += nums[i];
                if (nums[j] == k) ans++;
            }
        }
        return ans;
    }
}

/**
 * 前缀和 + 哈希表优化 哈希表存前缀和 如果pre - k的值在哈希表存在，说明存在当前状态存在map.get(pre - k)个和为k的组合
 */
class Solution560B {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap< Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

}