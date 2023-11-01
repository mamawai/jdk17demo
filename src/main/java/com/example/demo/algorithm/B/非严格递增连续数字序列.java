package com.example.demo.algorithm.B;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * OD201
 */
public class 非严格递增连续数字序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static int getResult(String s) {
        int ans = 0;
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0 ; i<s.length();i++) {
            char c = s.charAt(i);
            if (c>='0' && c<='9') {
                if (list.size() == 0) {
                    list.add(c);
                } else {
                    if (list.getLast() > c){
                        ans = Math.max(ans,list.size());
                        list.clear();
                    }
                    list.add(c);
                }
            } else {
                if (list.size() > 0) {
                    ans = Math.max(ans,list.size());
                    list.clear();
                }
            }
        }
        ans = Math.max(ans,list.size());
        return  ans;
    }
}
