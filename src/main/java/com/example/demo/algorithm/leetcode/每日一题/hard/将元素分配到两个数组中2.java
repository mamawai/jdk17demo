package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 将元素分配到两个数组中2 {
    public static void main(String[] args) {
        int[] ints = new Solution3072().resultArray(new int[]{5, 14, 3, 5, 4, 2});
    }
}


class FenWick{
    // tree的下标表示映射的数字，tree[i]表示i映射的数字num出现的次数
    private final int[] tree;

    // 初始化树状数组
    public FenWick(int n) {
        tree = new int[n];
    }

    // 把下标为i的元素加1
    public void add(int i) {
        while (i < tree.length) {
            tree[i]++;
            i += i & -i; // 每次都加lowBit(i)
        }
    }

    // 返回下标在[1,i]的元素之和
    public int pre(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= i & -i; // 每次都减lowBit(i)
        }
        return res;
    }
}

class Solution3072 {
    public int[] resultArray(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int n = nums.length;

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(nums[0]);
        b.add(nums[1]);

        FenWick t1 = new FenWick(n + 1);// 对应a
        FenWick t2 = new FenWick(n + 1);// 对应b
        t1.add(Arrays.binarySearch(sorted, nums[0]) + 1);
        t2.add(Arrays.binarySearch(sorted, nums[1]) + 1);

        for (int i = 2; i < nums.length; i++) {
            int x = nums[i];
            int v = Arrays.binarySearch(sorted, x) + 1;
            int greaterCountA = a.size() - t1.pre(v);
            int greaterCountB = b.size() - t2.pre(v);
            if (greaterCountA > greaterCountB || greaterCountA == greaterCountB && a.size() <= b.size()) {
                a.add(x);
                t1.add(v);
            } else {
                b.add(x);
                t2.add(v);
            }
        }
        a.addAll(b);
        for (int i = 0; i < n; i++) {
            nums[i] = a.get(i);
        }
        return nums;
    }
}