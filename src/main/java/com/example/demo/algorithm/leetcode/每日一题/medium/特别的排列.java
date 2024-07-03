package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 特别的排列 {
    public static void main(String[] args) {
        int i = new Solution2714().specialPerm(new int[]{1, 4, 3});
    }
}
/**
 * 状态压缩dp好题
 */

class Solution2714 {
    private long[][] memo;   // 用于记忆化搜索的数组，memo[s][i]表示当前未选元素构成的集合s且最后一个选择元素为nums[i]时，可以得到的特别排列总数目

    /**
     * 对于当前未选元素的集合s，且最后一个选择元素为nums[i]，求解未选元素可以得到的特别排列总数目
     * @param s: 二进制每一位bit表示nums[bit]是否未选择，即1表示未选，0表示已选
     * @param i: 当前已选择元素的最后一个元素的索引，用来判断当前选择的元素是否可以加入
     */
    private long dfs(int s, int i, int[] nums){
        // 所有元素都选择，说明得到了一种特殊排列方案，返回1
        if(s == 0)return 1;
        // 搜索过的状态，直接返回记忆值
        if(memo[s][i]>= 0)return memo[s][i];
        memo[s][i] = 0;     // 统计这个状态的值
        // 枚举未选择的元素，作为当前选择的元素
        for(int j = 0; j < nums.length; j++){
            // s的第j位为1，说明nums[j]未选择；且nums[j]和nums[i]可以满足条件
            if(((s >> j) & 1) == 1 && (nums[j] % nums[i] == 0 || nums[i] % nums[j] == 0)){
                memo[s][i] += dfs(s ^ (1 << j), j, nums);   // 将s的第j位置为0
            }
        }
        return memo[s][i];
    }

    public int specialPerm(int[] nums) {
        int n = nums.length;
        int r = (1 << n) - 1;   // 初始集合，所有位置的元素都未选择
        memo = new long[r+1][n];   // 定义矩阵尺寸, 初始化值都为-1
        for(int i = 0; i < r + 1; i++){
            Arrays.fill(memo[i], -1);
        }
        long res = 0;
        for(int i = 0; i < n; i++){
            // 以每个元素为集合的第一个元素进行dfs搜索
            // 这里的 r ^ (1 << i) 就是把某一位的1变成0即先选了nums的第几个数字
            res += dfs(r ^ (1 << i), i, nums);
        }
        return (int)(res % 1000000007);
    }
}