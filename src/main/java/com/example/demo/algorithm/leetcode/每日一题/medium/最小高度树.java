package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 最小高度树 {
    public static void main(String[] args) {
        List<Integer> minHeightTrees = new FindMinHeightTrees().findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}});

    }
}

class Solution310 {
    private int[] rootHeights;    // rootHeights[i]表示以节点i为根的树的高度
    int minHeight;      // 最小高度

    private List<Integer>[] g;
    int[] dep;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // g[x] 表示 x 的所有邻居
        g = new ArrayList[n];
        dep = new int[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        // 从0开始 以0为根dfs 计算一次从0节点到n-1每个节点的深度
        dfs(0, -1);
        // 生成每个节点为根的树的高度
        rootHeights = new int[n];
        minHeight = n;
        // 换根dp
        changeRoot(0, -1);

        // 找到高度为最小高度的根节点
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(rootHeights[i] == minHeight)res.add(i);
        }
        return res;
    }

    private void changeRoot(int son, int fa) {
        // 统计当前节点node的所有子树的最大高度和次大高度
        // 如果只有一个子树那么次大高度为 0
        int maxH = 0;
        int secondMaxH = 0;
        for (int y : g[son]) {
            if(dep[y] > maxH){
                secondMaxH = maxH;
                maxH = dep[y];
            }else if(dep[y] > secondMaxH){
                secondMaxH = dep[y];
            }
        }
        rootHeights[son] = maxH + 1;   // 根节点node的高度就等于最大子树高度 + 1
        minHeight = Math.min(minHeight, rootHeights[son]);      // 更新最小高度
        // 换根，换到子节点为根的树
        // 每次换根的时候交换了两个节点，但是只更新成为子节点的根节点的高度，因为根节点node的高度就等于最大子树高度 + 1，
        for(int child: g[son]){
            if(child == fa) continue;
            dep[son] = (dep[child] == maxH ? secondMaxH : maxH) + 1;   // 换根后，当前节点变成子节点的子节点，更新其为根的子树高度
            changeRoot(child, son);
        }

    }

    private int dfs(int son, int fa) {
        int height = 1;
        for (int y : g[son]) {
            if (y != fa) {
                height = Math.max(height, dfs(y, son) + 1);
            }
        }
        dep[son] = height;
        return height;
    }

}

class FindMinHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            res.add(0);
            return res;
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;/*出度++*/
            map.get(edge[0]).add(edge[1]);/*添加相邻节点*/
            map.get(edge[1]).add(edge[0]);
        }
        /*建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        /*循环条件当然是经典的不空判断*/
        while (!queue.isEmpty()) {
            res = new ArrayList<>();/*这个地方注意，我们每层循环都要new一个新的结果集合，
            这样最后保存的就是最终的最小高度树了*/
            int size = queue.size();/*这是每一层的节点的数量*/
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);/*把当前节点加入结果集，不要有疑问，为什么当前只是叶子节点为什么要加入结果集呢?
                因为我们每次循环都会新建一个list，所以最后保存的就是最后一个状态下的叶子节点，
                这也是很多题解里面所说的剪掉叶子节点的部分，你可以想象一下图，每层遍历完，
                都会把该层（也就是叶子节点层）这一层从队列中移除掉，
                不就相当于把原来的图给剪掉一圈叶子节点，形成一个缩小的新的图吗*/
                List<Integer> neighbors = map.get(cur);
                /*这里就是经典的bfs了，把当前节点的相邻接点都拿出来，
                 * 把它们的出度都减1，因为当前节点已经不存在了，所以，
                 * 它的相邻节点们就有可能变成叶子节点*/
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        /*如果是叶子节点我们就入队*/
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;/*返回最后一次保存的list*/
    }

}