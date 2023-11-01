package com.example.demo.algorithm.B;

import java.util.*;
import java.util.stream.Collectors;

public class 数组拼接 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int subLen = Integer.parseInt(sc.nextLine());

        int n = Integer.parseInt(sc.nextLine());

        LinkedList<LinkedList<Integer>> lists = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            lists.add(
                    new LinkedList<>(
                            Arrays.stream(sc.nextLine().split(","))
                                    // 输入用例中的数组存在 4,,6 这种情况，所以这里需要过滤掉空串
                                    .filter(a -> !"".equals(a))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList())));
        }

        System.out.println(getResult(subLen, lists));
    }

    public static String getResult(int subLen, LinkedList<LinkedList<Integer>> lists) {
        ArrayList<Integer> ans = new ArrayList<>();

        while (lists.size() > 0) {
            // 每次取第一个list 如果还有剩余那么addLast等待下一次取
            LinkedList<Integer> list = lists.removeFirst();
            ans.addAll(removeRange(list, 0, subLen));
            if (list.size() > 0) lists.addLast(list);
        }

        StringJoiner sj = new StringJoiner(",");
        for (Integer an : ans) {
            sj.add(an + "");
        }
        return sj.toString();
    }

    public static List<Integer> removeRange(LinkedList<Integer> list, int start, int end) {
        if (end > list.size()) end = list.size();

        List<Integer> tmp = list.subList(start, end);

        List<Integer> ans = new ArrayList<>(tmp);
        tmp.clear();

        return ans;
    }
}
