package com.example.demo.algorithm.A;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 看答案做的 9.27 第一次
 */
public class 敏感字段加密 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();

        System.out.println(getResult(k,s));
    }

    private static String getResult(int k, String s) {

        // 创造一个栈
        StringBuilder stack = new StringBuilder();
        // 创造一个有序链表 用于拼接Result
        LinkedList<String> result = new LinkedList<>();

        // 遍历输入字符串
        for (int i = 0; i<s.length(); i++){
            // 取出第一个字节
            char c = s.charAt(i);

            if (c == '_' && (stack.length() == 0 || stack.charAt(0) != '"')){
                result.add(stack.toString());
                stack.setLength(0);
            }else if (c == '"' && !stack.isEmpty()){
                stack.append('"');
                result.add(stack.toString());
                stack.setLength(0);
            }else {
                stack.append(c);
            }
        }

        // 遍历到最后 stack里面可能还存着字符串 也要把这部分加进来
        if (stack.length() > 0){
            result.add(stack.toString());
        }

        List<String> ans = result.stream().filter(str -> !"".equals(str)).collect(Collectors.toList());

        if (k > ans.size() - 1){
            return "ERROR!";
        }

        ans.set(k,"******");

        StringJoiner stringJoiner = new StringJoiner("_");

        for (String an : ans){
            stringJoiner.add(an);
        }

        return stringJoiner.toString();
    }
}
