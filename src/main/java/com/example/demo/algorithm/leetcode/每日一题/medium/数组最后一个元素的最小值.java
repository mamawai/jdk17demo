package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 数组最后一个元素的最小值 {
    public static void main(String[] args) {
        long l = new Solution3133().minEnd(2, 7);
        System.out.println(l);
    }
}

class Solution3133 {
    public long minEnd(int n, int x) {
        StringBuilder ans = new StringBuilder();
        n--;
        String nbs = Integer.toBinaryString(n);
        int nl = nbs.length() - 1;
        String xbs = Integer.toBinaryString(x);
        int i = xbs.length() - 1;
        for (; i >= 0; i--) {
            char charAt = xbs.charAt(i);
            if (nl >= 0) {
                if (charAt == '0' && nbs.charAt(nl--) == '1') {
                    ans.append('1');
                    continue;
                }
            }
            ans.append(charAt);
        }
        while (nl >= 0) {
            ans.append(nbs.charAt(nl--));
        }
        StringBuilder reversed = ans.reverse();
        return Long.parseLong(reversed.toString(), 2);
    }
}

class Solution3133B {
    public long minEnd(int n, int x) {
        n--; // 先把 n 减一，这样下面讨论的 n 就是原来的 n-1
        long ans = x;
        int i = 0, j = 0;
        while ((n >> j) > 0) {
            // x 的第 i 个比特值是 0，即「空位」
            if ((ans >> i & 1) == 0) {
                // 空位填入 n 的第 j 个比特值
                ans |= (long) (n >> j & 1) << i;
                j++;
            }
            i++;
        }
        return ans;
    }
}