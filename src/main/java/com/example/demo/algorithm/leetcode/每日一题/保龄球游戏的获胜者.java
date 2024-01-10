package com.example.demo.algorithm.leetcode.每日一题;

public class 保龄球游戏的获胜者 {
    public static void main(String[] args) {
        int winner = new Solution2660().isWinner(new int[]{10, 2, 2, 3}, new int[]{3, 8, 4, 5});
        System.out.println(winner);
    }
}

class Solution2660 {
    public int isWinner(int[] player1, int[] player2) {
        int round = player1.length;
        int mark1 = 0;
        int mark2 = 0;
        for (int i = 0; i < round; i++) {
            boolean doubleFlag1 = false;
            boolean doubleFlag2 = false;
            if (i >= 2) {
                if (player1[i - 2] == 10 || player1[i - 1] == 10) {
                    doubleFlag1 = true;
                }
                if (player2[i - 2] == 10 || player2[i - 1] == 10) {
                    doubleFlag2 = true;
                }
                mark1 += doubleFlag1 ? 2 * player1[i] : player1[i];
                mark2 += doubleFlag2 ? 2 * player2[i] : player2[i];
            } else {
                if (i == 1) {
                    if (player1[0] == 10) {
                        doubleFlag1 = true;
                    }
                    if (player2[0] == 10) {
                        doubleFlag2 = true;
                    }
                    mark1 += doubleFlag1 ? 2 * player1[i] : player1[i];
                    mark2 += doubleFlag2 ? 2 * player2[i] : player2[i];
                } else {
                    mark1 += player1[i];
                    mark2 += player2[i];
                }
            }
        }
        if (mark1 == mark2) {
            return 0;
        } else if (mark1 > mark2){
            return 1;
        } else {
            return 2;
        }
    }
}
