package com.example.demo.algorithm.leetcode.每日一题;

import java.util.Arrays;

public class 购买两块巧克力 {
    public static void main(String[] args) {
        int i = new Solution2706().buyChoco(new int[]{98, 54, 6, 34, 66, 63, 52, 39}, 62);
        System.out.println(i);
    }
}

class Solution2706 {
    public int buyChoco(int[] prices, int money) {
        quickSort(prices,0,prices.length - 1);
        int minCombine = prices[0] + prices[1];
        if (minCombine > money) {
            return money;
        } else {
            return money - minCombine;
        }
    }

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
