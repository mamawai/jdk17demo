package com.example.demo.algorithm.A;

import java.util.*;

/**
 * OD001
 */
public class 处理器问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        String replace = nextLine.replace(" ", "");
        String[] split = replace.substring(1, replace.length() - 1).split(",");
        String applyNum = sc.nextLine();
        if (split[0].equals("")){
            System.out.println(new ArrayList<>());
        } else {
            Integer[] cpuArray = Arrays.stream(split).map(Integer::parseInt).toArray(Integer[]::new);
            System.out.println(getResult(cpuArray, applyNum));
        }
    }

    private static String getResult(Integer[] cpuArray, String num) {
        ArrayList<Integer> link1 = new ArrayList<>();
        ArrayList<Integer> link2 = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        int index = 1;
        List<ArrayList<Integer>> result =  new ArrayList<>();
        // 分配link
        for (Integer cpu : cpuArray){
            if (cpu < 4){
                link1.add(cpu);
            } else {
                link2.add(cpu);
            }
        }

        link1.sort(Comparator.comparingInt(o -> o));
        link2.sort(Comparator.comparingInt(o -> o));

        // 获取链路数量
        int size1 = link1.size();
        int size2 = link2.size();

        // 当其中一个link size为零
        if (size1 == 0 || size2 ==0){
            if (size1 == 0) {
                dfs(num, index, path, result, link2);
            } else {
                dfs(num, index, path, result, link1);
            }
            return result.toString();
        }

        // 请求只有 1，2，4，8 四种
        // 亲和性调度原则为1时，剩余1个最佳 其次3 再次2 最次4
        if (num.equals("1") && size1+size2 >= 1){
            // 若奇偶相同返回最小的那个组合 若数量相同全用
            if (size1 % 2 == size2 % 2){
                if (size1 < size2) {
                    dfs(num, index, path, result, link1);
                } else {
                    dfs(num, index, path, result, link1, link2);
                }
            } else {
                // 若奇偶不同两个链 优先选奇数个
                if (size1 % 2 > size2 % 2) {
                    dfs(num, index, path, result, link1);
                } else {
                    dfs(num, index, path, result, link2);
                }
            }
        } else if (num.equals("2") && size1+size2 > 2) {
            // 亲和性调度原则为2时，剩余2个最佳 其次4 再次3
            if (size1%2 == size2%2){
                // 奇偶相同为偶数
                if (size1%2 == 0) {
                    // 数量相同link全用
                    if (size1 == size2) {
                        dfs(num, index, path, result, link1, link2);
                    } else if (size1 > size2) {
                        // 若大小不同取小的
                        dfs(num, index, path, result, link2);
                    } else {
                        dfs(num, index, path, result, link1);
                    }
                } else {
                    // 奇偶相同为奇数
                    // 数量相同link全用
                    if (size1 == size2) {
                        dfs(num, index, path, result, link1, link2);
                    }
                    // 奇偶相同为奇数 取大的
                    else if (size1 > size2){
                        dfs(num, index, path, result, link1);
                    } else {
                        dfs(num, index, path, result, link2);
                    }
                }
            } else {
                // 若奇偶不同 两个链优先选偶数个
                if (size1%2 > size2%2) {
                    dfs(num, index, path, result, link2);
                } else {
                    dfs(num, index, path, result, link1);
                }
            }
        } else if (num.equals("4") && (size1==4 || size2==4)) {
            // 亲和性调度原则为4时，选择同一链路的4个
            if (size1 == 4 && size2 == 4) {
                result.add(link1);
                result.add(link2);
            } else if (size1 == 4) {
                result.add(link1);
            } else {
                result.add(link2);
            }
        } else if (num.equals("8") && size1+size2 == 8){
            // 亲和性调度原则为8时，即申请全部
            link1.addAll(link2);
            result.add(link1);
        }
        // 若获取不到资源 直接返回空集合
        return result.toString();
    }

    private static void dfs(String applyNum, int index, ArrayList<Integer> path,
                            List<ArrayList<Integer>> result, ArrayList<Integer>... links) {
        // 遍历links
        for (ArrayList<Integer> link : links) {
            // 看是否满足请求num数量
            int parsedNum = Integer.parseInt(applyNum);
            if (path.size() == parsedNum) {
                result.add(new ArrayList<>(path));
                return;
            }
            // 确定剪枝后的上界 link.size()-(parsedNum - path.size())+1
            // 比如 你要4个元素 当前path有0个元素 link为1234 4-(4-0)+1 上界就为 1
            for (int i = index; i <= link.size()-(parsedNum - path.size())+1; i++){
                path.add(link.get(i-1));
                dfs(applyNum, i+1, path, result, link);
                path.remove(path.size()-1);
            }
        }
    }
}