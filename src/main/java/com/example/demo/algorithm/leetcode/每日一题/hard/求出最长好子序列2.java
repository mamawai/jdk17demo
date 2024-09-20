package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.HashMap;
import java.util.Map;

public class 求出最长好子序列2 {
    public static void main(String[] args) {
        int i = new Solution3177().maximumLength(new int[]{78, 79, 80, 80, 78, 79, 79, 78}, 2);
        System.out.println(i);
    }
}

class Solution3177 {
    public int maximumLength(int[] nums, int k) {
        Map<Integer, int[]> dp = new HashMap<>();  // dp[num][l]表示以元素num结尾，存在l个下标满足不等条件的最长好子序列
        int[] record = new int[k+2];                     // record[l+1]表示当前存在l个下标满足不等条件的最长好子序列，record[0]避免l=0时l-1不存在
        for(int num: nums){
            if(!dp.containsKey(num)){
                dp.put(num, new int[k+1]);        // 首次处理元素num，将其状态数组进行初始化
            }
            for(int l = k; l >= 0; l--){    // 枚举l的范围，上限为k
                // 要么从相同元素结尾的存在l个下标满足不等条件的最长好子序列转移过来
                // 要么从之前存在l-1个下标满足不等条件的最长好子序列转移过来
                int[] dpNum = dp.get(num);
                dpNum[l] = Math.max(dpNum[l], record[l]) + 1;// 这里的record[l]的l已经是l-1的状态了，因为在初始化record的时候的size是k+2
                // 更新record[l+1]
                record[l+1] = Math.max(record[l+1], dpNum[l]);
            }
        }
        return record[0];    // 当前存在k个下标满足不等条件的最长好子序列（不超过k个，那么k个一定是最长的）
    }
}