package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 设计内存分配器 {
    public static void main(String[] args) {
        Allocator obj = new Allocator(100);
        String[] split = getStrings();
        for (int i = 0; i < split.length; i++) {
            String[] strings = split[i].split(",");
            if (strings.length == 1) {
                System.out.print(obj.freeMemory(Integer.parseInt(strings[0])) + " ");
            } else if (strings.length == 2) {
                System.out.print(obj.allocate(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])) + " ");
            }
        }
    }

    private static String[] getStrings() {
        String operations = "27],[12],[53],[61],[80],[21,78],[81,40],[50,76],[40],[76],[63],[25,100],[96,12],[92],[92],[84],[19,71],[22,90],[60],[42,79],[26,41],[59,33],[79],[58],[97],[92],[97],[92],[40],[52,74],[40],[53,17],[17],[36,32],[51,13],[41],[5,87],[44,66],[71],[53],[74,14],[78],[14],[32,54],[45,28],[84,47],[16],[100,78],[5,99],[33],[100],[62,79],[31,32],[85,81],[78],[34,45],[47,7],[7],[84],[6],[35,55],[94],[87],[20],[87],[96,60],[40,66],[28,96],[28],[25,2],[100],[96],[19,35],[16],[92,42],[80],[79";
        return operations.split("],\\[");
    }
}


class Allocator {

    int[] memory;

    Map<Integer, List<Integer>> map;

    int nullPointer = 0;

    public Allocator(int n) {
        memory = new int[n];
        map = new HashMap<>();
        Arrays.fill(memory, -1);
    }

    public int allocate(int size, int mID) {
        int originalPointer = nullPointer;
        int returnPointer;
        boolean skip = false;
        for (int i = nullPointer; i < nullPointer + size; i++) {
            if (i >= memory.length) {
                nullPointer = originalPointer; // pointer位置不变
                return -1;
            }
            if (memory[i] != -1) {
                skip = true;
                while (i < memory.length && memory[i] != -1) {
                    i++;
                }
                nullPointer = i;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = nullPointer; i < nullPointer + size; i++) {
            list.add(i);
            memory[i] = mID; // 对应的区间全部赋值
        }
        List<Integer> orDefault = map.getOrDefault(mID, new ArrayList<>());
        orDefault.addAll(list);
        map.put(mID, orDefault);
        returnPointer = nullPointer;
        if (skip) nullPointer = originalPointer;// 有跳过的空区间 pointer位置不变
        else while (nullPointer < memory.length && memory[nullPointer] != -1) nullPointer++;
        return returnPointer;
    }

    public int freeMemory(int mID) {
        if (!map.containsKey(mID)) return 0;
        List<Integer> list = map.get(mID);
        for (int index : list) {
            memory[index] = -1;
        }
        map.remove(mID);
        Collections.sort(list);
        nullPointer = Math.min(nullPointer, list.get(0)); // 取最小值，如果释放区间前面又空位那么pointer位置不变
        return list.size();
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.freeMemory(mID);
 */