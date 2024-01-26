package com.example.demo.test;

import java.nio.charset.StandardCharsets;

public class TruncateTest {
    public static void main(String[] args) {
        interception(string2Array("str你好的话就撒开导航手机卡"), 23);
        String m = "(我";
        int length = m.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(length);
    }

    /**
     * 字符串按字节数截取
     * @param str
     * @param n
     */
    public static void interception(String[] str, int n)
    {
        int count = 0;
        String m = "[\u4e00-\u9fa5]";
        StringBuilder res = new StringBuilder();
        for (int i=0; i < str.length; i++) {
            if (str[i].matches(m)) {
                //如果当前字符是汉子，计数器加2
                count += 3;
            } else {
                //如果当前字符不是是汉子，计数器加1
                count += 1;
            }
            if (count <= 23) {
                res.append(str[i]);
            } else {
                System.out.println("dsa");
                break;
            }
        }
        System.out.println(res.toString());
    }
    /**
     * 将字符串转成字符串数组
     * @param string
     * @return
     */
    public static String[] string2Array(String string)
    {
        return string.split("");
    }
}
