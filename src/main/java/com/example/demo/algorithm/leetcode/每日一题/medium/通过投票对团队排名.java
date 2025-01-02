package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class 通过投票对团队排名 {
    public static void main(String[] args) {
        String s = new Solution1366().rankTeams(new String[]{"WXYZ","XYZW"});
        System.out.println(s);
    }
}

class Solution1366 {
    public String rankTeams(String[] votes) {
        StringBuilder ans = new StringBuilder();
        int n = votes[0].length();
        int[] map = new int[26];
        Arrays.fill(map, -1);
        int[][] ranksByTeam = new int[n][n];
        for (int j = 0; j < votes.length; j++) {
            if (j == 0) init(votes[j], map); // 初始化map
            char[] charArray = votes[j].toCharArray();
            for (int i = 0; i < charArray.length; i++) ranksByTeam[map[charArray[i] - 'A']][i]++;
        }
        // 堆排序
        Queue<Pair<Integer, int[]>> pq = new PriorityQueue<>((a, b) -> {
            int[] aRight = a.getValue();
            int[] bRight = b.getValue();
            for (int i = 0; i < aRight.length; i++) {
                if (aRight[i] != bRight[i]) return bRight[i] - aRight[i];
            }
            return a.getKey() - b.getKey();
        });
        for (int i = 0; i < ranksByTeam.length; i++) pq.add(new ImmutablePair<>(i, ranksByTeam[i]));
        while (!pq.isEmpty()) {
            Pair<Integer, int[]> p = pq.poll();
            int key = p.getKey(); // 这个key还要从map中反向找
            for (int j = 0; j < map.length; j++) {
                if (map[j] == key) {
                    ans.append((char) ('A' + j));
                    break;
                }
            }
        }
        return ans.toString();
    }

    private void init(String vote, int[] map) {
        char[] array = vote.toCharArray();
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) map[array[i] - 'A'] = i;
    }
}