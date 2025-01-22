package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.List;

public class 从栈中取出K个硬币的最大面值和 {
    public static void main(String[] args) {
        ArrayList<List<Integer>> piles = new ArrayList<>();
        ArrayList<Integer> e = new ArrayList<>();
        e.add(1);
        e.add(101);
        e.add(200);
        ArrayList<Integer> e1 = new ArrayList<>();
        e1.add(1);
        e1.add(101);
        e1.add(200);
        piles.add(e);
        piles.add(e1);


        int i = new Solution2218B().maxValueOfCoins(piles, 5);
        System.out.println(i);
    }
}

class Solution2218 {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] f = new int[piles.size() + 1][k + 1];
        for (int i = 0; i < piles.size(); i++) {
            List<Integer> pile = piles.get(i);
            for (int j = 0; j <= k; j++) {
                // 不选这一组中的任何物品
                f[i + 1][j] = f[i][j];
                // 枚举选哪个
                int v = 0; // 物品价值和
                for (int w = 0; w < Math.min(j, pile.size()); w++) {
                    v += pile.get(w); // 前缀和累加物品价值和
                    // w 从 0 开始，物品体积为 w+1
                    f[i + 1][j] = Math.max(f[i + 1][j], f[i][j - w - 1] + v); // 状态转移方程
                }
            }
        }
        return f[piles.size()][k];
    }
}


class Solution2218B {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] f = new int[k + 1];
        int sumN = 0;
        for (List<Integer> Pile : piles) {
            Integer[] pile = Pile.toArray(Integer[]::new); // 转成数组处理更快更方便
            int n = pile.length;
            for (int i = 1; i < n; i++) {
                pile[i] += pile[i - 1]; // 提前计算 pile 的前缀和
            }
            sumN = Math.min(sumN + n, k);
            for (int j = sumN; j > 0; j--) { // 优化：j 从前 i 个栈的大小之和开始枚举
                for (int w = 0; w < Math.min(n, j); w++) {
                    f[j] = Math.max(f[j], f[j - w - 1] + pile[w]); // w 从 0 开始，物品体积为 w+1
                }
            }
        }
        return f[k];
    }
}