package com.example.demo.algorithm.A;

import java.util.Scanner;

/**
 * AOD0019
 */
public class 对称美学 {
    static int isChange = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lines = Integer.parseInt(sc.next());

        double[][] arr = new double[lines][2];
        for (int i = 0; i<lines; i++) {
            arr[i][0] = sc.nextDouble();
            arr[i][1] = sc.nextDouble();
        }

        System.out.println(getResult(arr));
    }

    private static String  getResult(double[][] arr) {
        for (double[] nk : arr) {
            // 因为k从0开始这里提前加1
            dfs(nk[0], nk[1] + 1);
            if (isChange % 2 == 0) {
                return "red";
            } else {
                // 重置 isChange
                isChange = 0;
                return "blue";
            }
        }
        return null;
    }
    private static void dfs(double n, double k) {
        // dfs退出条件
        if (n == 1) {
            return;
        }
        if (n == 2) {
            if (k == 1) {
                isChange++;
            }
            return;
        }

        double halfLong = Math.pow(2, n - 2);
        // 当前位于前一半还是后一半 前一半则加一
        if (k <= halfLong) {
            isChange++;
        } else {
            // 如果k在后半段那么重新计算k在后半段作为前一段的位置
            k = k - halfLong;
        }
        dfs(n - 1, k);
    }
}
