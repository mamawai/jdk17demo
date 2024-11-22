package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 找到按位或最接近K的子数组 {
    public static void main(String[] args) {
        int i = new Solution3171B().minimumDifference(new int[]{1, 2, 9, 6}, 7);
        System.out.println(i);
    }
}

class Solution3171 {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int[] bitsMaxPos = new int[31];
        Arrays.fill(bitsMaxPos, -1);
        List<int[]> posToBit = new ArrayList<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int len = Integer.toBinaryString(nums[i]).length() - 1;
            for (int j = 0; j <= len; j++) {
                if ((nums[i] >> j & 1) != 0) {
                    bitsMaxPos[j] = i;
                }
            }
            posToBit.clear();
            for (int j = 0; j < bitsMaxPos.length; j++) {
                if (bitsMaxPos[j] != -1) {
                    posToBit.add(new int[]{bitsMaxPos[j], j});
                }
            }
            posToBit.sort((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
            int val = 0;
            for (int j = 0, p = 0; j < posToBit.size(); ) {
                while (j < posToBit.size() && posToBit.get(j)[0] == posToBit.get(p)[0]) {
                    val |= 1 << posToBit.get(j)[1];
                    j++;
                }
                res = Math.min(res, Math.abs(val - k));
                p = j;
            }
        }
        return res;
    }
}

class Solution3171B {
    public int minimumDifference(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            ans = Math.min(ans, Math.abs(x - k));
            // 如果 x 是 nums[j] 的子集，就退出循环
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                nums[j] |= x;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }
}