package com.example.demo.algorithm.A;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 看答案做的 9.27 第一次
 */
public class IP地址转化为整数 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getResult(scanner.nextLine()));
        System.out.println(Integer.toHexString(256));
    }

    public static String getResult(String sc){
        try {
            Integer[] ip = Arrays.stream(sc.split("#")).map(Integer::parseInt).toArray(Integer[]::new);

            if (ip.length != 4){
                return "InValid IP";
            }

            int p1 = ip[0], p2 = ip[1], p3 = ip[2], p4 = ip[3];

            if (p1>=1 && p1<=128 && p2 >=0 && p2 <=255 && p3 >=0 && p3 <=255 && p4 >=0 && p4 <=255){
                String tmp = getHexString(p1) + getHexString(p2) +getHexString(p3) +getHexString(p4);
                return String.valueOf(Long.parseUnsignedLong(tmp, 16));
            }else {
                return "InValid IP";
            }
        } catch (Exception e) {
            return "InValid IP";
        }
    }

    public static String getHexString(int p){

        String s = Integer.toHexString(p);
        if (s.length() < 2){
            s = "0"+ s;
        }
        return s;
    }

}
