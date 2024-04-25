package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 尽量减少恶意软件的传播 {
    public static void main(String[] args) {
        int i = new Solution924().minMalwareSpread(new int[][]{{1,0,0,0,1,0,0,0,0,0,1},
                                                               {0,1,0,1,0,0,0,0,0,0,0},
                                                               {0,0,1,0,0,0,0,1,0,0,0},
                                                               {0,1,0,1,0,1,0,0,0,0,0},
                                                               {1,0,0,0,1,0,0,0,0,0,0},
                                                               {0,0,0,1,0,1,0,0,1,1,0},
                                                               {0,0,0,0,0,0,1,1,0,0,0},
                                                               {0,0,1,0,0,0,1,1,0,0,0},
                                                               {0,0,0,0,0,1,0,0,1,0,0},
                                                               {0,0,0,0,0,1,0,0,0,1,0},
                                                               {1,0,0,0,0,0,0,0,0,0,1}}, new int[]{7,8,6,2,3});
        System.out.println(i);
    }
}

class Solution924 {
    List<Integer> [] g;
    public int minMalwareSpread(int[][] graph, int[] initials) {
        g = new List[graph.length];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 0; i < graph.length - 1; i++) {
            for (int j = i + 1; j < graph.length; j++) {
                if (graph[i][j] == 1) {
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }
        // 找只有一个病毒源头的去算它感染的数量
        int[] res = new int[]{Integer.MAX_VALUE, -1};
        int[] quickFind = new int[graph.length];
        boolean[] isVisit = new boolean[graph.length];
        for (int initial : initials) {
            quickFind[initial]++;
        }
        Set<Integer> circleSet = new HashSet<>();
        for (int initial : initials) {
            if (circleSet.contains(initial)) {
                if (res[1] < 0) {
                    if (res[0] > initial) {
                        res[0] = initial;
                    }
                }
                continue;
            }
            boolean isCircle = false;
            int[] pair = new int[2];
            pair[0] = initial;
            pair[1] = g[initial].size();
            // bfs
            Queue<Integer> queue = new LinkedList<>(g[initial]);
            isVisit[initial] = true;
            while (!queue.isEmpty()) {
                Integer polled = queue.poll();
                if (quickFind[polled] != 0) {
                    circleSet.add(polled);
                    isCircle = true;
                }
                if (!isVisit[polled]) {
                    pair[1]++;
                    isVisit[polled] = true;
                    for (int node : g[polled]) {
                        if (!isVisit[node]) {
                            queue.offer(node);
                        }
                    }
                }
            }
            if (!isCircle) {
                if (pair[1] > res[1]) {
                    res = pair;
                } else if (pair[1] == res[1]) {
                    if (res[0] > pair[0]) {
                        res = pair;
                    }
                }
            } else {
                if (res[1] < 0) {
                    if (res[0] > initial) {
                        res[0] = initial;
                    }
                }
            }
        }
        return res[0];
    }
}
