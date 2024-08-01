package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 双模幂运算 {
    public static void main(String[] args) {
        List<Integer> goodIndices = new Solution2961().getGoodIndices(new int[][]{{8,3,5,8}}, 4);
        System.out.println(goodIndices);
    }
}

class Solution2961 {
    private final static List<Integer>[] g = new List[10];
    static {
        Arrays.setAll(g, e-> new ArrayList<>());
        g[0].add(0);g[1].add(1);g[2].addAll(List.of(2,4,8,6));
        g[3].addAll(List.of(3,9,7,1));g[4].addAll(List.of(4,6));g[5].add(5);
        g[6].add(6);g[7].addAll(List.of(7,9,3,1));g[8].addAll(List.of(8,4,2,6));g[9].addAll(List.of(9,1));
    }
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int[] variable = variables[i];
            int a = variable[0] % 10, b = variable[1] - 1, c = variable[2], m = variable[3];
            List<Integer> fList = g[a];
            b = fList.get(b % fList.size());
            int tmpB = b;
            int tmpC = c;
            List<Integer> modLoop = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            while (--c >= 0) {
                int MOD = b % m;
                if (!set.add(MOD)) break;
                else modLoop.add(MOD);
                b = MOD * tmpB;
            }
            int n = modLoop.size();
            if (c < 0) {
                if (modLoop.get(n - 1) % m == target) ans.add(i);
            } else {
                if ((set.contains(0) ? 0 : modLoop.get((tmpC - 1) % n)) % m == target) ans.add(i);
            }
        }
        return ans;
    }
}