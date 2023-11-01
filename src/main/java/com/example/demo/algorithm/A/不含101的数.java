package com.example.demo.algorithm.A;

import java.util.Scanner;

public class 不含101的数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nums = sc.nextLine();
        System.out.println(getResult(nums));
    }

    private static int getResult(String nums) {
        int sum = 0;
        String[] numsArray = nums.split(" ");
        for (int i = Integer.parseInt(numsArray[0]); i <= Integer.parseInt(numsArray[1]); i++){
            String binaryString = Integer.toBinaryString(i);
            if (binaryString.contains("101")) {
                sum ++;
            }
        }
        int length = Integer.parseInt(numsArray[1]) - Integer.parseInt(numsArray[0]);

        return length + 1 - sum;
    }
}
