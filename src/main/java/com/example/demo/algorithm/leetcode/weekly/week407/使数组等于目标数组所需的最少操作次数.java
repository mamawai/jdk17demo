package com.example.demo.algorithm.leetcode.weekly.week407;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 使数组等于目标数组所需的最少操作次数 {
    public static void main(String[] args) {
        long l = new Solution407q4().minimumOperations(new int[]{9,2,6,10,4,8,3,4,2,3}, new int[]{9,5,5,1,7,9,8,7,6,5});
        System.out.println(l);
    }
}

class Solution407q4 {
    public long minimumOperations(int[] nums, int[] target) {
        long ans = 0L;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = target[i] - nums[i];
        }
        int j = 0;
        while (nums[j] == 0) j++;
        boolean flag = nums[j] > 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = j; i < nums.length; i++) {
            // 符号变化或0 处理queue中数据
            if (flag != nums[i] > 0 || nums[i] == 0) {
                // 3 1 5 3 4 2
                // 2 0 4 2 3 1
                //
                ans = calAns(ans, queue);
                while (nums[i] == 0) i++;
                flag = nums[i] > 0;
                i--;
            } else if (flag == nums[i] > 0) {
                queue.offer(nums[i] > 0 ? nums[i] : -nums[i]);
            }
        }
        return calAns(ans, queue);
    }

    private long calAns(long ans, ArrayDeque<Integer> queue) {
        while (!queue.isEmpty()) {
            int minus = queue.poll();
            List<Integer> rest = new ArrayList<>();
            while (!queue.isEmpty()) {
                Integer polled = queue.poll();
                if (polled - minus != 0) rest.add(polled - minus);
            }
            ans += minus;
            queue.addAll(rest);
        }
        return ans;
    }
}