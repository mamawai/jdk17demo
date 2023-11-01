package com.example.demo.algorithm.B;

import java.util.*;
public class 第k个排列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();int k = sc.nextInt();
        System.out.println(getResult(n,k));
    }
    private static String getResult(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i<n; i++) {nums.add(i+1);}
        dfs(sb, n, k, nums);
        return sb.toString();
    }
    private static void dfs(StringBuilder sb, int n, int k, List<Integer> nums) {
        if (n == 0) {return;}
        int everyGroup = 1;
        for (int i = 1; i<n; i++) {everyGroup *= i;}
        // 看它是第几组 如 n = 4 k =5 就是第一组
        int whichGroup = k% everyGroup >0?(k/ everyGroup)+1:(k/ everyGroup);
        // 计算k 在第一组中属于第几个 还是第五个
        k = k- everyGroup *(whichGroup-1);
        // 拼接 1
        sb.append(nums.get(whichGroup-1));
        // 因为已经拼接过了 将候选nums中为1的数值删除注意删除的不是index
        nums.remove(nums.get(whichGroup-1));
        // dfs往下层走 即 在1234 1243 1324 1342 1423 1432这六个里面选 k位于第几组 以此类推
        dfs(sb, n-1, k, nums);
    }
}
