package com.example.demo.algorithm.leetcode.weekly.week409;

import java.util.*;

public class 新增道路查询后的最短距离I {
    public static void main(String[] args) {
        int[] ints = new SolutionB().shortestDistanceAfterQueries(5, new int[][]{
                {2, 4},
                {0, 2},
                {0, 4}
        });
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}

class Solution {
    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // 初始化邻接表
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 初始道路 (i -> i+1)
        for (int i = 0; i < n - 1; i++) {
            graph.get(i).add(new Edge(i + 1, 1));
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            graph.get(u).add(new Edge(v, 1));
            answer[i] = dijkstra(graph, n);
        }
        return answer;
    }

    private int dijkstra(List<List<Edge>> graph, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int node = current.target;
            int weight = current.weight;

            if (weight > dist[node]) {
                continue;
            }

            for (Edge edge : graph.get(node)) {
                int nextNode = edge.target;
                int newDist = dist[node] + edge.weight;
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.add(new Edge(nextNode, newDist));
                }
            }
        }

        return dist[n - 1];
    }
}


class SolutionB {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // 初始化邻接表
        int[][] graph = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            graph[i][i + 1] = 1; // 初始道路 (i -> i+1)
        }
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            graph[u][v] = 1; // 添加新道路 (u -> v)
            answer[i] = dijkstra(graph, n);
        }
        return answer;
    }

    private int dijkstra(int[][] graph, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{0, 0}); // {node, distance}
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];
            if (distance > dist[node]) continue;

            for (int nextNode = 0; nextNode < n; nextNode++) {
                if (graph[node][nextNode] > 0) {
                    int newDist = distance + graph[node][nextNode];
                    if (newDist < dist[nextNode]) {
                        dist[nextNode] = newDist;
                        pq.add(new int[]{nextNode, newDist});
                    }
                }
            }
        }

        return dist[n - 1];
    }

}






