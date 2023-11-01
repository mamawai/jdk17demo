package com.example.demo.algorithm.B;

import java.util.*;

public class 欢乐的周末 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] map = new int[n][];

        for (int i = 0; i<n ; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(m, n, map));
    }

    private static int getResult(int m, int n, int[][] map) {
        ArrayList<int[]> queue = new ArrayList<>();

        int[][] tmp = new int[n][m];
        // 寻找起始点
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                int k = map[i][j];
                tmp[i][j] = k;
                if (map[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int[][] offsets = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};

        HashMap<String, Integer> res = new HashMap<>();

        for (int[] man : queue) {
            int x = man[0];
            int y = man[1];
            dfs(m, n, map, offsets, res, x, y);
            map = tmp;
        }

        List<Map.Entry<String, Integer>> collect = res.entrySet().stream().filter(stringIntegerEntry -> stringIntegerEntry.getValue() == 2).toList();

        return collect.size();
    }

    private static void dfs(int m, int n, int[][] map, int[][] offsets, HashMap<String, Integer> res, int x, int y) {
        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && map[newX][newY] == 3) {
                res.put(newX + String.valueOf(newY), res.getOrDefault(newX+String.valueOf(newY), 0)+1);
                map[newX][newY] = 1;
                dfs(m,n,map,offsets,res,newX,newY);
            }
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && (map[newX][newY] == 0 || map[newX][newY] == 2)) {
                map[newX][newY] = 1;
                dfs(m,n,map,offsets,res,newX,newY);
            }

        }
    }
}
