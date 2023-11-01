package com.example.demo.algorithm.A;

import java.util.*;

/**
 * OD002
 */
public class 单向链表中间节点 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String headAddr = sc.next();
        int number = Integer.parseInt(sc.next());
        HashMap<String, String[]> nodes = new HashMap<>();

        for (int i = 0; i < number; i++){
            String addr = sc.next();
            String value = sc.next();
            String pointAddr = sc.next();
            nodes.put(addr, new String[]{value, pointAddr});
        }
        System.out.println(getMidValue(headAddr, nodes));
    }

    private static String getMidValue(String headAddr, HashMap<String, String[]> nodes) {
        // 快慢指针
        String[] slow = nodes.get(headAddr);
        String[] fast = nodes.get(slow[1]);

        while (fast != null){
            // 慢指针走一步
            slow = nodes.get(slow[1]);
            // 快指针走一步
            fast = nodes.get(fast[1]);
            // 如果这一步 fast 没走空 那么就再走一步 最终fast走了两步
            if (fast != null){
                fast = nodes.get(fast[1]);
            } else {
                // 反之跳出while
                // 比如一共5个 slow 走到3 ；fast 走到5 再走一步到6 但此时fast为null 即break
                // 再比如一共6个 slow 走到3 ；fast 走到6 fast不为null 但是slow和fast再同时走一步 slow 4 fast 7 fast为null 即 break
                // 所以最后返回slow[0]即可
                break;
            }
        }

        return slow[0];
    }
}
