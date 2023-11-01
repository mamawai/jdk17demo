package com.example.demo.algorithm.B;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * OD186
 */
public class 字符串加密 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int anInt = Integer.parseInt(sc.nextLine());

        String[] lines = new String[anInt];
        for (int i = 0; i < anInt; i++) {
            lines[i] = sc.next();
        }

        // 初始化a数组
        int[] a = new int[50];
        a[0] = 1;
        a[1] = 2;
        a[2] = 4;
        for (int i = 3; i < 50; i++) {
            a[i] = (a[i - 1] + a[i - 2] + a[i - 3]) % 26;
        }

        for (int i = 0; i < anInt; i++) {
            System.out.println(getResult(lines[i],a));
        }
    }

    private static String getResult(String s, int[] a) {
        // (s.charAt(i) - 'a' + current) % 26 + 'a'
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) ((charArray[i] - 'a' + a[i]) % 26 + 'a');
        }
        return new String(charArray);
    }
}