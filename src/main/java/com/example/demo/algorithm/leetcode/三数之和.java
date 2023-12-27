package com.example.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 三数之和 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution15().threeSum(new int[]{-2,0,1,1,2});
        System.out.println(lists);
    }
}

class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        quickSort(nums, 0, nums.length - 1);
        // -4 -1 -1 0 1 2

        for (int left = 0;left < nums.length - 2;left++) {
            if (nums[left] > 0) {
                break;
            }
            int right = nums.length - 1;
            int mid = left + 1;
            if (mid == nums.length - 1) {
                break;
            }
            while (mid < right) {
                if (nums[left] + nums[right] + nums[mid] < 0) {
                    mid++;
                }
                else if (nums[left] + nums[right] + nums[mid] > 0) {
                    right--;
                }
                else if (nums[left] + nums[right] + nums[mid] == 0) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(nums[left]);
                    integers.add(nums[mid]);
                    integers.add(nums[right]);
                    ans.add(integers);
                    while (left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    mid++;
                    right--;
                }
            }
        }
        return ans.stream().distinct().toList();
    }

    public static void quickSort(int[] a, int l, int r) {

        if (l < r) {
            int i,j,x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while(i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if(i < j)
                    a[i++] = a[j];
                while(i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if(i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quickSort(a, l, i-1); /* 递归调用 */
            quickSort(a, i+1, r); /* 递归调用 */
        }
    }
}
