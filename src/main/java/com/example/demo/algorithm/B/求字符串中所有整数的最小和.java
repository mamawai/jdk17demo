package com.example.demo.algorithm.B;

import java.math.BigInteger;
import java.util.Scanner;
/**
 * OD149
 */
public class 求字符串中所有整数的最小和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        BigInteger minSum = new BigInteger("0");
        boolean isN = false;
        StringBuilder neg = new StringBuilder();
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (!isN) {
                    minSum = minSum.add(new BigInteger(String.valueOf(c)));
                } else {
                    neg.append(c);
                }
            } else {
                if (isN) {
                    minSum = minSum.subtract(new BigInteger(neg.toString()));
                    neg = new StringBuilder();
                }

                isN = c == '-';
            }
        }

        if (neg.length() > 0) {
            minSum = minSum.subtract(new BigInteger(neg.toString()));
        }
        return minSum.toString();
    }
}
