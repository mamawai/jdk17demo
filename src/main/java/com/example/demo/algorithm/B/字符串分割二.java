package com.example.demo.algorithm.B;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 一次过
 */
public class 字符串分割二 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.next());
        String string = sc.next();
        System.out.println(getResult(k,string));
    }

    private static String getResult(int k, String string) {
        String[] split = string.split("-");
        StringBuilder restString = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            restString.append(split[i]);
        }
        StringJoiner sj = new StringJoiner("-");
        sj.add(split[0]);
        for (int j = 0; j < restString.length(); j+=k) {
            String cut = "";
            if (j+k > restString.length()) {
                cut = restString.substring(j, restString.length());
                cut = compareUL(cut);
                sj.add(cut);
                break;
            }
            cut = restString.substring(j, j+k);
            cut = compareUL(cut);
            sj.add(cut);
        }
        return sj.toString();
    }

    private static String compareUL(String cut) {
        int upper = 0;
        int lower = 0;
        char[] charArray = cut.toCharArray();
        for (Character c : charArray) {
            if (c >= 'a' && c <= 'z') {
                lower++;
            } else if (c >= 'A' && c <= 'Z') {
                upper++;
            }
        }

        if (upper == lower) {
            return  cut;
        } else if (upper > lower) {
            return cut.toUpperCase();
        } else {
            return cut.toLowerCase();
        }
    }
}
