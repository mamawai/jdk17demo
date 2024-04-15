package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 互质树 {
    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(1);
        stack.add(3);
        stack.add(2);
        stack.add(4);
        int indexOf = stack.indexOf(2);
        stack.remove(indexOf);
        stack.addFirst(5);
        stack.removeFirst();
        stack.add(indexOf, 2);
        System.out.println(stack);


//        int[] coprimes = new Solution1766().getCoprimes(new int[]{9,16,30,23,33,35,9,47,39,46,16,38,5,49,21,44,17,1,6,37,49,15,23,46,38,9,27,3,24,1,14,17,12,23,43,38,12,4,8,17,11,18,26,22,49,14,9},
//                new int[][]{{17,0},{30,17},{41,30},{10,30},{13,10},{7,13},{6,7},{45,10},{2,10},{14,2},{40,14},{28,40},{29,40},{8,29},{15,29},{26,15},{23,40},{19,23},{34,19},{18,23},{42,18},{5,42},{32,5},{16,32},{35,14},{25,35},{43,25},{3,43},{36,25},{38,36},{27,38},{24,36},{31,24},{11,31},{39,24},{12,39},{20,12},{22,12},{21,39},{1,21},{33,1},{37,1},{44,37},{9,44},{46,2},{4,46}});
        int[] coprimes = new Solution1766().getCoprimes(new int[]{2, 3, 3, 2}, new int[][]{{0, 2}, {2, 1}, {2, 3}});
        System.out.println("");
    }
}

class Solution1766 {
    int[] nums;
    List<Integer>[] g;
    int[] ans;
    public int[] getCoprimes(int[] nums, int[][] edges) {
        this.nums = nums;
        ans = new int[nums.length];
        ans[0] = -1;
        g = new List[nums.length];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        // 初始化list和map
        LinkedList<Integer> grandList = new LinkedList<>();
        grandList.add(nums[0]);
        HashMap<Integer, Deque<Integer>> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        map.put(nums[0], deque);
        dfs(0, -1, grandList, map);
        return ans;
    }

    private void dfs(int son, int fa, LinkedList<Integer> grandLink, HashMap<Integer, Deque<Integer>> map) {
        List<Integer> nodes = g[son];
        for (int node : nodes) {
            if (node == fa) continue;
            int nodeVal = nums[node];
            boolean flag = false;
            for (int v : grandLink) {
                if (gcd(v, nodeVal) == 1) {
                    ans[node] = map.get(v).getFirst();
                    flag = true;
                    break;
                }
            }
            if (!flag) ans[node] = -1;
            Deque<Integer> deque = map.get(nodeVal);
            int removedIndex = -1;
            if (deque == null) {
                deque = new ArrayDeque<>();
                deque.add(node);
                map.put(nodeVal, deque);
                grandLink.addFirst(nodeVal);
            } else {
                deque.addFirst(node);
                removedIndex = grandLink.indexOf(nodeVal);
                if (removedIndex != 0) {
                    grandLink.remove(removedIndex);
                    grandLink.addFirst(nodeVal);
                }
            }
            dfs(node, son, grandLink, map);
            // 恢复原状态
            if (removedIndex == -1) grandLink.pollFirst();
            else if (removedIndex != 0) {
                grandLink.pollFirst();
                grandLink.add(removedIndex, nodeVal);
            }
            Deque<Integer> integerDeque = map.get(nodeVal);
            if (integerDeque.size() == 1) map.remove(nodeVal);
            else integerDeque.removeFirst();
        }
    }

    private int gcd(int a, int b) {
        return (a % b == 0) ? b : gcd(b,a % b );
    }
}