package com.example.demo.algorithm.A;

import java.util.*;

/**
 * OD003
 */
public class 字符串重新排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");

        System.out.println(getResult(words));
    }

    private static String getResult(String[] words) {

        words = Arrays.stream(words).map(
                s -> {
                    char[] charArray = s.toCharArray();
                    Arrays.sort(charArray);
                    return new String(charArray);
                }
        ).toArray(String[]::new);

        // 计算每个单词出现次数
        HashMap<String, Integer> countTimes = new HashMap<>();
        for (String word : words){
            Integer times = countTimes.get(word);
            if (times == null){
                countTimes.put(word,1);
            } else {
                countTimes.put(word,times+1);
            }
        }

        Arrays.sort(words, (a, b) -> {
            // 比较出现次数 次数不同 这么写是次数降序排列 即次数出现多的考前
            if (!countTimes.get(a).equals(countTimes.get(b))){
                return countTimes.get(b) - countTimes.get(a);
            }
            // 次数相同比较 长度 再比较首字母
            else {
                if (a.length() == b.length()){
                    for (int i = 0; i<a.length();i++){
                        if (a.charAt(i) != b.charAt(i)){
                            return a.charAt(i) - b.charAt(i);
                        }
                    }
                } else {
                    // 长度不同 升序排列 即短的字符串靠前
                    return a.length() - b.length();
                }
            }
            return 0;
        });

        StringJoiner sj = new StringJoiner(" ");
        for (String word : words){
            sj.add(word);
        }

        return sj.toString();
    }
}
