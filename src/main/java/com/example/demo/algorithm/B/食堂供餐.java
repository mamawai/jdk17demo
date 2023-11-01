package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * OD172
 */
public class 食堂供餐 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int dishes = Integer.parseInt(sc.nextLine());
        int[] ints = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(N,dishes,ints));
    }

    private static int getResult(int n, int dishes, int[] ints) {
        int tmp = dishes;
        int x = 0;
        for (int i = 0; i<n; i++) {
            int current = ints[i];
            if (dishes - current < 0) {
                dishes = tmp;
                i = -1;
                x++;
                continue;
            }
            dishes = dishes - current + x;
        }
        return x;
    }
}
