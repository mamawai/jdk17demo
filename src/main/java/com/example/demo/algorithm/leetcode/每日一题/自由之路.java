package com.example.demo.algorithm.leetcode.每日一题;

import java.util.ArrayList;
import java.util.List;

public class 自由之路 {
    public static void main(String[] args) {
        int rotateSteps = new Solution514().findRotateSteps("nyngl", "yyynnnnnnlllggg");
        System.out.println(rotateSteps);
    }
}

class Solution514 {
    List<Integer>[] ch_index=new ArrayList[26];
    int[][] solution;
    public int findRotateSteps(String ring, String key) {
        int n=ring.length(),m=key.length();
        solution=new int[n][m];
        for (int i = 0; i < 26; i++) {
            ch_index[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {//记录字符出现的位置
            ch_index[ring.charAt(i)-'a'].add(i);
        }

        return dfs(n,0,key,0);
    }

    private int dfs(int n, int now, String key, int index) {
        if (index == key.length()) return 0;
        if(solution[now][index]>0) {
            return solution[now][index];
        }//有解直接返回
        List<Integer> list=ch_index[key.charAt(index)-'a'];//可能的位置
        int min = Integer.MAX_VALUE;
        for (Integer one : list) {
            int dis = Math.min(Math.abs(now-one),n-Math.abs(now-one)) + 1 + dfs(n,one,key,index+1);
            if(dis<min) min=dis;
        }
        solution[now][index]=min; //记录解
        return min;
    }
}