package com.example.demo.algorithm.B;

import java.util.Scanner;

/**
 * OD222 位运算
 */
public class 素数之积 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int anInt = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(anInt));
    }

    private static String getResult(int anInt) {
        if (isPrime(anInt)) {
            return "-1 -1";
        }

        // 优化i取值范围
        for (int i = 2; i<Math.sqrt(anInt);i++) {
            if (anInt % i == 0) {
                int j = anInt / i;

                if (isPrime(i) && isPrime(j)) {
                    return i<j?i+" "+j:j+" "+i;
                } else {
                    break;
                }
            }
        }

        return "-1 -1";
    }

    private static boolean isPrime(int n) {
        if (n <= 3) {
            return n>1;
        }
        // 优化i取值范围
        for (int i = 2;i<Math.sqrt(n);i++) {
            if (n % i==0) {
                return false;
            }
        }
        return true;
    }
}
