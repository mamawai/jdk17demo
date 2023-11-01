package com.example.demo.algorithm.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * OD0012
 */
public class 羊狼农夫过河 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sheep = sc.nextInt();
        int wolf = sc.nextInt();
        int capacity = sc.nextInt();
        System.out.println(getMinTimes(sheep, wolf, capacity));
    }

    private static int getMinTimes(int sheep, int wolf, int capacity) {
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(sheep, wolf, capacity, 0, 0, 0, ans);
        if (ans.size() > 0){
            return Collections.min(ans);
        } else {
            return 0;
        }
    }

    private static void dfs(int sheep, int wolf, int capacity, int oppo_sheep, int oppo_wolf, int count, ArrayList<Integer> ans) {

        // dfs停止条件
        if (sheep == 0 && wolf == 0) {
            ans.add(count);
            return;
        }

        // 最后一趟一次性运走
        if (sheep + wolf <= capacity){
            ans.add(count+1);
            return;
        }

        // i代表羊的数量 每次运输的最大值是 capacity和sheep中的最小值
        for (int i = 0; i <= Math.min(capacity, sheep); i++) {
            // j代表羊的数量 每次运输的最大值是 capacity和wolf中的最小值
            for (int j = 0; j <= Math.min(capacity, wolf); j++) {
                // 空运
                if (i+j == 0) continue;
                // 超载
                if (i+j > capacity) break;

                // 0 < 本岸的羊 <= 本岸的狼 说明狼运少了 continue 加狼
                if (sheep - i <= wolf - j && sheep - i != 0) continue;
                // 0 < 对岸的羊 <= 对岸的狼 说明狼运多了 break 加羊
                if (oppo_sheep + i <= oppo_wolf + j && oppo_sheep + i != 0) break;
                // 对岸没有羊 对岸的狼的数量大于等于船的容量(算这次运输)
                if (oppo_wolf + j >= capacity && oppo_sheep + i == 0) break;

                dfs(sheep - i, wolf - j, capacity, oppo_sheep + i, oppo_wolf + j, count + 1, ans);
            }
        }
    }
}
