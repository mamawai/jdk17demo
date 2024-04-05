package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 有向无环图中一个节点的所有祖先 {
    public static void main(String[] args) {
        List<List<Integer>> ancestors = new Solution2192().getAncestors(8, new int[][]{{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}});
        System.out.println(ancestors);
    }
}

class Solution2192 {
    List<Integer>[] e;
    Set<Integer>[] memo;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> ans = new ArrayList<>();
        e = new List[n];
        memo = new Set[n];
        Arrays.setAll(e, e -> new ArrayList<>());
        Arrays.setAll(memo, m -> new HashSet<>());
        for (int[] edge : edges) {
            int son = edge[1];
            int fa = edge[0];
            e[son].add(fa);
        }
        for(int i = 0; i < n; i++){
            if(!memo[i].isEmpty() || e[i].isEmpty())continue;  // 祖先节点集合已生成或者无父节点的节点，跳过
            dfs(i);   // 递归处理
        }
        for(int i = 0; i < n; i++){
            ans.add(new ArrayList<>(memo[i]));
            Collections.sort(ans.get(i));
        }
        return ans;
    }

    private void dfs(int node) {
        for (int fa : e[node]) {
            memo[node].add(fa);
            if (memo[fa].isEmpty() && !e[node].isEmpty()) {
                dfs(fa);
            }
            if (!memo[fa].isEmpty()) {
                memo[node].addAll(memo[fa]);
            }
        }
    }
}

class Solution2192B {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] parents = new ArrayList[n];     // parent[i]存储节点i的所有直接父节点
        for(int i = 0; i < n; i++){
            parents[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            int pa = e[0], child = e[1];
            parents[child].add(pa);
        }
        Set<Integer>[] tmp = new HashSet[n];  // tmp[i]存储节点i的所有祖先节点集合，临时空间
        for(int i = 0; i < n; i++){
            tmp[i] = new HashSet<>();
        }
        for(int i = 0; i < n; i++){
            if(!tmp[i].isEmpty() || parents[i].isEmpty())continue;  // 祖先节点集合已生成或者无父节点的节点，跳过
            dfs(i, parents, tmp);   // 递归处理
        }
        // 重新构造结果数组，并排序
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            res.add(new ArrayList<>(tmp[i]));
            Collections.sort(res.get(i));
        }
        return res;
    }

    /**
     * 深度优先搜索，递归查找node所有祖先节点的祖先节点集合，然后合并
     * @param node: 当前节点编号
     * @param parents: 直接父节点集合
     * @param tmp: 当前处理的祖先节点集合
     */
    private void dfs(int node, List<Integer>[] parents, Set<Integer>[] tmp){
        // 枚举节点的所有父节点，合并他们的祖先节点集合
        for(int fa: parents[node]){
            tmp[node].add(fa);  // 祖先节点集合首先添加直接父节点
            if(tmp[fa].isEmpty() && !parents[node].isEmpty()){
                dfs(fa, parents, tmp);  // 父节点的祖先节点集合还没有生成，递归
            }
            if(!tmp[fa].isEmpty()){
                tmp[node].addAll(tmp[fa]);   // 合并父节点的祖先节点集合到node的祖先节点集合
            }
        }
    }
}

class Solution2192C {
    List<Integer>[] e;
    Set<Integer>[] memo;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> ans = new ArrayList<>();
        e = new List[n];
        memo = new Set[n];
        Arrays.setAll(e, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int son = edge[1];
            int fa = edge[0];
            e[son].add(fa);
        }
        for (int i = 0; i < e.length; i++) {
            List<Integer> fas = e[i];
            if (fas.isEmpty()) {
                // 没有父节点
                ans.add(new ArrayList<>());
                memo[i] = new HashSet<>();
            } else {
                // 有父节点 需要dfs + memo去追溯所有的父节点
                List<Integer> sorted = new ArrayList<>(dfs(fas));
                Collections.sort(sorted);
                ans.add(sorted);
            }
        }
        return ans;
    }

    /**
     * dfs父节点就变成了子节点
     */
    private Set<Integer> dfs(List<Integer> sons) {
        Set<Integer> backSet = new HashSet<>();
        for (int son : sons) {
            backSet.add(son);
            if (memo[son] != null) {
                backSet.addAll(memo[son]);
            } else {
                List<Integer> fas = e[son];
                if (fas.isEmpty()) {
                    memo[son] = new HashSet<>();
                } else {
                    memo[son] = dfs(fas);
                    backSet.addAll(memo[son]);
                }
            }
        }
        return backSet;
    }
}