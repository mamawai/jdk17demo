package com.example.demo.algorithm.leetcode.每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 统计和小于目标的下标对数目 {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(-6);
        integers.add(2);
        integers.add(5);
        integers.add(-2);
        integers.add(-7);
        integers.add(-1);
        integers.add(3);

        int target = -2;
        int i = new Solution2824().countPairs(integers, target);
        System.out.println(i);
    }
}

class Solution2824 {
    public int countPairs(List<Integer> nums, int target) {
        int size = nums.size();
        quickSort(nums, 0, size - 1);
        int ans = 0;
        for (int i = 0; i < size; i++) {
            if (nums.get(i) > target/2) {
                break;
            }
            int j = i + 1;
            while (j < size) {
                if (nums.get(i)+ nums.get(j) < target) {
                    ans++;
                    j++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    private void quickSort(List<Integer> a, int l, int r) {
        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a.get(i);
            while (i < j) {
                while (i < j && a.get(j) > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    a.set(i++, a.get(j));
                while (i < j && a.get(i) < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    a.set(j--, a.get(i));
            }
            a.set(i, x);
            quickSort(a, l, i - 1);
            quickSort(a, i + 1, r);
        }
    }
}