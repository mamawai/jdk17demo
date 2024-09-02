package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 划分为k个相等的子集 {
    public static void main(String[] args) {
        boolean b = new SolutionCCC().canPartitionKSubsets(new int[]{1,1,1,1,2,2,2,2}, 4);
        System.out.println(b);
    }
}

class Solution698 {
    int avg;
    int[] nums;
    Map<String, Boolean> memo;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        // 整除不开直接返回false
        if (sum % k != 0) return false;
        // 平均值
        avg = sum / k;
        if (max > avg) return false;
        boolean[] picked = new boolean[nums.length];
        memo = new HashMap<>();
        // k表示当前剩余划分的子集个数
        return dfs(0, 0, picked, k);
    }

    private boolean dfs(int pick, int eachSum, boolean[] picked, int k) {
        String state = pick + "-" + eachSum + "-" + k;
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        picked[pick] = true;
        int num = nums[pick];
        eachSum += num;
        boolean result = false;

        if (eachSum == avg) {
            // 相同说明需要开下一组
            for (int i = 0; i < picked.length; i++) {
                if (!picked[i]) {
                    if (dfs(i, 0, picked, k - 1)) {
                        result = true;
                        break;
                    } else {
                        picked[i] = false;
                    }
                }
            }
        } else {
            // 不相同且小于avg找下一个
            if (eachSum < avg) {
                for (int i = pick + 1; i < nums.length; i++) {
                    if (!picked[i]) {
                        if (dfs(i, eachSum, picked, k)) {
                            result = true;
                            break;
                        } else {
                            picked[i] = false;
                        }
                    }
                }
            } else {
                // 大于avg减去num还原picked找下一个
                picked[pick] = false;
                for (int i = pick + 1; i < nums.length; i++) {
                    if (!picked[i]) {
                        if (dfs(i, eachSum - num, picked, k)) {
                            result = true;
                            break;
                        } else {
                            picked[i] = false;
                        }
                    }
                }
            }
        }

        if (k == 1 && eachSum == avg) {
            for (boolean b : picked) {
                if (!b) return false;
            }
            return true;
        }

        memo.put(state, result);
        return result;
    }
}

class SolutionCCC {
    int avg;
    int[] nums;
    Map<Long, Boolean> memo;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        // 整除不开直接返回false
        if (sum % k != 0) return false;
        // 平均值
        avg = sum / k;
        if (max > avg) return false;
        // 记忆化
        memo = new HashMap<>();
        return dfs(0, 0, 0, k);
    }

    private boolean dfs(long used, int currentSum, int start, int k) {
        // 如果k已经为0，表示已经成功划分为k个子集
        if (k == 0) return true;
        if (currentSum == avg) {
            // 如果当前子集和已经等于目标值avg，递归计算剩下的k-1个子集
            return dfs(used, 0, 0, k - 1);
        }
        if (memo.containsKey(used)) return memo.get(used);
        // 尝试从start位置开始，选择一个元素加入当前子集
        for (int i = start; i < nums.length; i++) {
            // used & (1L << i) 表示i这个位置的元素是否已经被使用过
            if ((used & (1L << i)) == 0 && currentSum + nums[i] <= avg) {
                // 更新used状态
                if (dfs(used | (1L << i), currentSum + nums[i], i + 1, k)) return true;
            }
        }
        memo.put(used, false);
        return false;
    }
}