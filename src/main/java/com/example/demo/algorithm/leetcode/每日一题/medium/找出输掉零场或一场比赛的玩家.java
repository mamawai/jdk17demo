package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 找出输掉零场或一场比赛的玩家 {
    public static void main(String[] args) {

    }
}

class Solution2225 {
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> oneLose = new ArrayList<>();
        List<Integer> noLose = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            map.put(winner, map.getOrDefault(winner, 0));
            map.put(loser, map.getOrDefault(loser, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) continue;
            if (entry.getValue() == 1) oneLose.add(entry.getKey());
            if (entry.getValue() == 0) noLose.add(entry.getKey());
        }
        Collections.sort(noLose);
        Collections.sort(oneLose);
        ans.add(noLose);
        ans.add(oneLose);
        return ans;
    }
}