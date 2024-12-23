package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 交替组2 {
    public static void main(String[] args) {
        int i = new Solution3208C().numberOfAlternatingGroups(new int[]{0,1,0,1,0}, 3);
        // 1,0,0,1,0,1,0
        // 0,1,0,0,1,0,1,0,1,0,0,1
        // 0,0,1,0,1,0,1,0,0,1,0,1
        System.out.println(i);
    }
}

class Solution3208 {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int cnt = 0;
        int n = colors.length;
        boolean isAlternate = false;
        // 拼一个内容一样的colors在colors后面 其实拼k-1个就可以了
        int[] newColors = new int[colors.length * 2];
        System.arraycopy(colors, 0, newColors, 0, colors.length);
        System.arraycopy(colors, 0, newColors, colors.length, k - 1);
        colors = newColors;
        for (int i = 0; i < n; i++) {
            if (i != 0 && colors[i + k - 1] != colors[i + k - 2] && isAlternate) {
                cnt++;
                continue;
            }
            int cur = colors[i];
            int len = 1, pre = i;
            while (len < k) {
                int nxt = pre + 1;
                if (colors[nxt] != cur) {
                    pre = nxt;
                    cur = colors[nxt];
                    if (++len == k) isAlternate = true;
                } else {
                    // 在小于k长度的时候，出现连续的颜色 重新定位i并且break
                    i = nxt - 1;
                    break;
                }
            }
            if (len < k) isAlternate = false;
            if (isAlternate) cnt++;
        }
        return cnt;
    }
}

class Solution3208B {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int res = 0, cnt = 1;
        for (int i = -k + 2; i < n; i++) {
            if (colors[(i + n) % n] != colors[(i - 1 + n) % n]) {
                cnt += 1;
            } else {
                cnt = 1;
            }
            if (cnt >= k) {
                res += 1;
            }
        }
        return res;
    }
}

class Solution3208C {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int ans = 0, cnt = 0;
        for (int i = 0; i < (n + k - 1); ++i) {
            if (i > 0 && colors[i % n] == colors[(i - 1) % n]) {
                cnt = 1;
            } else {
                ++cnt;
            }
            ans += cnt >= k ? 1 : 0;
        }
        return ans;
    }
}
