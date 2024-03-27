package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 二叉树中的伪回文路径 {
    public static void main(String[] args) {
        //int i = new Solution1457().pseudoPalindromicPaths(new TreeNode(2, new TreeNode(3, new TreeNode(3, new TreeNode(2), new TreeNode(3)), new TreeNode(1, new TreeNode(1), new TreeNode(2))), new TreeNode(1, null, new TreeNode(1, new TreeNode(2), new TreeNode(2)))));
//        int i1 = new Solution1457().pseudoPalindromicPaths(new TreeNode(2, new TreeNode(1, new TreeNode(1), new TreeNode(3, null, new TreeNode(1))), new TreeNode(1)));
        int i2 = new Solution1457().pseudoPalindromicPaths(new TreeNode(8, new TreeNode(8, new TreeNode(7), new TreeNode(7, new TreeNode(2, null, new TreeNode(8, null, new TreeNode(1))), new TreeNode(4, null, new TreeNode(7)))),null));

        System.out.println(i2);
    }
}

class Solution1457 {
    int ans;
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root.left == null && root.right == null) return 1;
        int[] arr = new int[10];
        arr[root.val] ++;
        if (root.left != null)  {
            dfs(root.left, arr);
            arr[root.left.val]--;
        }
        if (root.right!= null) dfs(root.right, arr);
        return ans;
    }

    private void dfs(TreeNode node, int[] arr) {
        // 如果是根节点
        if (node.left == null && node.right == null) {
            arr[node.val]++;
            int oddCount = 0;
            for (int a : arr) {
                if (a % 2 == 1) {
                    oddCount++;
                    if (oddCount > 1) {
                        return;
                    }
                }
            }
            ans++;
            return;
        }
        arr[node.val]++;
        if (node.left != null) {
            dfs(node.left, arr);
            arr[node.left.val]--;
        }
        if (node.right != null) {
            dfs(node.right, arr);
            arr[node.right.val]--;
        }
    }
}