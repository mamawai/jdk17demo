package com.example.demo.algorithm.leetcode;

public class 寻找重复数 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,5,9,6,9,3,8,9,7,1};
        int duplicate = new SolutionA().findDuplicate(nums);
        System.out.println(duplicate);
    }
}

class Solution {
    public int findDuplicate(int[] nums) {
        // 二分查找
        // 定位左右两个指针 因为一共n+1个数字 r要定位到n处 所以是数组长度再减去一
        int l = 1;
        int r = nums.length - 1;
        int ans = 0;

        while (l <= r) {
            int cnt = 0;
            int mid = (l+r)/2;
            for (int i : nums) {
                if (i <=  mid) {
                    cnt++;
                }
            }
            // 1 2 3 4 5
            if (cnt > mid) {
                // 大于说明mid位于重复数字或者位于重复数字右面,所以让r移动到mid-1处
                r = mid - 1;
                // 暂存ans 之后可以覆盖
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
class SolutionA {
    public int findDuplicate(int[] nums) {
    // 快慢指针
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        return nums[slow];
    }
}