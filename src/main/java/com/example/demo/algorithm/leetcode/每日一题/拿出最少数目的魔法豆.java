package com.example.demo.algorithm.leetcode.每日一题;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class 拿出最少数目的魔法豆 {
    public static void main(String[] args) {
        long l = new SolutionB2171().minimumRemoval(new int[]{2, 10, 3, 2});
        System.out.println(l);

    }
}

class Solution2171 {
    public long minimumRemoval(int[] beans) {
        int length = beans.length;
        quickSort(beans, 0, length - 1);
        long summed = 0;
        for (int bean : beans) {
            summed += bean;
        }
        long ans = summed;
        // 只看移除袋子 移除0，1，2，3... length-1个袋子
        for (int i = 0; i < length; i++) {
            long beanNum = beans[i];
            ans = Math.min(summed - beanNum * (length - i), ans);
        }
        return ans;
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

class SolutionB2171 {
    public long minimumRemoval(int[] beans) {
        int[] cnt = new int[100001];
        long sum = 0, n = beans.length, ans = Long.MAX_VALUE, idx = 0;
        for (int b : beans) {
            cnt[b]++;
            sum += b;
        }
        long count = 0;
        for (int i = 0; i < 100001; ++i) {
            if (cnt[i] > 0) {
                ans = Math.min(ans, sum - (n - count) * i);
                count+=cnt[i];
            }
        }
        return ans;
    }
}