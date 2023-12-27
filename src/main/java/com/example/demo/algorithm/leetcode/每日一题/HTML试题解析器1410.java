package com.example.demo.algorithm.leetcode.每日一题;

public class HTML试题解析器1410 {
    public static void main(String[] args) {
        Solution1410 solution1410 = new Solution1410();
        String text = "x &gt; y &amp;&amp; x &lt; y is always false";
        System.out.println(solution1410.entityParser(text));
    }
}

class Solution1410 {

        public String entityParser(String text) {
            String reg1="&quot;";
            String reg2="&apos;";
            String reg3="&amp;";
            String reg4="&gt;";
            String reg5="&lt;";
            String reg6="&frasl;";
            return text.replace(reg1,"\"").replace(reg2,"'").replace(reg6,"/").replace(reg4,">").replace(reg5,"<").replace(reg3,"&");


        }

}