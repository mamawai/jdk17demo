package com.example.demo.algorithm.leetcode.weekly.week381;

public class 按距离统计房屋对数目1 {
    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        long[] ints = new Solution3015B().countOfPairs(8, 3, 6);
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1-l);
        char a = 'a' + 1;
        int z = 'z';
        int A = 'A';
        int Z = 'Z';
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append(a);
        System.out.println(append.toString());
        System.out.println(a + " " + z + " " + A + " " + Z);
        // 1 2 3. 4 5 6. 7 8
    }
}
class Solution3015B {
    public long[] countOfPairs(int n, int x, int y) {
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        long[] ans = new long[n];
        if (x + 1 >= y) {
            for (int i = 1; i < n; i++) {
                ans[i - 1] = (n - i) * 2;
            }
            return ans;
        }

        diff = new int[n + 1];
        for (int i = 1; i < n; i++) {
            if (i <= x) {
                int k = (x + y + 1) / 2;
                add(1, k - i);
                add(x - i + 2, x - i + y - k);
                add(x - i + 1, x - i + 1 + n - y);
            } else if (i < (x + y) / 2) {
                int k = i + (y - x + 1) / 2;
                add(1, k - i);
                add(i - x + 2, i - x + y - k);
                add(i - x + 1, i - x + 1 + n - y);
            } else {
                add(1, n - i);
            }
        }

        long sumD = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i + 1];
            ans[i] = sumD * 2;
        }
        return ans;
    }

    private int[] diff;

    private void add(int l, int r) {
        diff[l]++;
        diff[r + 1]--;
    }
}
