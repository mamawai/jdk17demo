package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 新增道路查询后的最短距离 {
    public static void main(String[] args) {
        int[] ints = new Solution3243C().shortestDistanceAfterQueries(12, new int[][]{{8,10}, {2,9}, {1,5}, {9,11},{3,5}});
        // 0 1 2 3 4 5 6 7 8 9 10 11
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}

class Solution3243 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] ans = new int[queries.length];
        int[] dist = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            dist[i] = n - 1 - i;
        }
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int j = 0, queriesLength = queries.length; j < queriesLength; j++) {
            int[] query = queries[j];
            int from = query[0], to = query[1], disToEnd = to == n - 1 ? 1 : dist[to] + 1;
            int step = 0;
            for (int i = from; i >= 0; i--) {
                if (map.get(i) != null) {
                    // 遍历set集合
                    for (int tos : map.get(i)) {
                        if (tos <= from && disToEnd + step > dist[tos] + 1) {
                            disToEnd = dist[tos] + 1;
                            step = 0;
                            break;
                        }
                    }
                }
                dist[i] = Math.min(dist[i], disToEnd + step++);
            }
            ans[j] = dist[0];
            Queue<Integer> queue = map.get(from);
            if (queue == null) {
                PriorityQueue<Integer> q = new PriorityQueue<>();
                q.offer(to);
                map.put(from, q);
            } else queue.offer(to);
        }
        return ans;
    }
}

class Solution3243B {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] from = new ArrayList[n];
        Arrays.setAll(from, i -> new ArrayList<>());
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            f[i] = i;
        }

        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            from[r].add(l);
            if (f[l] + 1 < f[r]) {
                f[r] = f[l] + 1;
                for (int i = r + 1; i < n; i++) {
                    f[i] = Math.min(f[i], f[i - 1] + 1);
                    for (int j : from[i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
            ans[qi] = f[n - 1];
        }
        return ans;
    }
}

/**
 *  注意此解法的query路径不交叉，所以上面测试的用例不适用
 */
class Solution3243C {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] nxt = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nxt[i] = i + 1;
        }

        int[] ans = new int[queries.length];
        int cnt = n - 1;
        for (int qi = 0; qi < queries.length; qi++) {
            int i = queries[qi][0];
            int r = queries[qi][1];
            while (nxt[i] < r) {
                cnt--;
                int tmp = nxt[i];
                nxt[i] = r;
                i = tmp;
            }
            ans[qi] = cnt;
        }
        return ans;
    }
}

class Solution3243D {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {

    }
}