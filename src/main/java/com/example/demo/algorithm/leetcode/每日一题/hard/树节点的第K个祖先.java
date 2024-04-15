package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 树节点的第K个祖先 {
    public static void main(String[] args) {
        TreeAncestorB treeAncestor = new TreeAncestorB(7, new int[]{-1, 0, 0, 1, 1, 2, 2});
        System.out.println(treeAncestor.getKthAncestor(3,1));
        System.out.println(treeAncestor.getKthAncestor(5,2));
        System.out.println(treeAncestor.getKthAncestor(6,3));
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
class TreeAncestor {
    private int[][] pa;

    public TreeAncestor(int n, int[] parent) {
        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        pa = new int[n][m];
        for (int i = 0; i < n; i++)
            pa[i][0] = parent[i];
        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
        for (int i = 0; i < m; i++) {
            if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                node = pa[node][i];
                if (node < 0) break;
            }
        }
        return node;
    }

    // 另一种写法，不断去掉 k 的最低位的 1
    public int getKthAncestor2(int node, int k) {
        for (; k > 0 && node != -1; k &= k - 1) {
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        }
        return node;
    }
}

/**
 * 每次只dfs一条链用那个dfs那个 还能反复用不超内存
 */
class TreeAncestorB {

    private int [] parent;
    private List<Integer>[] parentLists;

    public TreeAncestorB(int n, int[] parent) {
        this.parent=parent;
        this.parentLists = new List[n];
    }

    public int getKthAncestor(int node, int k) {
        if(parentLists[node] !=null){
            if(parentLists[node].size()<=k){
                return -1;
            }else{
                return parentLists[node].get(k);
            }
        }else{
            dfs(node);
            return getKthAncestor(node,k);
        }
    }
    private void dfs(int node){
        List<Integer> parents = new ArrayList<>();
        while(node !=-1 && this.parentLists[node] == null){
            parents.add(node);
            node = this.parent[node];
        }
        if(node !=-1){
            parents.addAll(this.parentLists[node]);
        }

        for(int i=0;i< parents.size();i++){
            if(this.parentLists[parents.get(i)]!=null){
                break;
            }
            this.parentLists[parents.get(i)]=parents.subList(i,parents.size());
        }
    }
}


