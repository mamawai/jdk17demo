package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 做菜顺序 {
    public static void main(String[] args) {
        int i = new Solution1402().maxSatisfaction(new int[]{-1,-8,0,5,-7});
        System.out.println(i);
    }
}

class Solution1402 {
    public int maxSatisfaction(int[] satisfaction) {
        int ans = 0;
        int n = satisfaction.length;
        // satisfaction 从小到大排序
        quickSort(satisfaction, 0, n-1);
        // 若全小于等于0直接返回
        if (satisfaction[n-1] <= 0) {
            return ans;
        }
        // 找到第一个小于0的下标
        int firstNegPos = firstNegPos(satisfaction, n);
        // 若全大于等于0则直接求和并返回
        if (firstNegPos < 0) {
            for (int i = 0; i < n; i++) {
                ans += satisfaction[i] * (i+1);
            }
            return ans;
        }
        int positiveSum = 0;
        int negativeSum = 0;
        // 计算正数总和
        for (int i = firstNegPos + 1; i < n; i++) {
            positiveSum += satisfaction[i];
        }
        for (int i = 0; i <= firstNegPos ; i++) {
            negativeSum += satisfaction[i];
        }
        int deleteTimes = 0;
        // 依次将负数给移除 比较每次移除的差值是否大于0
        for (int i = 1; i <= firstNegPos + 1; i ++) {
            // 比较每次移除的差值是否大于等于0 小于0直接break
            if (-negativeSum - positiveSum >= 0) {
                deleteTimes++;
            } else {
                break;
            }
            // 将移除的负数减掉 更新negativeSum
            negativeSum = negativeSum - satisfaction[i -1];
        }
        // 计算ans
        for (int i = deleteTimes, j = 1; i < n; i++, j++) {
            ans += j * satisfaction[i];
        }
        return ans;
    }
    /**
     * 二分找第一个负数的下标
     */
    private int firstNegPos(int[] satisfaction, int n) {
        int l = 0;
        int r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            if (satisfaction[mid] >= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (satisfaction[l] >= 0 && l != 0) {
            return l - 1;
        } else
            return -1;

    }
    /**
     * 快排
     */
    private void quickSort(int[] a, int l, int r) {
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