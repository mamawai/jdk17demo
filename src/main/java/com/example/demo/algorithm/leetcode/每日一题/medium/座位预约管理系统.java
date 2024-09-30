package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 座位预约管理系统 {
    public static void main(String[] args) {

    }
}

class SeatManager {
    // 退订座位先加入，取座位先从这里取，
    Queue<Integer> prioritySeats;
    int seatCnt = 1;

    public SeatManager(int n) {
        prioritySeats = new PriorityQueue<>((o1, o2) -> o1 - o2);
    }

    public int reserve() {
        if (prioritySeats.isEmpty()) return seatCnt++;
        else return prioritySeats.poll();
    }

    public void unreserve(int seatNumber) {
        prioritySeats.offer(seatNumber);
    }
}