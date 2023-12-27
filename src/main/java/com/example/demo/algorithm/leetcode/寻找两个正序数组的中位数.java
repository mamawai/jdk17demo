package com.example.demo.algorithm.leetcode;

public class 寻找两个正序数组的中位数 {
    public static void main(String[] args) {
        double medianSortedArrays = new Solution4().findMedianSortedArrays(new int[]{}, new int[]{1});
        System.out.println(medianSortedArrays);
    }
}

class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int midL = length/2 + 1;
        int[] ansArray = new int[2];
        int left = 0, right = 0, index = 0;
        for (int i = 0; i < midL; i++) {
            if (left < nums1.length && right < nums2.length && nums1[left] <= nums2[right]) {
                if (i == midL - 2 || i == midL - 1)
                    ansArray[index++] = nums1[left];
                left++;
            }
            else if (left < nums1.length && right < nums2.length && nums1[left] >nums2[right]) {
                if (i == midL - 2 || i == midL - 1)
                    ansArray[index++] = nums2[right];
                right++;
            }
            else if (left >= nums1.length || right >= nums2.length) {
                if (left >= nums1.length) {
                    if (i == midL - 2 || i == midL - 1)
                        ansArray[index++] = nums2[right];
                    right++;
                } else {
                    if (i == midL - 2 || i == midL - 1)
                        ansArray[index++] = nums1[left];
                    left++;
                }
            }
        }
        if (length%2 == 0) {
            return (double) (ansArray[0] + ansArray[1]) / 2;
        } else {
            return ansArray[1] == 0 ? ansArray[0] : ansArray[1];
        }
    }
}

/*
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int midL = length/2 + 1;
        int[] ansArray = new int[midL];
        int left = 0, right = 0;
        for (int i = 0; i < midL; i++) {
            if (left < nums1.length && right < nums2.length && nums1[left] <= nums2[right])
                ansArray[i] = nums1[left++];
            else if (left < nums1.length && right < nums2.length && nums1[left] >nums2[right])
                ansArray[i] = nums2[right++];
            else if (left >= nums1.length || right >= nums2.length)
                ansArray[i] = left >= nums1.length ? nums2[right++] : nums1[left++];
        }
        if (length%2 == 0) {
            return (double) (ansArray[midL - 2] + ansArray[midL - 1]) / 2;
        } else {
            return ansArray[midL-1];
        }
    }
}*/
