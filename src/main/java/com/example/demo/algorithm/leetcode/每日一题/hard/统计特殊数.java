package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 统计特殊数 {
    public static void main(String[] args) {
        System.out.println(new Solution2376().countSpecialNumbers(3456));
    }
}

class Solution2376 {
    public int countSpecialNumbers(int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int ans = 0;
        // 统计比n少一位的特殊数 比如34356 这里统计的是1-9999
        for (int i = len - 2; i >= 0; i--) {
            int j = i, cnt = 9;
            while (j > 0) cnt *= (10 - j--);
            ans += cnt;
        }
        // 与n位数相同的特殊数
        int[] arr = new int[10];
        // 遍历s
        int available = 10;// 指剩余可用数字
        for (int i = 0; i < len; i++) {
            int num = s.charAt(i) - '0';// 当前第i位数字
            int head = 0, cnt = 1, k = len - i - 1;
            int j = (i == 0 ? 1 : 0);// 第一位的时候不考虑0
            for (; j < num; j++) if (arr[j] == 0) head++;
            while (k > 0) cnt *= (available - k--);
            ans += head * cnt;
            if (arr[num]++ == 0) available--;// 统计第i位是否出现过，如果没出现过，available减一
            else return ans;// 重复出现直接返回 比如34356 遍历到第二个3的时候 统计完340- 341- 342- 之后就成了343- 开头的数字,重复了直接跳出
        }
        return ans + 1;// 如果走到这里说明n就是没有重复数字的数，这里把n加上取
    }
}
