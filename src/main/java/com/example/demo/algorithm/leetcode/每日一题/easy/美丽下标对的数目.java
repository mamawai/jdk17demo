package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 美丽下标对的数目 {
    public static void main(String[] args) {
        int i = new Solution2748().countBeautifulPairs(new int[]{2, 5, 1, 4});
    }
}

class Solution2748 {
    public int countBeautifulPairs(int[] nums) {
        int ans = 0;
        int[] cnt = new int[10];
        for (int x : nums) {
            for (int y = 1; y < 10; y++) {
                if (cnt[y] > 0 && gcd(y, x % 10) == 1) {
                    ans += cnt[y];
                }
            }
            while (x >= 10) {
                x /= 10;
            }
            cnt[x]++; // 统计最高位的出现次数
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}