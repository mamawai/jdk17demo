package com.example.demo.algorithm.B;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * OD196
 */
public class 计算疫情扩散时间 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(",");
        int N = (int) Math.sqrt(split.length);
        ArrayList<String[]> matrix = new ArrayList<>();
        String[] strings = new String[N];
        int arrL = 0;
        for (String s : split) {
            strings[arrL] = s;
            arrL++;
            if (arrL == N) {
                arrL = 0;
                matrix.add(strings);
                strings = new String[N];
            }
        }
        System.out.println(getResult(matrix,N));
    }

    private static int getResult(ArrayList<String[]> matrix, int N) {

        ArrayList<int[]> queue = new ArrayList<>();
        int days = 0;
        // 记录安全地区数量
        int safeArea = 0;
        // 记录疫情坐标
        for (int i = 0; i<N;i++) {
            for (int j = 0; j<N; j++) {
                if (matrix.get(i)[j].equals("1")) {
                    queue.add(new int[]{i,j});
                } else {
                    safeArea++;
                }
            }
        }

        if (queue.size() == 0) return -1;
        if (safeArea == N*N || safeArea == 0) return -1;

        // 上，下，左，右四个方向的偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (safeArea > 0 && queue.size() >0) {

            ArrayList<int[]> newQueue = new ArrayList<>();

            for (int[] pos : queue) {
                int x = pos[0];
                int y = pos[1];
                for (int[] offset : offsets) {
                    // 上，下，左，右四个方向扩散
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // 如果新位置没有越界，且为0，则可以被感染
                    if (newX >= 0
                            && newX < N
                            && newY >= 0
                            && newY < N
                            && "0".equals(matrix.get(newX)[newY])) {
                        matrix.get(newX)[newY] = "1";
                        newQueue.add(new int[] {newX, newY});
                        safeArea--;
                    }
                }
            }
            days++;
            queue = newQueue;
        }
        if (safeArea == 0) return days;
        else return -1;
    }
}
