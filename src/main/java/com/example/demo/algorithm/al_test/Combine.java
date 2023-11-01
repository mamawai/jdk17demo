package com.example.demo.algorithm.al_test;

import java.util.*;

public class Combine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> combine = combine(n, k);
        System.out.println(combine);
    }

    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n,k,1,path,result);
        return result;
    }
    private static void dfs(int n, int k, int index, Deque<Integer> path, List<List<Integer>> result) {
        // 当path中元素数量达到k时 说明满足一种情况 向result中添加
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i <= n; i++){
            path.addLast(i);
            // 例：第一次path中加了1，那么再往里面加就只能从2，3，4中选；如果path中第一个加了2，那么再往里加只能选3，4；因此这里要用i+1
            dfs(n,k,i+1,path,result);
            // 移除最后一个元素
            path.removeLast();
        }
    }
}
