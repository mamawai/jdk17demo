package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 统计可能的树根数目 {
    public static void main(String[] args) {
//        int i = new Solution2581().rootCount(new int[][]{{0,1},{2,0},{0,3},{4,2},{3,5},{6,0},{1,7},{2,8},{2,9},{4,10},{9,11},{3,12},{13,8},{14,9},{15,9},{10,16}},
//                new int[][]{{8, 2}, {12, 3}, {0, 1}, {16, 10}}, 2);
        int i = new Solution2581().rootCount(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                new int[][]{{1, 0}, {3, 4}, {2, 1}, {3, 2}}, 1);
        System.out.println(i);
    }
}

class Solution2581 {
    int count;
    int ans = 0;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        // 父节点到子节点
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();
        for (int[] guess : guesses) {
            List<Integer> orDefault = map.getOrDefault(guess[0], new ArrayList<>());
            orDefault.add(guess[1]);
            map.put(guess[0], orDefault);
        }
        for (int[] edge : edges) {
            List<Integer> orDefault = edgesMap.getOrDefault(edge[0], new ArrayList<>());
            List<Integer> orDefault1 = edgesMap.getOrDefault(edge[1], new ArrayList<>());
            orDefault.add(edge[1]);
            orDefault1.add(edge[0]);
            edgesMap.put(edge[0], orDefault);
            edgesMap.put(edge[1], orDefault1);
        }
        List<int[]> edgeslist = Arrays.asList(edges);
        // 这里暴力dfs 时间复杂度N²会超时
/*        for (int i = 0; i <= edges.length; i++) {
            count = 0;
            dfs(edgeslist, i, map, k);
            if (count == k) {
                ans++;
            }
        }*/
        count = 0;
        dfs(0, -1,map, edgesMap);
        // 换根dp
        reroot(0, -1, k, map, edgesMap, count);
        return ans;
    }

    private void dfs(int x, int fa, Map<Integer, List<Integer>> map, Map<Integer, List<Integer>> edgesMap) {
        for (int y : edgesMap.get(x)) {
            if (y != fa) {
                List<Integer> list = map.get(x);
                if (list != null) {
                    if (list.contains(y)) {
                        count++;
                    }
                }
                dfs(y, x, map, edgesMap);
            }
        }
    }
    private void reroot(int x, int fa, int k, Map<Integer, List<Integer>> map, Map<Integer, List<Integer>> edgesMap, int count) {
        if (count >= k) {
            ans++;
        }
        List<Integer> list2 = edgesMap.get(x);
        if (list2 != null) {
            for (int y : list2) {
                if (y != fa) {
                    int c = count;
                    List<Integer> list = map.get(x);
                    if (list != null && list.contains(y)) c--;
                    List<Integer> list1 = map.get(y);
                    if (list1 != null && list1.contains(x)) c++;
                    reroot(y, x, k, map, edgesMap, c);
                }
            }
        }
    }
}
