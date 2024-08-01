package com.example.demo.algorithm.leetcode.weekly.week408;

public class 统计1显著的字符串的数量 {
    public static void main(String[] args) {
        int i = new Solution3234().numberOfSubstrings("00001");
        System.out.println(i);
    }
}
/**
 * 暴力枚举？
 * 如何优雅的枚举
 */
class Solution3234 {
    public int numberOfSubstrings(String s) {
        char[] array = s.toCharArray();
        int n = array.length;
        // 存每个0的下一个0节点 比如s中第2位是0第5位是0且第五位是最后一个0，那么first = 2，a[2] = 5， a[5] = n; 在最末尾加一个哨兵
        int[] a = new int[n];
        int pre = -1;
        int first = -1;
        for (int i = 0; i < n; i++) {
            if (array[i] == '0') {
                if (pre != -1) {
                    a[pre] = i;
                }
                pre = i;
                if (first == -1) first = i;
            }
        }
        if (first == - 1) return (1 + n) * n / 2;
        a[pre] = n;
        int ans = 0;
        // left为窗口左端点
        int left = 0;
        for (; left < n; left++) {
            // 当前left下第一个0
            int p = first;
            if (left + 1 > first) first = a[first];
            // 下一个0的位置
            if (p == n) break;
            int q = a[p];
            int cnt0 = 0;
            int cnt1 = p - left;
            ans += cnt1;
            // 从p开始遍历作为窗口的右端点
            for (int right = p; true;) {
                cnt0++;
                if (cnt0 * cnt0 > cnt1 + n - right + 1) break;
                // 如果窗口内0的平方大于1的平方，则窗口右移至下一个0的位置
                if (cnt0 * cnt0 > cnt1) {
                    // 两个0之间的1的个数
                    cnt1 += q - right - 1;
                    if (cnt0 * cnt0 <= cnt1) ans += cnt1 - cnt0 * cnt0 + 1;
                } else {
                    ans += q - right;
                    cnt1 += q - right - 1;
                }
                // 更新右端点 以及下一个0的位置
                right = q;
                if (right == n) break;
                q = a[right];
            }
        }
        ans += ((n - left) + 1) * (n - left) / 2 ;
        return ans;
    }
}
