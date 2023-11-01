package com.example.demo.algorithm.B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class 增强的strstr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String src = sc.nextLine();
        String tar = sc.nextLine();

        System.out.println(getResult(src, tar));
    }

    public static int getResult(String src, String tar) {
        // 将tar字符串转化为levels多层结构，转化逻辑为：tar字符串中，每个[]包含的所有字符作为一层，未被[]包含的单个字符作为一层
        ArrayList<HashSet<Character>> levels = new ArrayList<>();

        // level用于记录[]中的字符
        HashSet<Character> level = new HashSet<>();
        boolean isOpen = false;

        for (int i = 0; i < tar.length(); i++) {
            char c = tar.charAt(i);

            switch (c) {
                case '[':
                    isOpen = true;
                    break;
                case ']':
                    isOpen = false;
                    levels.add(level);
                    level = new HashSet<>();
                    break;
                default:
                    if (isOpen) {
                        level.add(c);
                    } else {
                        HashSet<Character> tmp = new HashSet<>();
                        tmp.add(c);
                        levels.add(tmp);
                    }
            }
        }

        return indexOf(src, levels);
    }

    public static int indexOf(String src, ArrayList<HashSet<Character>> levels) {
        // 滑动匹配levels.length长度的子串
        for (int i = 0; i <= src.length() - levels.size(); i++) {
            boolean isFind = true;

            for (int j = 0; j < levels.size(); j++) {
                if (!levels.get(j).contains(src.charAt(i + j))) {
                    isFind = false;
                    break;
                }
            }

            if (isFind) return i;
        }

        return -1;
    }
}