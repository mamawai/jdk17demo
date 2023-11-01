package com.example.demo.algorithm.B;

import java.util.Scanner;

/**
 * 分治法
 */
public class 数列描述 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(n));
    }

    private static String getResult(int n) {
        String ans = "";
        for (int i = 0; i<= n;i++) {
            if (i == 0) {
                ans = "1";
            } else {
                ans = getAns(ans);
            }
        }
        return ans;
    }

    private static String getAns(String ans) {
        int times = 0;
        StringBuilder s = new StringBuilder();
        char[] charArray = ans.toCharArray();
        char current = charArray[0];
        for (char c : charArray) {
            // 如果相同增加times
            if (c == current) {
                times++;
            }
            // 如果当前字符和上一个不一样 也就是变了
            else {
                // 先将前一个存好
                s.append(times).append(current);
                // 重置times为1
                times = 1;
                // 修改current
                current = c;
            }
        }
        // 如果像 21 一样最后一个1还没存就退出循环了我们要把这个1存进去
        s.append(times).append(current);
        return s.toString();
    }
}
