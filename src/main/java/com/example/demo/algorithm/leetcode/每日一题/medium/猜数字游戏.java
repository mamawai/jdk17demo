package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 猜数字游戏 {
    public static void main(String[] args) {
        String hint = new Solution299().getHint("1123", "0111");
        System.out.println(hint);
        int zero = '0';
        int nine = '9';
        System.out.println(zero);
        System.out.println(nine);
    }
}

class Solution299 {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] sec = new int[58];
        int[] gue = new int[58];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                sec[secret.charAt(i)] ++;
                gue[guess.charAt(i)] ++;
            }
        }
        for (int i = 48; i < sec.length; i++) {
            if (sec[i] > 0 && gue[i] <= sec[i]) {
                cows += gue[i];
            }
            if (sec[i] > 0 && sec[i] < gue[i]) {
                cows += sec[i];
            }
        }
        return bulls + "A" + cows + "B";
    }
}