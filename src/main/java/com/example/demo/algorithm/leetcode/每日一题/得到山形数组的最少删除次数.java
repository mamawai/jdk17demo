package com.example.demo.algorithm.leetcode.每日一题;

import java.util.ArrayList;
import java.util.List;

public class 得到山形数组的最少删除次数 {
    public static void main(String[] args) {
        int i = new Solution1671().minimumMountainRemovals(new int[]{1,2,2,3,4,3,2,3,1});
        System.out.println(i);
    }
}
/**
 * 最长递增子序列 LC300 + dp
 */
class Solution1671 {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] pre = getLISArray(nums);
        int[] reversed = reverse(nums);
        int[] suf = getLISArray(reversed);
        suf = reverse(suf);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (pre[i] > 1 && suf[i] > 1) {
                ans = Math.max(ans, pre[i] + suf[i] - 1);
            }
        }

        return n - ans;
    }

    public int[] getLISArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        List<Integer> seq = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            int index = binarySearch(seq, nums[i]);
            if (index == seq.size()) {
                seq.add(nums[i]);
                dp[i] = seq.size();
            } else {
                seq.set(index, nums[i]);
                dp[i] = index + 1;
            }
        }
        return dp;
    }

    public int[] reverse(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        return reversed;
    }

    public int binarySearch(List<Integer> seq, int target) {
        int low = 0, high = seq.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (seq.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

class SolutionBBB {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] pre = getLISArray(nums);
        int[] reversed = reverse(nums);
        int[] suf = getLISArray(reversed);
        suf = reverse(suf);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (pre[i] > 1 && suf[i] > 1) {
                ans = Math.max(ans, pre[i] + suf[i] - 1);
            }
        }

        return n - ans;
    }

    public int[] getLISArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        List<Integer> seq = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            int index = binarySearch(seq, nums[i]);
            if (index == seq.size()) {
                seq.add(nums[i]);
                dp[i] = seq.size();
            } else {
                seq.set(index, nums[i]);
                dp[i] = index + 1;
            }
        }
        return dp;
    }

    public int[] reverse(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        return reversed;
    }

    public int binarySearch(List<Integer> seq, int target) {
        int low = 0, high = seq.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (seq.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}