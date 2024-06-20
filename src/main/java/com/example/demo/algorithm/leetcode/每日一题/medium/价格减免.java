package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.text.DecimalFormat;
import java.util.StringJoiner;

public class 价格减免 {
    public static void main(String[] args) {
        int money = 1;
        DecimalFormat df = new DecimalFormat("0.00");
        String dis = df.format(money * (100 - 50)/100.00);
        System.out.println("money" + dis);
    }
}

class Solution2288 {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public String discountPrices(String sentence, int discount) {
        StringJoiner sj = new StringJoiner(" ");
        String[] split = sentence.split(" ");
        a:for (String s : split) {
            if (s.contains("$")) {
                if (s.charAt(0) == '$') {
                    char[] chars = s.toCharArray();
                    long money = 0;
                    int n = 1;
                    for (int i = chars.length - 1; i >= 1; i--) {
                        if (chars[i] >= '0' && chars[i] <= '9') {
                            money += (long) (chars[i] - 48) * n;
                            n = n * 10;
                        } else {
                            sj.add(s);
                            continue a;
                        }
                    }
                    if (money == 0) {
                        sj.add(s);
                    } else {
                        sj.add("$" + df.format(money * (100 - discount) / 100.00));
                    }
                    continue;
                }
            }
            sj.add(s);
        }
        return sj.toString();
    }
}