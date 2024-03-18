package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 最大单词长度乘积 {
    public static void main(String[] args) {
        int i = new Solution318().maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"});
        System.out.println(i);
    }
}

class Solution318{
    public int maxProduct(String[] words) {
        Queue<Pair<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getKey()- o1.getKey());
        for (String word : words) {
            StringBuilder sb = new StringBuilder("00000000000000000000000000");
            for (char c : word.toCharArray()) {
                int pos = c - 97;
                if (sb.charAt(pos) != '1') {
                    sb.replace(pos, pos + 1, "1");
                }
            }
            queue.offer(new ImmutablePair<>(word.length(), Integer.parseInt(sb.toString(), 2)));
        }
        int ans = 0;
        while (queue.size() > 0) {
            Pair<Integer, Integer> polled = queue.poll();
            Integer bi = polled.getValue();
            for (Pair<Integer, Integer> pair : queue) {
                if ((bi & pair.getValue()) == 0L) {
                    ans = Math.max(ans, polled.getKey() * pair.getKey());
                }
            }
        }
        return ans;
    }
}