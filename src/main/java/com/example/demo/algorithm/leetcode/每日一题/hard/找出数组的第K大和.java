package com.example.demo.algorithm.leetcode.每日一题.hard;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 找出数组的第K大和 {
    public static void main(String[] args) {
        long l = new Solution2386().kSum(new int[]{2, 4, -2}, 5);
        System.out.println(l);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
}

class Solution2386 {
    public long kSum(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);

        // 先将所有的正整数加起来，然后减去某些数就是所有的子序列的和的构成，减去所有正整数和加上所有负数就是最小的和
        // 由于减去正数和加上负数都是减去一个正数，所以将所有数变为正数，都是减去操作，减得多就变得小，减得少就变得多
        // 所有将要减去的数排序，然后找出第 k 小的和，所有正整数的和减去第 k 小的和，不就是第 k 大的和嘛，例如第一大的是所有正整数，第一小是不取，符合


        // 通过最小堆来生成第K个子序列的和 就是sum要减去的数
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>((a, b) -> Long.compare(a.getKey(), b.getKey()));
        pq.offer(new ImmutablePair<>(0L, 0)); // 空子序列
        while (--k > 0) {
            // 这个poll用的好每次poll出队当前最小的Pair的总和 以便最后的getKey取到的是第 k个子序列的和
            Pair<Long, Integer> p = pq.poll();
            long s = p.getKey();
            int i = p.getValue();
            if (i < nums.length) {
                // 在子序列的末尾添加 nums[i]
                pq.offer(new ImmutablePair<>(s + nums[i], i + 1)); // 下一个添加/替换的元素下标为 i+1
                if (i > 0) { // 替换子序列的末尾元素为 nums[i]
                    pq.offer(new ImmutablePair<>(s + nums[i] - nums[i - 1], i + 1));
                }
            }
        }
        return sum - pq.peek().getKey();
    }
}